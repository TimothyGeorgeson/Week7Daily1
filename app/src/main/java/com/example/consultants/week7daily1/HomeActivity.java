package com.example.consultants.week7daily1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvTime = findViewById(R.id.tvTime);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        //each second, run on main thread to update UI
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //get value from tvTime, subtract 1, and set the value back again
                                int time = Integer.parseInt(tvTime.getText().toString());
                                tvTime.setText(String.valueOf(time - 1));

                                //when count reaches 0, log user out
                                if (time - 1 == 0) {
                                    finish();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();
    }


    //clicking button resets timer to 60 (since user is active)
    public void onActive(View view) {
        tvTime.setText("60");
    }
}
