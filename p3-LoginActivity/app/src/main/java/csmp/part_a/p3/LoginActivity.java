package csmp.part_a.p3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MAX_ATTEMPTS = 2;
    int attempts;

    private String username;
    private String password;

    Button signInButton;
    EditText signInUsernameField, signInPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInUsernameField = (EditText) findViewById(R.id.signInUsernameEditText);
        signInPasswordField = (EditText) findViewById(R.id.signInPasswordEditText);
        signInButton = (Button) findViewById(R.id.signInButton);

        attempts = 0;

        getValueFromUserBundle();

        signInButton.setOnClickListener(this);
    }

    private void getValueFromUserBundle() { //getting values from bundles
        username = getIntent().getExtras().getString(Constants.USERNAME_KEY);
        password = getIntent().getExtras().getString(Constants.PASSWORD_KEY);
    }


    @Override
    public void onClick(View view) {
        attempts++;
        if (attempts > MAX_ATTEMPTS) {
            makeToast("Failed Login Attempts");
            signInButton.setEnabled(false);
        } else {
            String usernameLocal, passwordLocal;
            usernameLocal = signInUsernameField.getText().toString();
            passwordLocal = signInPasswordField.getText().toString();

            if (usernameLocal.equals(username) && passwordLocal.equals(password))
                makeToast("Successful Login");
            else makeToast("Login Failed");
        }
    }

    public void makeToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}