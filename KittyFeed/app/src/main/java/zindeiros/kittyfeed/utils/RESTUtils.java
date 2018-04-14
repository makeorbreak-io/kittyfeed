package zindeiros.kittyfeed.utils;


import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by joaom on 13/04/2018.
 */

public final class RESTUtils {
    /**
     * Global HTTP Client
     */
    private static final AsyncHttpClient GLOBAL_CLIENT=new AsyncHttpClient();
    /**
     * JSON Type for HTTP Requests
     */
    private static final String JSON_CONTENT_TYPE="application/json";

    /**
     * Sends a POST with a JSON content
     * <br>Prints on console the success of the POST
     * @param url String with the REST URL
     * @param json String with the JSON to be sent
     * @param context Context with the content from where the REST is going to be sent
     * @throws JSONException Throws JSONException if JSON is invalid/unformatted
     * @throws UnsupportedEncodingException Throws UnsupportedEncodingException if JSON encoding is not supported
     */
    public static void postJSON(String url,String json,Context context) throws JSONException, UnsupportedEncodingException {
        JSONObject jsonObject=new JSONObject(json);
        StringEntity entity=new StringEntity(jsonObject.toString());
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,JSON_CONTENT_TYPE));
        GLOBAL_CLIENT.post(context, url, entity, JSON_CONTENT_TYPE, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("Success -> "+statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Failure -> "+statusCode);
            }
        });
    }
}
