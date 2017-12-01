package adityagaonkar.elearning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    private EditText editTextSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Courses");

        editTextSearch = findViewById(R.id.home_edit_search);
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    search();
                }
                return false;
            }
        });


        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });

        findViewById(R.id.button_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefsManager.writeToken(HomeActivity.this, null);
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
        });

        findViewById(R.id.button_clear_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextSearch.setText("");
                dismissKeyboard();
                getCourses(null);
            }
        });

        ListView listView = findViewById(R.id.course_list_view);
        courseListAdapter = new CourseListAdapter(courseList, this);
        listView.setAdapter(courseListAdapter);
        listView.setOnItemClickListener(this);

        getCourses(null);
    }

    private void search() {
        dismissKeyboard();
        if(!editTextSearch.getText().toString().isEmpty()) {
            getCourses(editTextSearch.getText().toString());
        }
    }

    private void dismissKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getCourses(String searchText){
        ProgressBarUtil.show(this, "Fetching courses..");
        CourseManager.getInstance().getCourses(HomeActivity.this, searchText, new CourseManager.GetCoursesManagerListener() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:

                getCourses(null);
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }

}
