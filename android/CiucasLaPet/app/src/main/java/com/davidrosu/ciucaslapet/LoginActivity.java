package com.davidrosu.ciucaslapet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginAction (View view) {
        String username = ((EditText)findViewById(R.id.editText)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText2)).getText().toString();

        String string = "{";

        string += "\"username\":\"";
        string += username;
        string += "\",";

        string += "\"password\":\"";
        string += password;
        string += "\"";

        string += "}";

        LoginCall loginCall = new LoginCall();

        try {
            String e = loginCall.execute(string).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (loginCall.success) {
            HomeActivity.username = username;
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);

        } else {

            Context context = getApplicationContext();
            CharSequence text = "Login failed!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
