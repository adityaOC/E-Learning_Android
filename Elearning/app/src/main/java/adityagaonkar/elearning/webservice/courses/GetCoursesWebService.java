package adityagaonkar.elearning.webservice.courses;

import android.content.Context;
import android.util.Log;

import java.util.List;

import adityagaonkar.elearning.model.Course;
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

public class GetCoursesWebService extends BaseWebService {
    public static void getCourses(Context context, final LoginWebServiceListener loginWebServiceListener){

        ApiInterface apiService = ApiClient.getClientWithTokenHeader(context).create(ApiInterface.class);
        Call<List<Course>> call = apiService.getCourses();
        call.enqueue(new Callback<List<Course>>() {

            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Log.d("loginResponse", response.toString());
                loginWebServiceListener.didCompleteRequest(response.body(), null);
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d("loginResponse","failure");
                AppError error = new AppError(0, "error");
                loginWebServiceListener.didCompleteRequest(null, error);
            }
        });
    }

    public interface LoginWebServiceListener{
        void didCompleteRequest(List<Course> courses, AppError appError);
    }
}
