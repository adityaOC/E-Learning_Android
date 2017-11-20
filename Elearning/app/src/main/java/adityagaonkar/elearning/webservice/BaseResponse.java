package adityagaonkar.elearning.webservice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil on 4/14/17.
 */

public class BaseResponse {

        @SerializedName("error")
        private AppError error;

        public AppError getError() {
            return error;
        }

        public void setError(AppError error) {
            this.error = error;
        }

}
