package br.com.infoterras.bindapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;

import br.com.infoterras.bindapplication.BR;
import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.activity.ContentActivity;
import br.com.infoterras.bindapplication.adapter.RecyclerBindingAdapter;
import br.com.infoterras.bindapplication.adapter.RecyclerConfiguration;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.network.ConsumerService;

/**
 * Created by Gustavo on 28/11/2016.
 */

public class UserViewModel implements RecyclerBindingAdapter.OnItemClickListener<Repository>, ConsumerService.OnTaskCompleted<ArrayList<Repository>>{

    private static final int REQUEST_CODE_REPOSITORY = 932;

    public User user;
    private Context context;
    public RecyclerConfiguration recyclerConfiguration;

    public UserViewModel(Context context, User user) {
        this.user = user;
        this.context = context;
        this.recyclerConfiguration = new RecyclerConfiguration();

        initRecycler();

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getRepositoryByUser(user.getLogin(), REQUEST_CODE_REPOSITORY);
    }

    private void initRecycler() {
        RecyclerBindingAdapter<Repository> adapter = getAdapter();

        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(context));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<Repository> getAdapter() {
        ArrayList<Repository> data = new ArrayList<>();
        RecyclerBindingAdapter<Repository> adapter = new RecyclerBindingAdapter<>(R.layout.item_repository_adapter, BR.repository, data);
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    public void onItemClick(int position, Repository repository) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putSerializable("repository", repository);

        context.startActivity(new Intent(context, ContentActivity.class).putExtra("extra", bundle));
    }

    @BindingAdapter({"visibility"})
    public static void setVisibility(View view, String value) {
        view.setVisibility(TextUtils.isEmpty(value) ? View.GONE: View.VISIBLE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ArrayList<Repository> response, int requestCode) {
        RecyclerBindingAdapter<Repository> adapter = (RecyclerBindingAdapter<Repository>) recyclerConfiguration.getAdapter();
        adapter.setList(response);
    }

    @Override
    public void onFailure(Throwable error) {

    }
}
