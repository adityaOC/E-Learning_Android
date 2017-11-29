package adityagaonkar.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adityagaonkar.elearning.manager.CourseManager;
import adityagaonkar.elearning.model.CourseDetail;
import adityagaonkar.elearning.model.Video;
import adityagaonkar.elearning.utility.ProgressBarUtil;
import adityagaonkar.elearning.webservice.AppError;

/**
 * Created by Nikhil on 11/28/17.
 */

public class CourseDetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static Integer courseId;

    private VideoListAdapter videoListAdapter;
    private List<Video> videoList = new ArrayList<>();
    private TextView textViewCourseName, textViewAuthor;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        setTitle("Detail");

        textViewCourseName = findViewById(R.id.course_detail_text_title);
        textViewAuthor = findViewById(R.id.course_detail_text_author);
        ratingBar = findViewById(R.id.course_detail_rating_bar);

        ListView listView = findViewById(R.id.video_list_view);
        videoListAdapter = new VideoListAdapter(videoList, this);
        listView.setAdapter(videoListAdapter);

        listView.setOnItemClickListener(this);

        getCourseDetails();
    }

    private void getCourseDetails() {
        ProgressBarUtil.show(this, "Please wait..");
        CourseManager.getInstance().getCourseDetails(this, courseId, new CourseManager.GetCourseDetailsManagerListener() {
            @Override
            public void onSuccess(CourseDetail courseDetail) {
                ProgressBarUtil.dismiss();
                videoList.clear();
                videoList.addAll(courseDetail.getVideos());
                videoListAdapter.notifyDataSetChanged();

                textViewCourseName.setText(courseDetail.getName());
                textViewAuthor.setText(courseDetail.getAuthor());
                if(!courseDetail.getCourseRatings().isEmpty()) {
                    ratingBar.setRating(courseDetail.getCourseRatings().get(0).getRating());
                }
            }

            @Override
            public void onFailure(AppError error) {
                ProgressBarUtil.dismiss();
                Toast.makeText(CourseDetailActivity.this, "Error fetching videos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        VideoDetailActivity.video = videoList.get(i);
        startActivity(new Intent(CourseDetailActivity.this, VideoDetailActivity.class));
    }
}
