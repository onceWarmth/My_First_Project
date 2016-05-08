package com.example.andy.seg;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andy on 2016/4/5.
 */
public class Syllabus_delete extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus_delete);

        Intent intent = getIntent();
        final String data = intent.getStringExtra("extra_data");
        final String po = intent.getStringExtra("extra_position");
        Log.i("exp", po);
        final int position = Integer.valueOf(po);

        dbHelper = new MyDatabaseHelper(this, "UserStore.db", null, 1);

//        Log.i("exp", "3333333333333333333333333");

        bt1 = (Button) findViewById(R.id.delete_yes);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Syllabus", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int i = 0;
                    do {
                        String week = cursor.getString(cursor.getColumnIndex("week"));
                        String title = cursor.getString(cursor.getColumnIndex("title"));
                        String content = cursor.getString(cursor.getColumnIndex("content"));
                        String text = cursor.getString(cursor.getColumnIndex("time"));
                        int ID = cursor.getInt(cursor.getColumnIndex("id"));

                        if (week.equals(data)) {
                            if (i == position) {
                                String id = "" + ID;
                                db.delete("Syllabus", "id=?", new String[]{id});
                                Toast.makeText(Syllabus_delete.this, "删除完成", Toast.LENGTH_SHORT).show();
                            }
                            i++;
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
                Intent in = new Intent(Syllabus_delete.this, Syllabus_main.class);
                startActivity(in);
                finish();
            }
        });

        bt2 = (Button) findViewById(R.id.delete_no);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
