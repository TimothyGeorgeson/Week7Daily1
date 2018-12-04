package com.example.consultants.week7daily1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginView extends AppCompatActivity {
    public static final String TAG = LoginView.class.getSimpleName() + "_TAG";
    public static final String PREF_NAME = "MyPref";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    public void onSignIn(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        SharedPreferences pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (pref.contains(email) && pref.getString(email, "xxx").equals(password)) {
            Toast.makeText(this, "Successful Sign In", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        if (pref.contains(email))
            Log.d(TAG, "onSignIn: pref is there");

        Log.d(TAG, "onSignIn: pref password: "+ pref.getString(email, "xxx"));
    }

    public void onSignUp(View view) {

        Intent intent = new Intent(this, SignUpView.class);
        intent.putExtra(EMAIL, etEmail.getText().toString());
        intent.putExtra(PASSWORD, etPassword.getText().toString());

        startActivity(intent);

    }
}
