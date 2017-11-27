package adityagaonkar.elearning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adityagaonkar.elearning.manager.AuthenticationManager;
import adityagaonkar.elearning.utility.ProgressBarUtil;
import adityagaonkar.elearning.webservice.AppError;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");

        buttonLogin = findViewById(R.id.button_login);
        editTextEmail = findViewById(R.id.login_edit_email);
        editTextPassword = findViewById(R.id.login_edit_password);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    login();
                }
            }
        });
    }

    private boolean validate() {
        if(editTextEmail.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!editTextEmail.getText().toString().contains("@")){
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void login(){
        ProgressBarUtil.show(this, "Logging in..");
        AuthenticationManager.getInstance().login(MainActivity.this, editTextEmail.getText().toString(), editTextPassword.getText().toString(), new AuthenticationManager.AuthenticationManagerListener() {
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
