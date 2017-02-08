package br.com.infoterras.bindapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import br.com.infoterras.bindapplication.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DISPLAY_LENGTH = 3500;
    private static boolean hasOpened = false;

    @BindView(R.id.tv_message1) TextView txt1;
    @BindView(R.id.tv_message2) TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        animateMessageView(txt1, hasOpened, R.anim.enter_from_left);
        animateMessageView(txt2, hasOpened, R.anim.enter_from_right);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!hasOpened) {
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity.this, findViewById(R.id.img_logo), "image").toBundle();
                    Intent mainIntent = new Intent(SplashActivity.this, SearchActivity.class);
                    SplashActivity.this.startActivity(mainIntent, bundle);
                } else
                    finish();

                hasOpened = !hasOpened;
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void animateMessageView(final View view, boolean visibility, int animResource){

        if(visibility){
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), animResource);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            view.startAnimation(animation);
        }else
            view.setVisibility(View.INVISIBLE);
    }
}
