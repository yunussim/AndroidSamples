package yunussimulya.gmail.com.mysample.mvp;

/**
 * Created by Yunus on 3/15/2018.
 */

public interface LoginContract {

    interface View {

        void showProgress();

        void hideProgress();

        void setUsernameError(String error);

        void setPasswordError(String error);

        void showSuccess();

    }

    interface Presenter {

        void validateCredentials(String username, String password);

    }

}
