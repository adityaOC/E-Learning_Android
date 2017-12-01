package adityagaonkar.elearning.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 11/27/17.
 */

public class Course {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("course_author")
    @Expose
    private String courseAuthor;
    @SerializedName("course_avegrage_ratings")
    @Expose
    private Float courseAvegrageRatings;

    @SerializedName("course_thumbnail_url")
    @Expose
    private String course_thumbnail_url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAuthor() {
        return courseAuthor;
    }

    public void setCourseAuthor(String courseAuthor) {
        this.courseAuthor = courseAuthor;
    }

    public Float getCourseAvegrageRatings() {
        return courseAvegrageRatings;
    }

    public void setCourseAvegrageRatings(Float courseAvegrageRatings) {
        this.courseAvegrageRatings = courseAvegrageRatings;
    }

    public String getCourse_thumbnail_url() {
        return course_thumbnail_url;
    }

    public void setCourse_thumbnail_url(String course_thumbnail_url) {
        this.course_thumbnail_url = course_thumbnail_url;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseAuthor=" + courseAuthor +
                ", courseAvegrageRatings=" + courseAvegrageRatings +
                '}';
    }
}
