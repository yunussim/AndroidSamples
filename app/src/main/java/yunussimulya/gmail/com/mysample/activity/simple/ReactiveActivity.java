package yunussimulya.gmail.com.mysample.activity.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.PrimitiveIterator;
import java.util.stream.IntStream;

import io.reactivex.Observable;
import yunussimulya.gmail.com.mysample.R;

public class ReactiveActivity extends AppCompatActivity {

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
    }
}
