package yunussimulya.gmail.com.mysample.activity.simple;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import yunussimulya.gmail.com.mysample.R;
import yunussimulya.gmail.com.mysample.network.ApacheConnection;
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