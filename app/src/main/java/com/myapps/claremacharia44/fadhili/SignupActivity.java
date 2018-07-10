package com.myapps.claremacharia44.fadhili;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class SignupActivity extends AppCompatActivity {
    public Button  btnloginSignup, btnsignupSignup;
    public EditText txtemailSignup, txtfnameSignup,txtlnameSignup, txtpassword1Signup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        btnloginSignup = findViewById(R.id.btn_loginSignup);
        btnsignupSignup =  findViewById(R.id.btn_signupSignup);
        txtemailSignup = findViewById(R.id.text_emailSignup);
        txtfnameSignup = findViewById(R.id.text_fnameSignup);
        txtlnameSignup = findViewById(R.id.text_lnameSignup);
        txtpassword1Signup = findViewById(R.id.text_passwordSignup);




        btnloginSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start NewActivity.class
                Intent myIntent = new Intent(SignupActivity.this,
                        LoginActivity.class);
                startActivity(myIntent);

            }
        });

    }

}