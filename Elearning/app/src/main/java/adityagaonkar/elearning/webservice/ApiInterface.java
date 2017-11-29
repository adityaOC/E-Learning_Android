package adityagaonkar.elearning.webservice;

import java.util.List;

import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.model.CourseDetail;
import adityagaonkar.elearning.webservice.authentication.LoginRequest;
import adityagaonkar.elearning.webservice.authentication.LoginResponse;
import adityagaonkar.elearning.webservice.authentication.RegisterRequest;
import adityagaonkar.elearning.webservice.authentication.RegisterResponse;
import adityagaonkar.elearning.webservice.ratings.RatingUpdateRequest;
import adityagaonkar.elearning.webservice.ratings.RatingUpdateResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Nikhil on 4/12/17.
 */

public interface ApiInterface {

    @GET("/Service.svc/Utility/CheckInternetConnection")
    Call<BaseResponse> checkInternetConnection();

    //Authentication
    @Headers( "Content-Type: application/json" )
    @POST("loginR/")
    Call<LoginResponse> login(@Body LoginRequest createUserRequest);

    @Headers( "Content-Type: application/json" )
    @POST("register/")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    //get courses
    @GET("getAllCourses/")
    Call<List<Course>> getCourses();

    @GET("courseDetail/{courseId}/")
    Call<CourseDetail> getCourseDetails(@Path("courseId") Integer courseId);

    @Headers( "Content-Type: application/json" )
    @POST("ratings/{courseId}/update/")
    Call<RatingUpdateResponse> updateRatings(@Path("courseId") Integer courseId, @Body RatingUpdateRequest ratingUpdateRequest);

}
