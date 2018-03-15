package yunussimulya.gmail.com.mysample.mvp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yunussimulya.gmail.com.mysample.R;
import yunussimulya.gmail.com.mysample.data.PostmanfitClient;
import yunussimulya.gmail.com.mysample.listener.PostmanfitInterface;

public class MvpLoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText edUsername;
    private EditText edPassword;
    private Button bLogin;
    private LoginPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        bLogin = findViewById(R.id.bLogin);
        progressBar = findViewById(R.id.progressBar);

        presenter = new LoginPresenter(this);

        bLogin.setOnClickListener(v -> {
            presenter.validateCredentials(edUsername.getText().toString(), edPassword.getText().toString());
        });

        hideProgress();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError(String error) {
        edUsername.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        edPassword.setError(error);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(bLogin, message, Snackbar.LENGTH_SHORT).show();
    }
}
