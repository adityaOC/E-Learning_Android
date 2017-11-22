package adityagaonkar.elearning.utility;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressBarUtil {

    private static ProgressDialog progressDialog;
    private static boolean isDisplayed = false;

    public static void show(Context context, String message) {
        if(context != null) {
            if (isDisplayed) {
                update(message);
            } else {
                try {
                    progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage(message);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    isDisplayed = true;
                } catch (IllegalArgumentException e) {  // FIX : ava.lang.IllegalArgumentException; View not attached to window manager / crash when trying to operate on progressDialog
                    e.printStackTrace();
                }
            }
        }
    }

    public static void update(String message) {
        try {
            if(progressDialog != null) {
                progressDialog.setMessage(message);
            }
        }catch (IllegalArgumentException e){  // FIX : ava.lang.IllegalArgumentException; View not attached to window manager / crash when trying to operate on progressDialog
            e.printStackTrace();
        }
    }

    public static void dismiss() {
        if(isDisplayed) {
            isDisplayed = false;
            try {
                if(progressDialog != null) {
                    if(progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }catch (IllegalArgumentException e){  // FIX : ava.lang.IllegalArgumentException; View not attached to window manager / crash when trying to operate on progressDialog
                e.printStackTrace();
            }
        }
    }
}
