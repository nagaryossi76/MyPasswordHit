package nytech.co.il.hit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainLogActivity extends Activity {
    protected EditText etUserName, etPassword, etEmail;
    protected Button btLogin;
    protected TextView txtRegister;
    private static final String MYTAG = "my_debug_tag";
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
            ParseUser currentUser = ParseUser.getCurrentUser();
            if(currentUser != null)

            {
                // do stuff with the user
                Intent intent = new Intent(MainLogActivity.this, MainScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            else
            {
        setContentView(R.layout.activity_login);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etLogPassword);
        txtRegister = (TextView) findViewById(R.id.txtSignin);
        btLogin = (Button) findViewById(R.id.btLogin);


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();


                ParseUser.logInInBackground(userName, password, new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent intent = new Intent(MainLogActivity.this, MainScreen.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainLogActivity.this);
                            builder.setMessage(e.getMessage())
                                    .setTitle(R.string.signin_error_title)
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                });
            }

        });


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainLogActivity.this, Signin.class));
            }
        });
    }


    }
}
