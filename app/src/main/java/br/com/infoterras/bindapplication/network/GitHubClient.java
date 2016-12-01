package br.com.infoterras.bindapplication.network;

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

}
