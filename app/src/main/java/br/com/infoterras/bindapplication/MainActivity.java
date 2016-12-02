package br.com.infoterras.bindapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import br.com.infoterras.bindapplication.databinding.ActivityMainBinding;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.network.ConsumerService;

/**
 * Created by Gustavo on 01/12/2016.
 */

public class MainActivity extends AppCompatActivity implements ConsumerService.OnTaskCompleted<List<Repository>>{

    private static final int REQUEST_CODE_REPOSITORY = 932;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        User user = (User) getIntent().getSerializableExtra("extra");

        binding.setUser(user);

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getRepositoryByUser(user.getLogin(), REQUEST_CODE_REPOSITORY);
    }

    @Override
    public void onSuccess(List<Repository> response, int requestCode) {

    }

    @Override
    public void onFailure(Throwable error) {

    }
}
