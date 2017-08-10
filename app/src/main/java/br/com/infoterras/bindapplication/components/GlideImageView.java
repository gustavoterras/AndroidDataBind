package br.com.infoterras.bindapplication.components;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import br.com.infoterras.bindapplication.R;

/**
 * Created by Gustavo on 01/12/2016.
 */

public class GlideImageView extends android.support.v7.widget.AppCompatImageView {
    public GlideImageView(Context context) {
        super(context);
    }

    public GlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlideImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindingAdapter("app:src")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(view.getContext()))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(view.getContext().getDrawable(R.drawable.ic_github))
                .into(view);
    }
}
