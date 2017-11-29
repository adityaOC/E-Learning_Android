package adityagaonkar.elearning.webservice.authentication;

import android.util.Log;

import adityagaonkar.elearning.webservice.ApiClient;
import adityagaonkar.elearning.webservice.ApiInterface;
import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.BaseWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikhil on 11/21/17.
 */

public class RegisterWebService extends BaseWebService {

    public static void register(RegisterRequest registerRequest, final RegisterWebServiceListener registerWebServiceListener){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RegisterResponse> call = apiService.register(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {

            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.d("register", response.toString());
                registerWebServiceListener.didCompleteRequest(response.body());
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.d("register","failure");
                AppError error = new AppError(0, "error");
                RegisterResponse registerResponse = new RegisterResponse();
                registerResponse.setError(error);
                registerWebServiceListener.didCompleteRequest(registerResponse);
            }
        });
    }

    public interface RegisterWebServiceListener {
        void didCompleteRequest(RegisterResponse response);
    }
}
