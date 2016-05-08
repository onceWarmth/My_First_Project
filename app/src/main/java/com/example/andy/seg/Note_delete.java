package com.example.andy.seg;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Andy on 2016/4/6.
 */
public class Note_delete extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_delete);

        bt1 = (Button) findViewById(R.id.delete_yes2);

        Intent intent = getIntent();
        final String po = intent.getStringExtra("extra_position");
        final int position = Integer.valueOf(po);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaryDbAdapter diaryDb = new DiaryDbAdapter(context);
                diaryDb.open();
                Cursor cursor = diaryDb.getAllNotes();
                if (cursor.moveToFirst()) {
                    int i = 0;
                    do {
                        if (i == position) {
                            long ID = cursor.getInt(cursor.getColumnIndex("_id"));
                            diaryDb.shanchuDiary(ID);
                            Toast.makeText(Note_delete.this, "删除完成", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(Note_delete.this, Note_main.class);
                            startActivity(in);
                        }
                        i++;

                    } while (cursor.moveToNext());
                }
                cursor.close();
                diaryDb.close();
                finish();
            }
        });

        bt2 = (Button) findViewById(R.id.delete_no2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
