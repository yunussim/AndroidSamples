package yunussimulya.gmail.com.mysample.activity.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.reactivestreams.Subscription;

import java.util.PrimitiveIterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import yunussimulya.gmail.com.mysample.R;

public class ReactiveActivity extends AppCompatActivity {


    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reactive);

        Button bIterator = findViewById(R.id.bIterator);
        bIterator.setOnClickListener(v -> {
            Observable
                    .range(1, 10)
                    .subscribe(integer -> Log.e("obser", integer + ""));
        });

        Button bInterval = findViewById(R.id.bInterval);
        bInterval.setOnClickListener(v -> {
            disposable = Observable.interval(1, TimeUnit.SECONDS).subscribe(aLong -> {
                Log.e("tick", "" + aLong);
            }, throwable -> {
                Log.e("error", "aye");
            }, () -> {
                Log.e("complete", "aye");
            });
        });
    }

    @Override
    protected void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }
}
