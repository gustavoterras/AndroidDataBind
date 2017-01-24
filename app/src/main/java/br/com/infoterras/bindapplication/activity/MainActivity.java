package br.com.infoterras.bindapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.ViewModel.UserViewModel;
import br.com.infoterras.bindapplication.adapter.RecyclerBindingAdapter;
import br.com.infoterras.bindapplication.databinding.ActivityMainBinding;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.network.ConsumerService;

/**
 * Created by Gustavo on 01/12/2016.
 */

public class MainActivity extends AppCompatActivity implements ConsumerService.OnTaskCompleted<ArrayList<Repository>>{

    private static final int REQUEST_CODE_REPOSITORY = 932;
    private UserViewModel userViewModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = (User) getIntent().getSerializableExtra("extra");
        userViewModel = new UserViewModel(this, user);
        binding.setViewModel(userViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initCollapsingToolbar();

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getRepositoryByUser(user.getLogin(), REQUEST_CODE_REPOSITORY);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(user.getName());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ArrayList<Repository> response, int requestCode) {
        RecyclerBindingAdapter<Repository> adapter = (RecyclerBindingAdapter<Repository>) userViewModel.recyclerConfiguration.getAdapter();
        adapter.setList(response);
    }

    @Override
    public void onFailure(Throwable error) {

    }
}
