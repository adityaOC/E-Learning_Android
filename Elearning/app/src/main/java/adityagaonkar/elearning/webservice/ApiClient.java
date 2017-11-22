package adityagaonkar.elearning.webservice;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikhil on 4/12/17.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static final String BASE_URL = "http://10.0.2.2:8000/api/";

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
