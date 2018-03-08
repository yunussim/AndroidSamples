package yunussimulya.gmail.com.mysample.databinding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import yunussimulya.gmail.com.mysample.R;

public class ProgressActivity extends AppCompatActivity implements ProgressActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityProgressBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_progress);
        ProgressActivityPresenter presenter = new ProgressActivityPresenter(this, getApplicationContext());
        Progress progress = new Progress("Yunus", 10);
        binding.setPresenter(presenter);
        binding.setProgress(progress);
    }

    @Override
    public void showData(Progress progress) {
        String progressText = progress.getProgressText();
        Toast.makeText(this, progressText, Toast.LENGTH_SHORT).show();
    }

}
