package adityagaonkar.elearning;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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

    VideoView video_player_view;
    DisplayMetrics dm;
    MediaController media_Controller;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        setTitle("Video Details");

        textViewVideoName = findViewById(R.id.video_detail_text_name);
        textViewVideoDescription = findViewById(R.id.video_detail_text_description);

        textViewVideoName.setText(video.getVideoName());
        textViewVideoDescription.setText(video.getVideoDescription());
        getInit();
    }

    public void getInit() {
        video_player_view = findViewById(R.id.video_player_view);
        media_Controller = new MediaController(this);
        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        video_player_view.setMinimumWidth(width);
        video_player_view.setMinimumHeight(height);
        video_player_view.setMediaController(media_Controller);
        Uri uri = Uri.parse("android.resource://adityagaonkar.elearning/" + R.raw.temp);
        video_player_view.setVideoURI(uri);
        video_player_view.start();
    }

}
