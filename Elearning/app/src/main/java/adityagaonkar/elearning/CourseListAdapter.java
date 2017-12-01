package adityagaonkar.elearning;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import adityagaonkar.elearning.model.Course;

/**
 * Created by Nikhil on 11/27/17.
 */

public class CourseListAdapter extends BaseAdapter {
    private List<Course> courseList;
    private Context context;
    private LayoutInflater layoutInflater;

    public CourseListAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Course getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.courses_list_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.textViewName = convertView.findViewById(R.id.course_list_item_text_title);
            viewHolder.textViewAuthor = convertView.findViewById(R.id.course_list_item_text_author);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.ratingBar = convertView.findViewById(R.id.ratingBar);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Course course = courseList.get(position);
        viewHolder.textViewName.setText(course.getCourseName());
        viewHolder.textViewAuthor.setText(course.getCourseAuthor());
        viewHolder.ratingBar.setRating(course.getCourseAvegrageRatings());
        Picasso.with(context).load(course.getCourse_thumbnail_url()).into(viewHolder.imageView);

       /* List<ImageDetail> images = goodsObject.getObjectImages();
        if(!images.isEmpty()){
            Picasso.with(context).load(images.get(0).getUrl()).fit().centerCrop().into(viewHolder.imageViewObject);
        }*/

        return convertView;
    }

    class ViewHolder {
        TextView textViewName, textViewAuthor;
        ImageView imageView;
        RatingBar ratingBar;
    }
}
