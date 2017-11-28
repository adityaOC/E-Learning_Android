package adityagaonkar.elearning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nikhil on 11/28/17.
 */

public class CourseDetail {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("course_name")
    @Expose
    private String name;
    @SerializedName("course_author")
    @Expose
    private String author;
    @SerializedName("course_videos")
    @Expose
    private List<Video> videos = null;
    @SerializedName("course_ratings")
    @Expose
    private List<CourseRating> courseRatings = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<CourseRating> getCourseRatings() {
        return courseRatings;
    }

    public void setCourseRatings(List<CourseRating> courseRatings) {
        this.courseRatings = courseRatings;
    }
}
