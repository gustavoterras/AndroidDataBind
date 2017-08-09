package br.com.infoterras.bindapplication.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import java.io.IOException;

import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.databinding.ActivityCodeViewBinding;
import br.com.infoterras.bindapplication.model.Content;
import br.com.infoterras.bindapplication.network.ConsumerService;
import br.tiagohm.codeview.CodeView;
import br.tiagohm.codeview.HightlightJs;
import okhttp3.ResponseBody;

public class CodeViewActivity extends AppCompatActivity implements ConsumerService.OnTaskCompleted<ResponseBody> {

    private static final int FILE_REQUEST_CODE = 627;

    private CodeView codeView;
    private FrameLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_view);

        ActivityCodeViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_code_view);

        codeView = binding.codeView;
        progressLayout = binding.progressLayout;

        Content file = (Content) getIntent().getSerializableExtra("extra");

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getFile(file.getDownload_url(), FILE_REQUEST_CODE);
    }

    @Override
    public void onSuccess(ResponseBody response, int requestCode) {
        try {
            codeView.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView view, String url) {
                    codeView.setVisibility(View.VISIBLE);
                    progressLayout.setVisibility(View.GONE);
                }
            });

            codeView.setSyntaxHighlighter(new HightlightJs())
                    .setCode(response.string())
                    .setLanguage(HightlightJs.Languages.AUTOIT)
                    .setTheme(HightlightJs.Themes.DRACULA)
                    .setTextSize(14)
                    .setShowLineNumber(true)
                    .apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Throwable error) {

    }
}
