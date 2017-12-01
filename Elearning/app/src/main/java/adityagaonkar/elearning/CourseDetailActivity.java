package adityagaonkar.elearning;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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

        findViewById(R.id.course_detail_text_add_rating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CourseDetailActivity.this);
                final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater.inflate(R.layout.custom_rating_dialog, null);
                dialogBuilder.setView(dialogView);

                //final EditText editText = (EditText) dialogView.findViewById(R.id.custom_dialog_edit_text);
                final RatingBar ratingBar = dialogView.findViewById(R.id.custom_dialog_rating_bar);

                dialogBuilder.setTitle("Rate this course");
                dialogBuilder.setMessage("");
                dialogBuilder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        updateUserRating(ratingBar.getRating());
                    }
                });
                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });
                AlertDialog b = dialogBuilder.create();
                b.show();
            }
        });

        ListView listView = findViewById(R.id.video_list_view);
        videoListAdapter = new VideoListAdapter(videoList, this);
        listView.setAdapter(videoListAdapter);

        listView.setOnItemClickListener(this);

        getCourseDetails();
    }

    private void updateUserRating(float rating) {
        ProgressBarUtil.show(CourseDetailActivity.this, "Updating..");
        CourseManager.getInstance().updateRatings(CourseDetailActivity.this, courseId, rating, new CourseManager.UpdateRatingsManagerListener() {
            @Override
            public void onSuccess() {
                ProgressBarUtil.dismiss();
                Toast.makeText(CourseDetailActivity.this, "Ratings updated", Toast.LENGTH_SHORT).show();
                getCourseDetails();
            }

            @Override
            public void onFailure(AppError error) {
                ProgressBarUtil.dismiss();
                Toast.makeText(CourseDetailActivity.this, "Failed to update Ratings", Toast.LENGTH_SHORT).show();
            }
        });
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

                if(!courseDetail.getCourseAuthorList().isEmpty()) {
                    textViewAuthor.setText(String.format("%s %s", courseDetail.getCourseAuthorList().get(0).getFirstName(), courseDetail.getCourseAuthorList().get(0).getLastName()));
                }
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
