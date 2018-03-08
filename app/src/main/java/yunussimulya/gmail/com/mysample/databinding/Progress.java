package yunussimulya.gmail.com.mysample.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import yunussimulya.gmail.com.mysample.BR;

/**
 * Created by Yunus on 3/8/2018.
 */

public class Progress extends BaseObservable {

    private String name = "";
    private String progressText = "";
    private int progress = 0;

    public Progress(String name, int progress) {
        this.name = name;
        this.progress = progress;
        progressText = progress + "";
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public int getProgress() {
        return progress;
    }

    @Bindable
    public String getProgressText() {
        return progressText;
    }

    public void setProgressText(String progressText) {
        this.progressText = progressText;
        notifyPropertyChanged(BR.progressText);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        notifyPropertyChanged(BR.progress);
    }
}
