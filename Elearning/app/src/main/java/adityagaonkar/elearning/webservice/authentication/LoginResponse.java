package adityagaonkar.elearning.webservice.authentication;

import com.google.gson.annotations.SerializedName;

import adityagaonkar.elearning.webservice.BaseResponse;

/**
 * Created by Nikhil on 11/21/17.
 */

public class LoginResponse extends BaseResponse {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
