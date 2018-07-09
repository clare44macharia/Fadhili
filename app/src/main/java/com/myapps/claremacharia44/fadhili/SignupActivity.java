package com.myapps.claremacharia44.fadhili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {
    private Button btn_signupSignup,btn_loginSignup;
    private EditText text_fnameSignup,text_lnameSignup,text_emailSignup,phoneNumber,
            text_password1Signup,text_password2Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_loginSignup = (Button) findViewById(R.id.btn_loginSignup);
        btn_signupSignup = (Button) findViewById(R.id.btn_signupSignup);
        text_emailSignup = findViewById(R.id.text_emailSignup);
        text_fnameSignup = findViewById(R.id.text_fnameSignup);
        text_lnameSignup = findViewById(R.id.text_lnameSignup);
        text_password1Signup = findViewById(R.id.text_passwordSignup);


        btn_loginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start NewActivity.class
                Intent myIntent = new Intent(SignupActivity.this,
                        LoginActivity.class);
                startActivity(myIntent);

            }
        });



        btn_signupSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}