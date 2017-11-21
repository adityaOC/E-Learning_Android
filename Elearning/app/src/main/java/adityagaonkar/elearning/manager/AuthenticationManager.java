package adityagaonkar.elearning.manager;

import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.authentication.LoginRequest;
import adityagaonkar.elearning.webservice.authentication.LoginResponse;
import adityagaonkar.elearning.webservice.authentication.LoginWebService;

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

    public void login(String userName, String password, final AuthenticationManagerListener authenticationManagerListener){
        LoginRequest loginRequest = new LoginRequest(userName, password);
        LoginWebService.login(loginRequest, new LoginWebService.LoginWebServiceListener() {
            @Override
            public void didCompleteRequest(LoginResponse response) {
                if(response.getToken() != null){
                    //TODO: save token to shared pref
                    authenticationManagerListener.onSuccess();
                }else {
                    authenticationManagerListener.onFailure(new AppError(0, "Call Failed"));
                }
            }
        });
    }

    public interface AuthenticationManagerListener{
        void onSuccess();
        void onFailure(AppError error);
    }
}
