package adityagaonkar.elearning.webservice.authentication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 11/21/17.
 */

public class LoginRequest {

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
