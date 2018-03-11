package yunussimulya.gmail.com.mysample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yunussimulya.gmail.com.mysample.R;
import yunussimulya.gmail.com.mysample.data.DataHandler;
import yunussimulya.gmail.com.mysample.data.OKHttpConnection;

public class OKHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        Button bCall = findViewById(R.id.bCall);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRequest();
            }
        });
    }

    private void makeRequest() {
        String url = "https://www.jualo.com/categories.json";
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", DataHandler.getB64Auth("jualo", "railsrails"));

        Request request = OKHttpConnection.GET(url, headers);
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body() != null ? response.body().string() : "";
                Log.e("" + response.code(), result);
            }
        });
    }
}
