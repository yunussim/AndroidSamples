package yunussimulya.gmail.com.mysample.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

import yunussimulya.gmail.com.mysample.R;
import yunussimulya.gmail.com.mysample.data.ApacheConnection;
import yunussimulya.gmail.com.mysample.model.ConnectionResult;

public class OldApacheActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_apache);

        Button bCall = findViewById(R.id.bCall);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApacheTask("https://jsonplaceholder.typicode.com/users").execute();
            }
        });
    }

    public static class ApacheTask extends AsyncTask<Void, Integer, ConnectionResult> {

        private String url;

        private ApacheTask(String url) {
            this.url = url;
        }

        @Override
        protected ConnectionResult doInBackground(Void... voids) {
            return ApacheConnection.GET(url, null);
        }

        @Override
        protected void onPostExecute(ConnectionResult s) {
            super.onPostExecute(s);
            Log.e("content", s.toString());
        }
    }


}