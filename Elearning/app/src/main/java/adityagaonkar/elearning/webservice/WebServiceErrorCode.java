package adityagaonkar.elearning.webservice;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import adityagaonkar.elearning.R;

/**
 * Created by Nikhil on 4/15/17.
 */

public enum WebServiceErrorCode {


    @SerializedName("-2")
    HTTP_ERROR_NO_RESPONSE(-2),
    @SerializedName("-1")
    HTTP_ERROR_UNKNOWN_ERROR(-1),
    @SerializedName("0")
    HTTP_ERROR_NOT_REACHABLE(0),

    ERROR_REST_CLIENT(-3),
    ERROR_NO_INTERNET_CONNECTION(-4);


    private int _value;

    WebServiceErrorCode(int Value) {
        this._value = Value;
    }

    public int getValue() {
        return _value;
    }

    public static WebServiceErrorCode fromInt(int intValue) {
        for (WebServiceErrorCode webServiceErrorCode : WebServiceErrorCode.values()) {
            if (webServiceErrorCode.getValue() == intValue) { return webServiceErrorCode; }
        }
        return HTTP_ERROR_UNKNOWN_ERROR;
    }

    public static String getErrorMessageForCode(Context context, int code){
        WebServiceErrorCode webServiceErrorCode = fromInt(code);

        if (webServiceErrorCode == null) {
            return context.getString(R.string.error_web_service_failure_default);
        }
        switch (webServiceErrorCode) {
            case HTTP_ERROR_NO_RESPONSE:
            case HTTP_ERROR_NOT_REACHABLE:
            case ERROR_NO_INTERNET_CONNECTION:
                return context.getString(R.string.error_message_no_connectivity);

            default:
                return context.getString(R.string.error_web_service_failure_default);
        }
    }
}
