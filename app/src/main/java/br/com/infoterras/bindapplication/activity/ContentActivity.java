package br.com.infoterras.bindapplication.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.databinding.ActivityContentBinding;
import br.com.infoterras.bindapplication.viewModel.ContentViewModel;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Bundle bundle = getIntent().getBundleExtra("extra");

        ContentViewModel contentViewModel = new ContentViewModel(this, bundle);

        ActivityContentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_content);
        binding.setContentViewModel(contentViewModel);
    }
}
