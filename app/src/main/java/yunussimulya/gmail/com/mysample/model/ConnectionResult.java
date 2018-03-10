package yunussimulya.gmail.com.mysample.model;

import org.apache.http.HttpResponse;

import java.util.HashMap;

/**
 * Created by Yunus on 3/11/2018.
 */

public class ConnectionResult {

    private int statusCode = 0;
    private String content = "";
    private String responseMessage = "";
    private HashMap<String, String> headers = new HashMap<>();
    private boolean error = false;

    public ConnectionResult(int statusCode, String content, String responseMessage, HashMap<String, String> headers, boolean error) {
        this.statusCode = statusCode;
        this.content = content;
        this.responseMessage = responseMessage;
        this.headers = headers;
        this.error = error;
    }

    public ConnectionResult() {
        this.statusCode = -1;
        this.content = "";
        this.responseMessage = "";
        this.error = true;
        this.headers = new HashMap<>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContent() {
        return content;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public boolean isError() {
        return error;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public String toString() {
        return content != null ? content : responseMessage != null ? responseMessage : "";
    }
}
