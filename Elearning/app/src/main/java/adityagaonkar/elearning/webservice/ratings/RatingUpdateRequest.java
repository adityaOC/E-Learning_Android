package adityagaonkar.elearning.webservice.ratings;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 11/21/17.
 */

public class RatingUpdateRequest {

    @SerializedName("rating_give_by_user")
    private Float rating;

    public RatingUpdateRequest(Float rating) {
        this.rating = rating;
    }
}
