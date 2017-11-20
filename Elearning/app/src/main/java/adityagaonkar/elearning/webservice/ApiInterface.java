package adityagaonkar.elearning.webservice;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nikhil on 4/12/17.
 */

public interface ApiInterface {

    @GET("/Service.svc/Utility/CheckInternetConnection")
    Call<BaseResponse> checkInternetConnection();

    /*
    //Authentication
    @Headers( "Content-Type: application/json" )
    @POST("/Service.svc/CreateUser")
    Call<CreateUserResponse> createUser(@Body CreateUserRequest createUserRequest);*/

}
