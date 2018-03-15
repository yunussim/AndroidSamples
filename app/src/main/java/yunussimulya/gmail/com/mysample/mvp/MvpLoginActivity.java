package yunussimulya.gmail.com.mysample.mvp;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import yunussimulya.gmail.com.mysample.R;

public class MvpLoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText edUsername;
    private EditText edPassword;
    private Button bLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_login);

        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        bLogin = findViewById(R.id.bLogin);
        presenter = new LoginPresenter(this);

        bLogin.setOnClickListener(v -> {
            presenter.validateCredentials(edUsername.getText().toString(), edPassword.getText().toString());
            Log.e("login", "clicked");
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
    public void showSuccess() {
        Snackbar.make(bLogin, "Sukses", Snackbar.LENGTH_SHORT).show();
    }
}
