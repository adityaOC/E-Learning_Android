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
    private CourseDetail courseDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        setTitle("Detail");

        textViewCourseName = findViewById(R.id.course_detail_text_title);
        textViewAuthor = findViewById(R.id.course_detail_text_author);
        ratingBar = findViewById(R.id.course_detail_rating_bar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                if(rating != courseDetail.getCourse_avegrage_ratings()) {

                    ProgressBarUtil.show(CourseDetailActivity.this, "Updating..");
                    CourseManager.getInstance().updateRatings(CourseDetailActivity.this, courseId, rating, new CourseManager.UpdateRatingsManagerListener() {
                        @Override
                        public void onSuccess() {
                            ProgressBarUtil.dismiss();
                            Toast.makeText(CourseDetailActivity.this, "Ratings updated", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(AppError error) {
                            ProgressBarUtil.dismiss();
                            Toast.makeText(CourseDetailActivity.this, "Failed to update Ratings", Toast.LENGTH_SHORT).show();
                            getCourseDetails();
                        }
                    });
                }
            }
        });

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

                CourseDetailActivity.this.courseDetail = courseDetail;

                textViewCourseName.setText(courseDetail.getName());
                textViewAuthor.setText(String.format("%s %s", courseDetail.getCourseAuthorList().get(0).getFirstName(), courseDetail.getCourseAuthorList().get(0).getLastName()));
                /*if(!courseDetail.getCourseRatings().isEmpty()) {
                    //ratingBar.setRating(courseDetail.getCourseRatings().get(0).getRating());

                }*/

                    ratingBar.setRating(courseDetail.getCourse_avegrage_ratings());

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
