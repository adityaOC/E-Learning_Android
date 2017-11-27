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
    private Integer courseAuthor;
    @SerializedName("course_avegrage_ratings")
    @Expose
    private Double courseAvegrageRatings;


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

    public Integer getCourseAuthor() {
        return courseAuthor;
    }

    public void setCourseAuthor(Integer courseAuthor) {
        this.courseAuthor = courseAuthor;
    }

    public Double getCourseAvegrageRatings() {
        return courseAvegrageRatings;
    }

    public void setCourseAvegrageRatings(Double courseAvegrageRatings) {
        this.courseAvegrageRatings = courseAvegrageRatings;
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
