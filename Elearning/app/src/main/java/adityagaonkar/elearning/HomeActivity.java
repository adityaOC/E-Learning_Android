package adityagaonkar.elearning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.courses.GetCoursesWebService;

/**
 * Created by Nikhil on 11/27/17.
 */

public class HomeActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView =  findViewById(R.id.text_view_response);

        findViewById(R.id.button_get_courses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetCoursesWebService.getCourses(HomeActivity.this, new GetCoursesWebService.LoginWebServiceListener() {
                    @Override
                    public void didCompleteRequest(List<Course> courses, AppError appError) {
                        if(courses != null){
                            textView.setText(courses.toString());
                        }
                    }
                });
            }
        });
    }
}
