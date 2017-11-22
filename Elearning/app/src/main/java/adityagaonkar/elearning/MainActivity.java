package adityagaonkar.elearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import adityagaonkar.elearning.manager.AuthenticationManager;
import adityagaonkar.elearning.utility.ProgressBarUtil;
import adityagaonkar.elearning.webservice.AppError;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textViewNetStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textViewNetStatus = findViewById(R.id.text_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*CheckInternetWebService.checkInternetConnection(new CheckInternetWebService.CheckInternetWebServiceListener() {
                    @Override
                    public void didCompleteRequest(Boolean isInternetPresent, AppError appError) {
                        textViewNetStatus.setText(isInternetPresent ? "Internet present" : "No internet connection");
                    }
                });*/

                login();

            }
        });
    }

    private void login(){
        ProgressBarUtil.show(this, "Logging in..");
        AuthenticationManager.getInstance().login("apiuser@user.com", "Awesome1", new AuthenticationManager.AuthenticationManagerListener() {
            @Override
            public void onSuccess(String token) {
                ProgressBarUtil.dismiss();
                Toast.makeText(MainActivity.this, "Login success: "+ token, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(AppError error) {
                ProgressBarUtil.dismiss();
                Toast.makeText(MainActivity.this, "Login failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
