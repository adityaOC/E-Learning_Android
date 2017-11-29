package adityagaonkar.elearning.manager;

import android.content.Context;

import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.authentication.LoginRequest;
import adityagaonkar.elearning.webservice.authentication.LoginResponse;
import adityagaonkar.elearning.webservice.authentication.LoginWebService;
import adityagaonkar.elearning.webservice.authentication.RegisterRequest;
import adityagaonkar.elearning.webservice.authentication.RegisterResponse;
import adityagaonkar.elearning.webservice.authentication.RegisterWebService;

/**
 * Created by Nikhil on 11/21/17.
 */

public class AuthenticationManager {
    private static final AuthenticationManager ourInstance = new AuthenticationManager();

    public static AuthenticationManager getInstance() {
        return ourInstance;
    }

    private AuthenticationManager() {
    }

    public void login(final Context context, String userName, String password, final AuthenticationManagerListener authenticationManagerListener){
        LoginRequest loginRequest = new LoginRequest(userName, password);
        LoginWebService.login(loginRequest, new LoginWebService.LoginWebServiceListener() {
            @Override
            public void didCompleteRequest(LoginResponse response) {
                if(response != null && response.getToken() != null){
                    // save token to shared pref
                    SharedPrefsManager.writeToken(context, response.getToken());
                    authenticationManagerListener.onSuccess();
                }else {
                    authenticationManagerListener.onFailure(new AppError(0, "Call Failed"));
                }
            }
        });
    }

    public void register(final Context context, String userName, String firstName, String lastName, String password, final AuthenticationManagerListener authenticationManagerListener){
        RegisterRequest registerRequest = new RegisterRequest(userName, firstName, lastName, password);
        RegisterWebService.register(registerRequest, new RegisterWebService.RegisterWebServiceListener() {
            @Override
            public void didCompleteRequest(RegisterResponse response) {
                if(response!=null && response.getError() == null && response.getId() != null){
                    authenticationManagerListener.onSuccess();
                }else {
                    authenticationManagerListener.onFailure(new AppError(0, "Registration failed"));
                }
            }
        });
    }


    public interface AuthenticationManagerListener{
        void onSuccess();
        void onFailure(AppError error);
    }
}
