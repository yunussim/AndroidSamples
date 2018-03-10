package yunussimulya.gmail.com.mysample.data;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

import yunussimulya.gmail.com.mysample.model.ConnectionResult;

/**
 * Created by Yunus on 3/11/2018.
 */

public class ApacheConnection {

    private static final int timeoutConnection = 10000;
    private static final int timeoutSocket = 10000;

    private static final int longTimeoutConnection = 180000;
    private static final int longTimeoutSocket = 180000;

    private static ConnectionResult GETBase(String url, ArrayList<BasicNameValuePair> headers, int timeoutSocket, int timeoutConnection) {
        ConnectionResult result;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpClient client = new DefaultHttpClient(httpParameters);
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response;
        try {
            if (headers != null) {
                for (NameValuePair header : headers) {
                    httpGet.setHeader(header.getName(), header.getValue());
                }
            }
            response = client.execute(httpGet);
            result = getResult(response);
        } catch (IOException e) {
            e.printStackTrace();
            result = resultTimeOut();
        }
        return result;
    }

    public static ConnectionResult GET(String sub_url, ArrayList<BasicNameValuePair> headers) {
        return GETBase(sub_url, headers, timeoutSocket, timeoutConnection);
    }

    public static ConnectionResult GETLongTimeout(String sub_url, ArrayList<BasicNameValuePair> headers) {
        return GETBase(sub_url, headers, longTimeoutSocket, longTimeoutConnection);
    }

    private static ConnectionResult POSTBase(String url, HttpEntity ent, ArrayList<BasicNameValuePair> headers, int timeoutSocket, int timeoutConnection) {
        ConnectionResult result;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpClient client=new DefaultHttpClient(httpParameters);
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response;
        try {
            if (headers != null) {
                for (NameValuePair header : headers) {
                    httpPost.setHeader(header.getName(), header.getValue());
                }
            }
            if (ent != null) {
                httpPost.setEntity(ent);
            }
            response = client.execute(httpPost);
            result = getResult(response);
        } catch (IOException e) {
            e.printStackTrace();
            result = resultTimeOut();
        }
        return result;
    }

    public static ConnectionResult POST(String sub_url, HttpEntity ent, ArrayList<BasicNameValuePair> headers) {
        return POSTBase(sub_url, ent, headers, timeoutSocket, timeoutConnection);
    }

    public static ConnectionResult POSTLongTimeout(String sub_url, HttpEntity ent, ArrayList<BasicNameValuePair> headers) {
        return POSTBase(sub_url, ent, headers, longTimeoutSocket, longTimeoutConnection);
    }

    private static ConnectionResult PUTBase(String url, HttpEntity ent, ArrayList<BasicNameValuePair> headers, int timeoutSocket, int timeoutConnection) {
        ConnectionResult result;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpClient client=new DefaultHttpClient(httpParameters);
        HttpPut httpPut = new HttpPut(url);
        HttpResponse response;
        try {
            if (headers != null) {
                for (NameValuePair header : headers) {
                    httpPut.setHeader(header.getName(), header.getValue());
                }
            }
            if (ent != null) {
                httpPut.setEntity(ent);
            }
            response = client.execute(httpPut);
            result = getResult(response);
        } catch (IOException e) {
            e.printStackTrace();
            result = resultTimeOut();
        }
        return result;
    }

    public static ConnectionResult PUT(String sub_url, HttpEntity ent, ArrayList<BasicNameValuePair> headers) {
        return PUTBase(sub_url, ent, headers, timeoutSocket, timeoutConnection);
    }

    public static ConnectionResult PUTLongTimeout(String sub_url, HttpEntity ent, ArrayList<BasicNameValuePair> headers) {
        return PUTBase(sub_url, ent, headers, longTimeoutSocket, longTimeoutConnection);
    }

    public static ConnectionResult DELETE(String url, ArrayList<BasicNameValuePair> headers) {
        ConnectionResult result;
        HttpClient httpclient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        try {
            if (headers != null) {
                for (NameValuePair header : headers) {
                    httpDelete.setHeader(header.getName(), header.getValue());
                }
            }
            HttpResponse response = httpclient.execute(httpDelete);
            result = getResult(response);
        } catch (Exception e) {
            e.printStackTrace();
            httpDelete.abort();
            httpclient.getConnectionManager().shutdown();
            result = resultTimeOut();
        } finally {
            httpDelete.abort();
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }

    private static ConnectionResult getResult(HttpResponse response) {
        ConnectionResult result = new ConnectionResult();
        try {
            if (response != null) {
                int statusCode = response.getStatusLine().getStatusCode();
                result.setStatusCode(statusCode);
                Header[] head = response.getAllHeaders();

                for (Header header : head) {
                    result.addHeader(header.getName(), header.getValue());
                }
                result.setError(false);
                result.setResponseMessage(response.getStatusLine().getReasonPhrase());
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String content = EntityUtils.toString(entity, "UTF-8");
                    result.setContent(content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static ConnectionResult resultTimeOut() {
        ConnectionResult result = new ConnectionResult();
        result.setStatusCode(-1);
        result.setError(true);
        result.setResponseMessage("Unable to contact server");
        result.setContent(null);
        return result;
    }

}
