package br.com.infoterras.bindapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.databinding.ActivityContentBinding;
import br.com.infoterras.bindapplication.viewModel.ContentViewModel;
import br.com.infoterras.bindapplication.adapter.RecyclerBindingAdapter;
import br.com.infoterras.bindapplication.model.Content;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.network.ConsumerService;

public class ContentActivity extends AppCompatActivity implements ConsumerService.OnTaskCompleted<ArrayList<Content>>{

    private static final int REQUEST_CODE_CONTENT = 513;
    private ContentViewModel contentViewModel;
    private Repository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Bundle bundle = getIntent().getBundleExtra("extra");

        user = (User) bundle.getSerializable("user");
        repository = (Repository) bundle.getSerializable("repository");
        contentViewModel = new ContentViewModel(this, user, repository);

        ActivityContentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_content);
        binding.setContentViewModel(contentViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getContentByRepository(user.getLogin(), repository.getName(), REQUEST_CODE_CONTENT);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ArrayList<Content> response, int requestCode) {
        RecyclerBindingAdapter<Content> adapter = (RecyclerBindingAdapter<Content>) contentViewModel.recyclerConfiguration.getAdapter();
        adapter.setList(response);
    }

    @Override
    public void onFailure(Throwable error) {

    }
}
