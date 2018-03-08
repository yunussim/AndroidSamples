package yunussimulya.gmail.com.mysample.databinding;

/**
 * Created by Yunus on 3/8/2018.
 */

public class ProgressActivityContract {

    public interface Presenter {
        void onShowData(Progress progress);
    }

    public interface View {
        void showData(Progress progress);
    }

}
