package adityagaonkar.elearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.internetcheck.CheckInternetWebService;

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
                CheckInternetWebService.checkInternetConnection(new CheckInternetWebService.CheckInternetWebServiceListener() {
                    @Override
                    public void didCompleteRequest(Boolean isInternetPresent, AppError appError) {
                        textViewNetStatus.setText(isInternetPresent ? "Internet present" : "No internet connection");
                    }
                });
            }
        });
    }
}
