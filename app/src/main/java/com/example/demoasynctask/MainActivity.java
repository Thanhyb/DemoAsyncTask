package com.example.demoasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mtxtShow;
    private Button mbtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtxtShow = (TextView) findViewById(R.id.txt_show);
        mbtnStart = (Button) findViewById(R.id.btn_start);

        mbtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask myTask = new MyTask(MainActivity.this, mtxtShow, mbtnStart);
                myTask.execute();
                mbtnStart.setEnabled(false);
            }
        });
    }
}
