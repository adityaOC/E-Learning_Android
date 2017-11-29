package adityagaonkar.elearning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class VideoDetailActivity extends AppCompatActivity {

    public static Video video;
    private TextView textViewVideoName, textViewVideoDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        setTitle("Video Details");

        textViewVideoName = findViewById(R.id.video_detail_text_name);
        textViewVideoDescription = findViewById(R.id.video_detail_text_description);

        textViewVideoName.setText(video.getVideoName());
        textViewVideoDescription.setText(video.getVideoDescription());
    }
}
