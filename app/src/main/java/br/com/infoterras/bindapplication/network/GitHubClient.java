package br.com.infoterras.bindapplication.network;

import java.util.List;

import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Gustavo on 22/11/2016.
 */

public interface GitHubClient {

    @GET("/users/{username}")
    Call<User> user(@Path("username") String owner);

    @GET("/users/{username}/repos")
    Call<List<Repository>> repository(@Path("username") String owner);

}
