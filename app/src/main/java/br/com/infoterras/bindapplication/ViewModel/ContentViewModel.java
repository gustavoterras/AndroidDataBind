package br.com.infoterras.bindapplication.viewModel;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.infoterras.bindapplication.BR;
import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.adapter.RecyclerBindingAdapter;
import br.com.infoterras.bindapplication.adapter.RecyclerConfiguration;
import br.com.infoterras.bindapplication.model.Content;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;

/**
 * Created by Gustavo on 28/11/2016.
 */

public class ContentViewModel implements RecyclerBindingAdapter.OnItemClickListener<Content>{

    public User user;
    public Repository repository;
    private Context context;
    public RecyclerConfiguration recyclerConfiguration;

    public ContentViewModel(Context context, User user, Repository repository) {
        this.user = user;
        this.context = context;
        this.repository = repository;
        this.recyclerConfiguration = new RecyclerConfiguration();

        initRecycler();
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
        Toast.makeText(context, item.getName(), Toast.LENGTH_SHORT).show();
    }
}
