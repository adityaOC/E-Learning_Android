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
    @SerializedName("courseAuthor")
    @Expose
    private List<CourseAuthor> courseAuthorList;
    @SerializedName("course_avegrage_ratings")
    @Expose
    private Float course_avegrage_ratings;
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

    public List<CourseAuthor> getCourseAuthorList() {
        return courseAuthorList;
    }

    public void setCourseAuthorList(List<CourseAuthor> courseAuthorList) {
        this.courseAuthorList = courseAuthorList;
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

    public Float getCourse_avegrage_ratings() {
        return course_avegrage_ratings;
    }

    public void setCourse_avegrage_ratings(Float course_avegrage_ratings) {
        this.course_avegrage_ratings = course_avegrage_ratings;
    }
}
