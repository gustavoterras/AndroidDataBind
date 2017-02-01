package br.com.infoterras.bindapplication.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Gustavo on 22/11/2016.
 */

public class ServiceGenerator {

    private static final String API_BASE_URL = "https://api.github.com";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
                                                    .baseUrl(API_BASE_URL)
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .addConverterFactory(ScalarsConverterFactory.create());

    static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
