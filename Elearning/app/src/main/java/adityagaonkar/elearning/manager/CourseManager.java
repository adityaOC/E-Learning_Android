package adityagaonkar.elearning.manager;

import android.content.Context;

import java.util.List;

import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.model.CourseDetail;
import adityagaonkar.elearning.webservice.AppError;
import adityagaonkar.elearning.webservice.courses.GetCourseDetailsWebService;
import adityagaonkar.elearning.webservice.courses.GetCoursesWebService;

/**
 * Created by Nikhil on 11/27/17.
 */

public class CourseManager {
    private static final CourseManager ourInstance = new CourseManager();

    public static CourseManager getInstance() {
        return ourInstance;
    }

    private CourseManager() {
    }

    public void getCourses(final Context context, final GetCoursesManagerListener getCoursesManagerListener){
        GetCoursesWebService.getCourses(context, new GetCoursesWebService.LoginWebServiceListener() {
            @Override
            public void didCompleteRequest(List<Course> courses, AppError appError) {
                if(courses != null){
                    getCoursesManagerListener.onSuccess(courses);
                }else {
                    getCoursesManagerListener.onFailure(appError);
                }
            }
        });
    }

    public interface GetCoursesManagerListener{
        void onSuccess(List<Course> courses);
        void onFailure(AppError error);
    }

    public void getCourseDetails(final Context context, Integer courseId, final GetCourseDetailsManagerListener getCourseDetailsManagerListener){

        GetCourseDetailsWebService.getCourseDetails(context, courseId, new GetCourseDetailsWebService.CourseDetailsWebServiceListener() {
            @Override
            public void didCompleteRequest(CourseDetail courseDetail, AppError appError) {
                if(courseDetail != null){
                    getCourseDetailsManagerListener.onSuccess(courseDetail);
                }else {
                    getCourseDetailsManagerListener.onFailure(appError);
                }
            }
        });
    }

    public interface GetCourseDetailsManagerListener{
        void onSuccess(CourseDetail courseDetail);
        void onFailure(AppError error);
    }
}
