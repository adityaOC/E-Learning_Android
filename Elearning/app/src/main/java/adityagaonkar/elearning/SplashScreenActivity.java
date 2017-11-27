package adityagaonkar.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import adityagaonkar.elearning.manager.SharedPrefsManager;

/**
 * Created by Nikhil on 11/27/17.
 */

public class SplashScreenActivity extends AppCompatActivity {

    public static final int SPLASH_SCREEN_TIME_OUT = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                if(SharedPrefsManager.readToken(SplashScreenActivity.this) != null){ //check if already logged in
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                }else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
