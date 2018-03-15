package yunussimulya.gmail.com.mysample.mvp;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yunussimulya.gmail.com.mysample.data.PostmanfitClient;
import yunussimulya.gmail.com.mysample.listener.PostmanfitInterface;

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
        PostmanfitInterface client = PostmanfitClient.getClient().create(PostmanfitInterface.class);
        client.login(username, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                try {
                    JSONObject object = new JSONObject(result);
                    JSONObject form = object.getJSONObject("form");
                    String resUsername = form.getString("username");
                    view.showMessage("Halo, " + resUsername);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                view.showMessage("Fail to contact the server");
            }
        });

    }
}
