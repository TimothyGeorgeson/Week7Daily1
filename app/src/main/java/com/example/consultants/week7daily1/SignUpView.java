package com.example.consultants.week7daily1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpView extends AppCompatActivity {
    public static final String TAG = SignUpView.class.getSimpleName() + "_TAG";

    private EditText etEmail;
    private EditText etPassword;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);

        //bind views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvError = findViewById(R.id.tvError);

        //get data from intent and set to edittexts
        etEmail.setText(getIntent().getStringExtra(LoginView.EMAIL));
        etPassword.setText(getIntent().getStringExtra(LoginView.PASSWORD));
    }

    public void onSignUp(View view) {

        if (!isValidEmail(etEmail.getText().toString())) {
            tvError.setText("Invalid email address");
            return;
        }
        if (!isValidPassword(etPassword.getText().toString())) {
            tvError.setText("Password must be length 4 or more and contain an Uppercase letter, Lowercase letter, and a Digit");
            return;
        }

        tvError.setText("");

        SharedPreferences pref = getSharedPreferences(LoginView.PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(etEmail.getText().toString(), etPassword.getText().toString());
        editor.apply();

        Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();

        finish();
    }

    public boolean isValidEmail(final String email) {

        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());

    }

    public boolean isValidPassword(final String password) {

        Log.d(TAG, "isValidPassword: " + password);

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{4,20})";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
