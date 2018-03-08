package yunussimulya.gmail.com.mysample.databinding;

import android.content.Context;

/**
 * Created by Yunus on 3/8/2018.
 */

public class ProgressActivityPresenter implements ProgressActivityContract.Presenter {

    private ProgressActivityContract.View view;
    private Context context;

    public ProgressActivityPresenter(ProgressActivityContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void onShowData(Progress progress) {
        view.showData(progress);
    }
}
