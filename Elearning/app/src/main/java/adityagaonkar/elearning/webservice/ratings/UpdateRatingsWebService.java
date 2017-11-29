package adityagaonkar.elearning.webservice.ratings;

import android.util.Log;

import adityagaonkar.elearning.webservice.ApiClient;
import adityagaonkar.elearning.webservice.ApiInterface;
import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.BaseWebService;
import adityagaonkar.elearning.webservice.authentication.LoginRequest;
import adityagaonkar.elearning.webservice.authentication.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikhil on 11/21/17.
 */

public class UpdateRatingsWebService extends BaseWebService {

    public static void updateRatings(RatingUpdateRequest ratingUpdateRequest, Integer courseId, final UpdateRatingsWebServiceListener updateRatingsWebServiceListener){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RatingUpdateResponse> call = apiService.updateRatings(courseId, ratingUpdateRequest);
        call.enqueue(new Callback<RatingUpdateResponse>() {

            @Override
            public void onResponse(Call<RatingUpdateResponse> call, Response<RatingUpdateResponse> response) {
                Log.d("ratingsUpdate", response.toString());
                updateRatingsWebServiceListener.didCompleteRequest(response.body());
            }

            @Override
            public void onFailure(Call<RatingUpdateResponse> call, Throwable t) {
                Log.d("ratingsUpdate","failure");
                AppError error = new AppError(0, "error");
                RatingUpdateResponse ratingUpdateResponse = new RatingUpdateResponse();
                ratingUpdateResponse.setError(error);
                updateRatingsWebServiceListener.didCompleteRequest(ratingUpdateResponse);
            }
        });
    }

    public interface UpdateRatingsWebServiceListener{
        void didCompleteRequest(RatingUpdateResponse response);
    }
}
