package adityagaonkar.elearning.webservice.ratings;

import com.google.gson.annotations.SerializedName;

import adityagaonkar.elearning.webservice.BaseResponse;

/**
 * Created by Nikhil on 11/21/17.
 */

public class RatingUpdateResponse extends BaseResponse {

    @SerializedName("status")
    private Integer status;

    @SerializedName("Message")
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
