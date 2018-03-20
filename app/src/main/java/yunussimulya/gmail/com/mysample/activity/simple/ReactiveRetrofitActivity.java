package yunussimulya.gmail.com.mysample.activity.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import yunussimulya.gmail.com.mysample.R;
import yunussimulya.gmail.com.mysample.listener.TmdbApiService;
import yunussimulya.gmail.com.mysample.model.MovieResponse;
import yunussimulya.gmail.com.mysample.network.TmdbClient;

public class ReactiveRetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactive_retrofit);
        Button bCall = findViewById(R.id.bCall);
        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
    }

    private void call() {
        TmdbApiService api = TmdbClient.getClient().create(TmdbApiService.class);
        Observable<MovieResponse> obj = api.getObservableTopRated(TmdbClient.API_KEY);
        obj.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieResponse -> {
                    Log.e("response", movieResponse.getMovies().get(0).getTitle());
                });
    }
}
