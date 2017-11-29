package adityagaonkar.elearning.webservice.authentication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 11/21/17.
 */

public class RegisterRequest {

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("password")
    private String password;

    public RegisterRequest(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
