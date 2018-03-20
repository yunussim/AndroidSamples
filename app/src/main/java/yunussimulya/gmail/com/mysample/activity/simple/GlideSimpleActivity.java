package yunussimulya.gmail.com.mysample.activity.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import yunussimulya.gmail.com.mysample.R;

public class GlideSimpleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_simple);

        Button bCall = findViewById(R.id.bCall);
        final ImageView ivImage = findViewById(R.id.ivImage);
        bCall.setOnClickListener(v -> {
            Glide.with(getApplicationContext())
                    .load("http://via.placeholder.com/300.png")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivImage);
        });
    }
}
