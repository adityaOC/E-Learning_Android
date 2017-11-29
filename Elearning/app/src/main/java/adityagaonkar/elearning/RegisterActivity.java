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

public class RegisterActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextEmail, editTextFirstName, editTextLastName, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        buttonRegister = findViewById(R.id.button_sign_up);
        editTextEmail = findViewById(R.id.register_edit_email);
        editTextFirstName = findViewById(R.id.register_edit_first_name);
        editTextLastName = findViewById(R.id.register_edit_last_name);
        editTextPassword = findViewById(R.id.register_edit_password);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    register();
                }
            }
        });
    }

    private boolean validate() {
        if(editTextEmail.getText().toString().isEmpty() || editTextFirstName.getText().toString().isEmpty() || editTextLastName.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!editTextEmail.getText().toString().contains("@")){
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void register(){
        ProgressBarUtil.show(this, "Registering..");
        AuthenticationManager.getInstance().register(RegisterActivity.this, editTextEmail.getText().toString(), editTextFirstName.getText().toString(), editTextLastName.getText().toString(), editTextPassword.getText().toString(), new AuthenticationManager.AuthenticationManagerListener() {
            @Override
            public void onSuccess() {
                ProgressBarUtil.dismiss();
                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(AppError error) {
                ProgressBarUtil.dismiss();
                Toast.makeText(RegisterActivity.this, "Registration failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
