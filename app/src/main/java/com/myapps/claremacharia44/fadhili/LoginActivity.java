package com.myapps.claremacharia44.fadhili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btn_loginLogin,btn_signupLogin;
    private EditText emailLogin,passLogin;
    private TextView googleSignup;


    // Alert Dialog Manager
    AlertDIalogManager alert = new AlertDIalogManager();

    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_loginLogin = findViewById(R.id.btn_loginLogin);
        btn_signupLogin = findViewById(R.id.btn_signupLogin);
        emailLogin = findViewById(R.id.text_emailLogin);
        passLogin = findViewById(R.id.text_passLogin);

        btn_loginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                // Get username, password from EditText
                String username = emailLogin.getText().toString();
                String password = passLogin.getText().toString();

                // Check if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    if(username.equals(emailLogin.getText()) && password.equals
                            (passLogin.getText())) {
                        /*creating a session for fetched data (GET)*/
                        session.createLoginSession("", "");


                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        // username / password doesn't match
                        alert.showAlertDialog(LoginActivity.
                                this, "Login failed..",
                                "Username/Password is incorrect", false);
                    }
                }else{
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    alert.showAlertDialog(LoginActivity.this,
                            "Login failed..", "Please enter username and password", false);
                }



                }


        });
        btn_signupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Login Successful", Toast.LENGTH_SHORT).show();

                // Start NewActivity.class
                Intent myIntent = new Intent(LoginActivity.this,
                        SignupActivity.class);
                startActivity(myIntent);

            }
        });

    }
}
