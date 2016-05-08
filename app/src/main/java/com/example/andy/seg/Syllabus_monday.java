package com.example.andy.seg;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clover on 2016/3/2.
 */
public class Syllabus_monday extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> cataList;
    private Button bt;
    private MyDatabaseHelper dbHelper;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus_monday);

        Intent intent = getIntent();
        data = intent.getStringExtra("extra_data");

        cataList = new ArrayList<Map<String,Object>>();
        listView = (ListView) findViewById(R.id.listView_monday);
        simp_adapter = new SimpleAdapter(this, initCata(data), R.layout.syllabus_item, new String[]{"title", "content", "text"}, new int[]{R.id.syll_tv1, R.id.syll_tv2, R.id.syll_tv3});
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);


        bt = (Button) findViewById(R.id.mon_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Syllabus_monday.this, Syllabus_choose.class);
                startActivity(intent);
                finish();
            }
        });

//        dbHelper = new MyDatabaseHelper(this, "UserStore.db", null, 1);
    }

    private List<Map<String,Object>> initCata(String data) {

        dbHelper = new MyDatabaseHelper(this, "UserStore.db", null, 1);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        Map<String ,Object>map1 = new HashMap<String ,Object>();
//        map1.put("title", "工科数学分析");
//        map1.put("content", "B11");
//        map1.put("text", "8:00-9:45");
//        cataList.add(map1);

        Cursor cursor = db.query("Syllabus", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String week = cursor.getString(cursor.getColumnIndex("week"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String text = cursor.getString(cursor.getColumnIndex("time"));

                if (week.equals(data))
                {
                    Map<String ,Object>map3 = new HashMap<String ,Object>();
                    map3.put("title", title);
                    map3.put("content", content);
                    map3.put("text", text);
                    cataList.add(map3);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return cataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.i("exp", "11111111111111111111111");
        Intent intent = new Intent(Syllabus_monday.this, Syllabus_delete.class);
        intent.putExtra("extra_data", data);
        String po = "" + position;
        intent.putExtra("extra_position", po);
//        Log.i("exp", "22222222222222222222222222");
        startActivity(intent);
        finish();
    }

    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_syllabus_monday, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete:
                Toast.makeText(Syllabus_monday.this, "删除完成", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
