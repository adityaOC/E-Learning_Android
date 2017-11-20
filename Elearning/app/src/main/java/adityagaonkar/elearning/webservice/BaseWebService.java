package adityagaonkar.elearning.webservice;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by Nikhil on 4/15/17.
 */

public class BaseWebService {

    protected static AppError getErrorFromThrowable(Throwable t) {
        if (t instanceof SocketException ||
                t instanceof UnknownHostException ||
                t instanceof SocketTimeoutException) {
            return new AppError(WebServiceErrorCode.ERROR_NO_INTERNET_CONNECTION.getValue(), t.getLocalizedMessage());
        } else {
            return new AppError(WebServiceErrorCode.ERROR_REST_CLIENT.getValue(), t.getLocalizedMessage());
        }
    }

}
