package com.example.andy.seg;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clover on 2016/3/4.
 */
public class Syllabus_add_task extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    private EditText et1,et2,et3;
    private MyDatabaseHelper dbHelper;
    private String week = "星期一";
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus_add_task);
        spinner = (Spinner) findViewById(R.id.spinner_task_week);
        //1、设置数据源
        list = new ArrayList<>();
        list.add("星期一");
        list.add("星期二");
        list.add("星期三");
        list.add("星期四");
        list.add("星期五");
        list.add("星期六");
        list.add("星期日");

        //2、新建ArrayAdapter （数组适配器）
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);

        //3、adapter设置一个下拉列表样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //4、spinner加载适配器
        spinner.setAdapter(adapter);

        //5、spinner设置监听器
        spinner.setOnItemSelectedListener(this);

        dbHelper = new MyDatabaseHelper(this, "UserStore.db", null, 1);

        bt = (Button) findViewById(R.id.add_bt2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1 = (EditText) findViewById(R.id.add_task_et1);
                et2 = (EditText) findViewById(R.id.add_task_et2);
                et3 = (EditText) findViewById(R.id.add_task_et3);

                String text1 = et1.getText().toString();
                String text2 = et2.getText().toString();
                String text3 = et3.getText().toString();

                ContentValues values = new ContentValues();
                values.put("title", text1);
                values.put("content", text2);
                values.put("time", text3);
                values.put("week", week);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.insert("Syllabus", null, values);

                Intent intent = new Intent(Syllabus_add_task.this, Syllabus_main.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        week = adapter.getItem(position);
        //String week = list.get(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
