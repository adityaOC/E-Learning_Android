package adityagaonkar.elearning.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nikhil on 11/27/17.
 */

public class SharedPrefsManager {
    private static final String USER_CREDENTIALS = Package.class + "USER_CREDENTIALS";
    private static final String KEY_TOKEN = Package.class + "KEY_TOKEN";

    public static void writeToken(Context context, String token){
        SharedPreferences sharedPref = context.getSharedPreferences(USER_CREDENTIALS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public static String readToken(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(USER_CREDENTIALS, Context.MODE_PRIVATE);
        return sharedPref.getString(KEY_TOKEN, null);
    }
}
