package adityagaonkar.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adityagaonkar.elearning.manager.CourseManager;
import adityagaonkar.elearning.manager.SharedPrefsManager;
import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.utility.ProgressBarUtil;
import adityagaonkar.elearning.webservice.AppError;

/**
 * Created by Nikhil on 11/27/17.
 */

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private CourseListAdapter courseListAdapter;
    private List<Course> courseList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Courses");

        findViewById(R.id.button_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsManager.writeToken(HomeActivity.this, null);
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        ListView listView = findViewById(R.id.course_list_view);
        courseListAdapter = new CourseListAdapter(courseList, this);
        listView.setAdapter(courseListAdapter);
        listView.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Course course = courseList.get(i);
        CourseDetailActivity.courseId = course.getId();
        startActivity(new Intent(HomeActivity.this, CourseDetailActivity.class));
    }
}
