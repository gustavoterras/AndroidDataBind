package br.com.infoterras.bindapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.network.ConsumerService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Gustavo on 01/12/2016.
 */

public class SearchActivity extends AppCompatActivity implements ConsumerService.OnTaskCompleted<User>{

    private static final int REQUEST_CODE = 563;
    private ConsumerService consumerService;
    private ProgressDialog progress;

    @BindView(R.id.edt_search) EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        progress = new ProgressDialog(this);
        progress.setMessage("Pesquisando...");

        consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
    }

    @OnClick(R.id.btn_search)
    public void onClick(View view){
        progress.show();
        consumerService.getUser(edtSearch.getText().toString(), REQUEST_CODE);
    }

    @Override
    public void onSuccess(User response, int requestCode) {

        if(progress.isShowing())
            progress.dismiss();

        if(response == null)
            Toast.makeText(this, "Usuário não encontrado!", Toast.LENGTH_SHORT).show();
        else
            startActivity(new Intent(this, MainActivity.class).putExtra("extra", response));
    }

    @Override
    public void onFailure(Throwable error) {
        progress.dismiss();
    }
}
