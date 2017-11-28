package adityagaonkar.elearning.webservice.courses;

import android.content.Context;
import android.util.Log;

import java.util.List;

import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.model.CourseDetail;
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

public class GetCourseDetailsWebService extends BaseWebService {

    public static void getCourseDetails(Context context, Integer courseId, final CourseDetailsWebServiceListener courseDetailsWebServiceListener){

        ApiInterface apiService = ApiClient.getClientWithTokenHeader(context).create(ApiInterface.class);
        Call<CourseDetail> call = apiService.getCourseDetails(courseId);
        call.enqueue(new Callback<CourseDetail>() {

            @Override
            public void onResponse(Call<CourseDetail> call, Response<CourseDetail> response) {
                Log.d("courseDetail", response.toString());
                courseDetailsWebServiceListener.didCompleteRequest(response.body(), null);
            }

            @Override
            public void onFailure(Call<CourseDetail> call, Throwable t) {
                Log.d("courseDetail","failure");
                AppError error = new AppError(0, "error");
                courseDetailsWebServiceListener.didCompleteRequest(null, error);
            }
        });
    }

    public interface CourseDetailsWebServiceListener{
        void didCompleteRequest(CourseDetail courseDetail, AppError appError);
    }
}
