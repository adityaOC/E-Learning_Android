package adityagaonkar.elearning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adityagaonkar.elearning.manager.CourseManager;
import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.utility.ProgressBarUtil;
import adityagaonkar.elearning.webservice.AppError;

/**
 * Created by Nikhil on 11/27/17.
 */

public class HomeActivity extends AppCompatActivity {

    private CourseListAdapter courseListAdapter;
    private List<Course> courseList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Courses");

        ListView listView = findViewById(R.id.course_list_view);
        courseListAdapter = new CourseListAdapter(courseList, this);
        listView.setAdapter(courseListAdapter);

        getCourses();
    }

    private void getCourses(){
        ProgressBarUtil.show(this, "Fetching courses..");
        CourseManager.getInstance().getCourses(HomeActivity.this, new CourseManager.GetCoursesManagerListener() {
            @Override
            public void onSuccess(List<Course> courses) {
                ProgressBarUtil.dismiss();
                courseList.clear();
                courseList.addAll(courses);
                courseListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(AppError error) {
                ProgressBarUtil.dismiss();
                Toast.makeText(HomeActivity.this, "Error fetching courses", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
