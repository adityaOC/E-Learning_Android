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

public class LoginWebService extends BaseWebService {
    public static void login(LoginRequest loginRequest, final LoginWebServiceListener loginWebServiceListener){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("loginResponse", response.toString());
                loginWebServiceListener.didCompleteRequest(response.body());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("loginResponse","failure");
                AppError error = new AppError(0, "error");
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setError(error);
                loginWebServiceListener.didCompleteRequest(loginResponse);
            }
        });
    }

    public interface LoginWebServiceListener{
        void didCompleteRequest(LoginResponse response);
    }
}
