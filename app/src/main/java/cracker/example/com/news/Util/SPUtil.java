package cracker.example.com.news.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cracker on 2017/5/20.
 */

public class SPUtil {
    private static final String FIRST_SP = "FIRST_SP";
    private final static String FIRST_RUN ="FIRST_RUN";


    public static boolean getIsFirstRun(Context context){
        SharedPreferences sp = context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        return sp.getBoolean(FIRST_RUN,true);
    }

    public static void setIsFirstRun(Context context,boolean b){
        SharedPreferences sp = context.getSharedPreferences(FIRST_RUN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(FIRST_RUN,b);
        editor.commit();
    }
}
