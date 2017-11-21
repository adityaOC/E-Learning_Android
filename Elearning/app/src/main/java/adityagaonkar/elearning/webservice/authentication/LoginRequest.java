package adityagaonkar.elearning.webservice.authentication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 11/21/17.
 */

public class LoginRequest {

    @SerializedName("Username")
    private String userName;

    @SerializedName("Password")
    private String password;

    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
