package br.com.infoterras.bindapplication.ViewModel;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import br.com.infoterras.bindapplication.BR;
import br.com.infoterras.bindapplication.R;
import br.com.infoterras.bindapplication.adapter.RecyclerBindingAdapter;
import br.com.infoterras.bindapplication.adapter.RecyclerConfiguration;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;

/**
 * Created by Gustavo on 28/11/2016.
 */

public class UserViewModel {

    public User user;
    private Context context;
    public RecyclerConfiguration recyclerConfiguration;

    public UserViewModel(Context context, User user) {
        this.user = user;
        this.context = context;
        this.recyclerConfiguration = new RecyclerConfiguration();


        initRecycler();
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
        /*adapter.setOnItemClickListener((position, item)
                -> Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show(),
                false));*/
        return adapter;
    }
}
