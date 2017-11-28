package adityagaonkar.elearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import adityagaonkar.elearning.model.Course;
import adityagaonkar.elearning.model.Video;

/**
 * Created by Nikhil on 11/27/17.
 */

public class VideoListAdapter extends BaseAdapter {
    private List<Video> videoList;
    private Context context;
    private LayoutInflater layoutInflater;

    public VideoListAdapter(List<Video> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public Video getItem(int position) {
        return videoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.video_list_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.textViewName = convertView.findViewById(R.id.video_list_item_text_title);
            viewHolder.textViewDescription = convertView.findViewById(R.id.video_list_item_text_description);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Video video = videoList.get(position);
        viewHolder.textViewName.setText(video.getName());
        viewHolder.textViewDescription.setText(video.getDescription());

       /* List<ImageDetail> images = goodsObject.getObjectImages();
        if(!images.isEmpty()){
            Picasso.with(context).load(images.get(0).getUrl()).fit().centerCrop().into(viewHolder.imageViewObject);
        }*/

        return convertView;
    }

    class ViewHolder {
        TextView textViewName, textViewDescription;
        ImageView imageView;
    }
}
