package adityagaonkar.elearning.webservice.internetcheck;

import android.util.Log;

import adityagaonkar.elearning.webservice.ApiClient;
import adityagaonkar.elearning.webservice.ApiInterface;
import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.BaseResponse;
import adityagaonkar.elearning.webservice.BaseWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikhil on 05/05/17.
 */

public class CheckInternetWebService extends BaseWebService {

    public static void checkInternetConnection(final CheckInternetWebServiceListener checkInternetWebServiceListener){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BaseResponse> call = apiService.checkInternetConnection();
        call.enqueue(new Callback<BaseResponse>() {

            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.d("Check Internet", response.toString());
                checkInternetWebServiceListener.didCompleteRequest(response.isSuccessful() , null);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                checkInternetWebServiceListener.didCompleteRequest(false, getErrorFromThrowable(t));
            }
        });
    }

    public interface CheckInternetWebServiceListener{
        void didCompleteRequest(Boolean isInternetPresent, AppError appError);
    }
}
