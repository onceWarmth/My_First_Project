package com.example.andy.seg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by clover on 2016/3/4.
 */
public class Syllabus_choose extends AppCompatActivity {

    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus_choose);

        bt1 = (Button) findViewById(R.id.choose_bt1);
        bt2 = (Button) findViewById(R.id.choose_bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Syllabus_choose.this, Syllabus_add_course.class);
                startActivity(intent);
                finish();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Syllabus_choose.this, Syllabus_add_task.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
