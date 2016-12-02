package br.com.infoterras.bindapplication.network;

import java.util.List;

import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Gustavo on 22/11/2016.
 */

public class ConsumerService {

    private OnTaskCompleted listener;

    public interface OnTaskCompleted<T> {
        void onSuccess(T response, int requestCode);
        void onFailure(Throwable error);
    }

    public void setOnTaskCompleted(OnTaskCompleted onTaskCompleted){
        listener = onTaskCompleted;
    }

    public void getUser(String username, final int requestCode){
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class);
        client.user(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listener.onSuccess(response.body(), requestCode);
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                listener.onFailure(throwable);
            }
        });
    }

    public void getRepositoryByUser(String username, final int requestCode){
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class);
        client.repository(username).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                listener.onSuccess(response.body(), requestCode);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable throwable) {
                listener.onFailure(throwable);
            }
        });
    }
}
