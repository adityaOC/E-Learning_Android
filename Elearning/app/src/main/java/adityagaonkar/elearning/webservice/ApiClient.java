package adityagaonkar.elearning.webservice;


import android.content.Context;

import java.io.IOException;

import adityagaonkar.elearning.manager.SharedPrefsManager;
import okhttp3.Interceptor;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikhil on 4/12/17.
 */

public class ApiClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofitWithHeader = null;

    public static final String BASE_URL_LIVE = "http://10.0.2.2:8000/api/";
    public static final String BASE_URL_MOCK = "https://private-0bb5ef-getcourses1.apiary-mock.com/";

    public static final String BASE_URL = BASE_URL_LIVE;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientWithTokenHeader(final Context context){
        if (retrofitWithHeader==null) {

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.networkInterceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    String token = SharedPrefsManager.readToken(context);
                    Request request = original.newBuilder()
                            .header("Authorization", token == null ? "" : "Token " + token)
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            });

            OkHttpClient client = httpClientBuilder.build();
            retrofitWithHeader = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitWithHeader;
    }

}
