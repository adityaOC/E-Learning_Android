package adityagaonkar.elearning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 11/27/17.
 */

public class Video {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("video_name")
    @Expose
    private String videoName;
    @SerializedName("video_link")
    @Expose
    private String videoLink;
    @SerializedName("video_description")
    @Expose
    private String videoDescription;

    public String getCourse_thumbnail_url() {
        return course_thumbnail_url;
    }

    public void setCourse_thumbnail_url(String course_thumbnail_url) {
        this.course_thumbnail_url = course_thumbnail_url;
    }

    @SerializedName("course_thumbnail_url")
    @Expose

    private String course_thumbnail_url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }
}
