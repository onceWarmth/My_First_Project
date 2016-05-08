package com.example.andy.seg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clover on 2016/3/2.
 */
public class Syllabus_main extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private ArrayAdapter<String>arr_adapter;
    private SimpleAdapter simp_Adapter;
    private List<Map<String,Object>> cataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus_main);

        String[]arr_date ={"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        listView = (ListView) findViewById(R.id.syl_list);
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_date);
        listView.setAdapter(arr_adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            String data = "星期一";
            Intent intent = new Intent(Syllabus_main.this, Syllabus_monday.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        } else if (position == 1) {
            String data = "星期二";
            Intent intent = new Intent(Syllabus_main.this, Syllabus_monday.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        } else if (position == 2) {
            String data = "星期三";
            Intent intent = new Intent(Syllabus_main.this, Syllabus_monday.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        } else if (position == 3) {
            String data = "星期四";
            Intent intent = new Intent(Syllabus_main.this, Syllabus_monday.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        } else if (position == 4) {
            String data = "星期五";
            Intent intent = new Intent(Syllabus_main.this, Syllabus_monday.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        } else if (position == 5) {
            String data = "星期六";
            Intent intent = new Intent(Syllabus_main.this, Syllabus_monday.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        } else if (position == 6) {
            String data = "星期日";
            Intent intent = new Intent(Syllabus_main.this, Syllabus_monday.class);
            intent.putExtra("extra_data", data);
            startActivity(intent);
        }
        finish();
    }
}
