package nytech.co.il.hit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Signin extends Activity {
    protected EditText etUserName, etRegPassword, etEmail;
    protected Button btSignin;
    protected TextView txtLogin;
    private static final String MYTAG = "my_debug_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etRegPassword = (EditText)findViewById(R.id.etRegPassWord);
        btSignin = (Button)findViewById(R.id.btRegister);
        txtLogin = (TextView)findViewById(R.id.txtLogin);

        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String password = etRegPassword.getText().toString();

                String email = etEmail.getText().toString();
                userName = userName.trim();
                password = password.trim();
                email = email.trim();

                if(userName.isEmpty() || password.isEmpty() || email.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Signin.this);
                    builder.setMessage(R.string.signin_error_message)
                            .setTitle(R.string.signin_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    //create new User
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(userName);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                //Success
                                Intent intent = new Intent(Signin.this ,MainScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                //Fail
                                AlertDialog.Builder builder = new AlertDialog.Builder(Signin.this);
                                builder.setMessage(e.getMessage())
                                        .setTitle(R.string.signin_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });


                }
            }
        });




        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this, MainLogActivity.class));
            }
        });




    }
}
