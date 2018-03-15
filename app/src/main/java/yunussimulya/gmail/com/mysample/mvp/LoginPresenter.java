package yunussimulya.gmail.com.mysample.mvp;

import android.support.annotation.NonNull;
import android.text.TextUtils;

/**
 * Created by Yunus on 3/15/2018.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(@NonNull LoginContract.View view) {
        this.view = view;

    }

    @Override
    public void validateCredentials(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            view.setUsernameError("Username harus diisi");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            view.setPasswordError("Password harus diisi");
            return;
        }
        view.showSuccess();
    }
}
