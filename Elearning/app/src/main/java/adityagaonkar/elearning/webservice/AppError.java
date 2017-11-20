package adityagaonkar.elearning.webservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 4/14/17.
 */

public class AppError {
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("description")
    @Expose
    private String description;

    public AppError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
