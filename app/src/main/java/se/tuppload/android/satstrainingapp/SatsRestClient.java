package se.tuppload.android.satstrainingapp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class SatsRestClient {

    private static final String DB_URL = "https://api.parse.com/1/classes/activities";
    private static final String SATS_API_URL = "https://api2.sats.com/v1.0/se/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("?include", "bookingId.class,subType");
        client.addHeader("Content-Type", "application/json");
        client.addHeader("X-Parse-Application-Id", "23p8xhISFQKfAfDa0kdS8NYnuKwiXHolJmXWLMyi");
        client.addHeader("X-Parse-REST-API-Key", "fKgzdx8dze90xyzlMY8e5uLcry6bT131ixcPcUfr");
        client.get(DB_URL + params, responseHandler);
    }

    public static void get(String relativeUrl, AsyncHttpResponseHandler responseHandler) {
        client.addHeader("Content-Type", "application/json");
        client.get(getAbsoluteUrl(relativeUrl), responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return SATS_API_URL + relativeUrl;
    }

}