package adityagaonkar.elearning.manager;

import android.content.Context;

import java.util.List;

import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.webservice.AppError;
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
}
