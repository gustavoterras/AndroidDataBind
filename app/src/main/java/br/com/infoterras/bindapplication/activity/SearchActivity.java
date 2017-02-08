package br.com.infoterras.bindapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.databinding.ActivitySearchBinding;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.network.ConsumerService;
import br.com.infoterras.bindapplication.util.BuildUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gustavo on 01/12/2016.
 */

public class SearchActivity extends AppCompatActivity implements ConsumerService.OnTaskCompleted<User>{

    private static final int REQUEST_CODE = 563;
    private ConsumerService consumerService;

    @BindView(R.id.progress) ProgressBar progress;
    @BindView(R.id.edt_search) EditText edtSearch;
    @BindView(R.id.img_logo) ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ActivitySearchBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setBuild(new BuildUtil());

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
    }

    @OnClick(R.id.btn_search)
    public void onClick(View view){
        progress.setVisibility(View.VISIBLE);
        consumerService.getUser(edtSearch.getText().toString(), REQUEST_CODE);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onSuccess(User response, int requestCode) {

        if(progress.getVisibility() == View.VISIBLE)
            progress.setVisibility(View.GONE);

        if(response == null)
            Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
        else {
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imgLogo, "image").toBundle();
            startActivity(new Intent(this, MainActivity.class).putExtra("extra", response), bundle);
        }
    }

    @Override
    public void onFailure(Throwable error) {
        progress.setVisibility(View.GONE);
    }
}
