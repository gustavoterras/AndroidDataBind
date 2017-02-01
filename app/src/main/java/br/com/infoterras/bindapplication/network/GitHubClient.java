package br.com.infoterras.bindapplication.network;

import java.util.List;

import br.com.infoterras.bindapplication.model.Content;
import br.com.infoterras.bindapplication.model.Repository;
import br.com.infoterras.bindapplication.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Gustavo on 22/11/2016.
 */

public interface GitHubClient {

    @GET("/users/{username}")
    Call<User> user(@Path("username") String owner);

    @GET("/users/{username}/repos")
    Call<List<Repository>> repository(@Path("username") String owner);

    @GET("/repos/{username}/{repository}/contents")
    Call<List<Content>> content(@Path("username") String owner, @Path("repository") String repository);

    @GET("/repos/{username}/{repository}/contents/{path}?ref=master")
    Call<List<Content>> content(@Path("username") String owner, @Path("repository") String repository, @Path("path") String path);

    @GET
    Call<ResponseBody> file(@Url String url);

}
