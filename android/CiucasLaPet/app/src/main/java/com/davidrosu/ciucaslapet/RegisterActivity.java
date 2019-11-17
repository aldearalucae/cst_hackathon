package com.davidrosu.ciucaslapet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerAction (View view) {
        String username = ((EditText)findViewById(R.id.editText19)).getText().toString();
        String password = ((EditText)findViewById(R.id.editText20)).getText().toString();
        String firstname = ((EditText)findViewById(R.id.editText21)).getText().toString();
        String lastname = ((EditText)findViewById(R.id.editText22)).getText().toString();
        String weight = ((EditText)findViewById(R.id.editText23)).getText().toString();
        String age = ((EditText)findViewById(R.id.editText24)).getText().toString();
        String gender = ((EditText)findViewById(R.id.editText25)).getText().toString();

        String string = "{";

        string += "\"username\":\"";
        string += username;
        string += "\",";

        string += "\"password\":\"";
        string += password;
        string += "\",";

        string += "\"firstname\":\"";
        string += firstname;
        string += "\",";

        string += "\"lastname\":\"";
        string += lastname;
        string += "\",";

        string += "\"weight\":\"";
        string += weight;
        string += "\",";

        string += "\"age\":\"";
        string += age;
        string += "\",";

        string += "\"gender\":\"";
        string += gender;
        string += "\"";

        string += "}";

        RegisterCall registerCall = new RegisterCall();
        registerCall.execute(string);

        super.onBackPressed();
    }
}
