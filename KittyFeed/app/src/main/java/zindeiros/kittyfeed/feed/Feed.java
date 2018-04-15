package zindeiros.kittyfeed.feed;

import android.content.Context;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

import zindeiros.kittyfeed.utils.RESTUtils;

/**
 * Created by joaom on 14/04/2018.
 */

public final class Feed {
    /**
     * Please do not enter
     */
    private static final String HARDCODED_FEED_URL="http://172.29.1.229:5000/Feed";
    /**
     * Please do not enter
     */
    private static final String HARDCODED_SET_URL="http://172.29.1.229:5000/set";

    /**
     * Activates the Feed Me Now functionality
     * @param context Context with the context from where the Feed Me Now is activated
     */
    public static void feedMeNow(Context context){
        try {
            RESTUtils.postJSON(HARDCODED_FEED_URL, createFeedMeNowJSON(), context);
        }catch (JSONException|UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }
    }
    public static void feedMeLater(Context context,String jsonLater){
        try{
            RESTUtils.postJSON(HARDCODED_SET_URL,jsonLater,context);
        }catch(JSONException|UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Creates a JSON that targets the Feed Me Now functionality
     * @return String with the JSON for the Feed Me Now functionality
     */
    private static String createFeedMeNowJSON(){
        return "{\n\t\"feed\" : \"true\"\n}";
    }
}
