package br.com.infoterras.bindapplication.viewModel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import br.com.infoterras.bindapplication.BR;
import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.activity.CodeViewActivity;
import br.com.infoterras.bindapplication.activity.ContentActivity;
import br.com.infoterras.bindapplication.adapter.RecyclerBindingAdapter;
import br.com.infoterras.bindapplication.adapter.RecyclerConfiguration;
import br.com.infoterras.bindapplication.model.Content;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import br.com.infoterras.bindapplication.network.ConsumerService;

/**
 * Created by Gustavo on 28/11/2016.
 */

public class ContentViewModel implements RecyclerBindingAdapter.OnItemClickListener<Content>, ConsumerService.OnTaskCompleted<ArrayList<Content>>{

    private static final int REQUEST_CODE_CONTENT = 513;

    public User user;
    public Content content;
    private Context context;
    public Repository repository;
    public RecyclerConfiguration recyclerConfiguration;

    public ContentViewModel(Context context, Bundle bundle) {
        this.context = context;
        this.user = (User) bundle.getSerializable("user");
        this.repository = (Repository) bundle.getSerializable("repository");
        this.recyclerConfiguration = new RecyclerConfiguration();

        initRecycler();

        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);

        if(bundle.containsKey("item")) {
            content = (Content) bundle.getSerializable("item");
            consumerService.getContentByPath(user.getLogin(), repository.getName(), content.getPath(), REQUEST_CODE_CONTENT);
        }else
            consumerService.getContentByRepository(user.getLogin(), repository.getName(), REQUEST_CODE_CONTENT);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(ArrayList<Content> response, int requestCode) {
        RecyclerBindingAdapter<Content> adapter = (RecyclerBindingAdapter<Content>) recyclerConfiguration.getAdapter();
        adapter.setList(response);
    }

    @Override
    public void onFailure(Throwable error) {

    }

    private void initRecycler() {
        RecyclerBindingAdapter<Content> adapter = getAdapter();

        recyclerConfiguration.setLayoutManager(new LinearLayoutManager(context));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<Content> getAdapter() {
        ArrayList<Content> data = new ArrayList<>();
        RecyclerBindingAdapter<Content> adapter = new RecyclerBindingAdapter<>(R.layout.item_content_adapter, BR.content, data);
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    public void onItemClick(int position, Content item) {
        if(item.getType().equals("file"))
            context.startActivity(new Intent(context, CodeViewActivity.class).putExtra("extra", item));
        else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            bundle.putSerializable("repository", repository);
            bundle.putSerializable("item", item);

            context.startActivity(new Intent(context, ContentActivity.class).putExtra("extra", bundle));
        }
    }
}
