package br.com.infoterras.bindapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.infoterras.bindapplication.databinding.ActivityMainBinding;
import br.com.infoterras.bindapplication.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user = new User("Gustavo", "Terras", 28);
        binding.setUser(user);
    }
}
