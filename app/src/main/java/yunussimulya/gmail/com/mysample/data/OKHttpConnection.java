package yunussimulya.gmail.com.mysample.data;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Request;
import yunussimulya.gmail.com.mysample.model.ConnectionResult;

/**
 * Created by Yunus on 3/11/2018.
 */

public class OKHttpConnection {

    private static final int timeoutConnection = 10000;
    private static final int timeoutSocket = 10000;

    private static final int longTimeoutConnection = 180000;
    private static final int longTimeoutSocket = 180000;

    public static Request GET(String url, HashMap<String, String> headers) {
        Request.Builder result = new Request.Builder()
                .url(url)
                .get();
        if (headers != null) {
            for (String key : headers.keySet()) {
                result.addHeader(key, headers.get(key));
            }
        }
        return result.build();
    }

}
