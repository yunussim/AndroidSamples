package yunussimulya.gmail.com.mysample.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import yunussimulya.gmail.com.mysample.R;

public class SimpleMVVMActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_mvvm);

        tv = findViewById(R.id.tvNumber);
        Button bIncrease = findViewById(R.id.bIncrease);

        CounterViewModel vm = ViewModelProviders.of(this).get(CounterViewModel.class);
        vm.counter.observe(this, integer -> {
            tv.setText(integer.toString());
        });
        bIncrease.setOnClickListener(view -> {
            vm.increaseCounter();
        });
    }
}
