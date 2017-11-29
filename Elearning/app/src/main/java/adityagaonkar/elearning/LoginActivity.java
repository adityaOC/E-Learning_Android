package adityagaonkar.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adityagaonkar.elearning.manager.AuthenticationManager;
import adityagaonkar.elearning.utility.ProgressBarUtil;
import adityagaonkar.elearning.webservice.AppError;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogin, buttonSignup;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        buttonLogin = findViewById(R.id.button_login);
        editTextEmail = findViewById(R.id.login_edit_email);
        editTextPassword = findViewById(R.id.login_edit_password);
        buttonSignup = findViewById(R.id.button_sign_up);
        buttonLogin.setOnClickListener(this);
        buttonSignup.setOnClickListener(this);
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
        AuthenticationManager.getInstance().login(LoginActivity.this, editTextEmail.getText().toString(), editTextPassword.getText().toString(), new AuthenticationManager.AuthenticationManagerListener() {
            @Override
            public void onSuccess() {
                ProgressBarUtil.dismiss();
                Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            }

            @Override
            public void onFailure(AppError error) {
                ProgressBarUtil.dismiss();
                Toast.makeText(LoginActivity.this, "Login failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login : {
                if(validate()) {
                    login();
                }
            }
            break;

            case R.id.button_sign_up : {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
            break;
        }
    }
}
