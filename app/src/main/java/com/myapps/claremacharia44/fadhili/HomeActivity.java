package com.myapps.claremacharia44.fadhili;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    // Alert Dialog Manager
    AlertDIalogManager alert = new AlertDIalogManager();

    // Session Manager Class
    SessionManagement session;

    // Button Logout
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        session = new SessionManagement(getApplicationContext());


        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManagement.KEY_NAME);

        // login_id
        String email = user.get(SessionManagement.KEY_ID);


    }
}
