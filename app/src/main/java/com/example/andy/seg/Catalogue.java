package com.example.andy.seg;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by clover on 2016/2/14.
 */
public class Catalogue extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<Map<String,Object>> cataList;
    private ListView listView;
    private ArrayAdapter<String> arr_adapter;
    private SimpleAdapter simp_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue);
        ListView listView = (ListView) findViewById(R.id.listView);
        //1、新建一个数据适配器
        //ArrayAdapter(上下文， 当前ListView加载的每一个列表项所对应的布局文件，数据源)
        //SimpleAdapter();
        /**
         * context ：上下文
         * data：数据源（List<？ extends Map<String, ?>> data）一个Map所组成的List集合
         *       每一个Map都会去对应ListView列表中的一行
         *       每一个Map（键-值对）中的键必须包含所有在from中所指定的键
         * resource：列表项的布局文件ID
         * from：Map中的键名
         * to:绑定数据视图中的ID， 与from成对应关系
         */

        //2、适配器加载数据源
        String[]arr_data = {"课程表", "时间管理", "学习交流区"};
        cataList = new ArrayList<Map<String,Object>>();
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        simp_adapter = new SimpleAdapter(this, initCata(), R.layout.cata_item, new String[]{"image", "name"},new int[]{R.id.cata_image,R.id.cata_name});
        //3、视图加载适配器
        //listView.setAdapter(arr_adapter);
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> initCata() {
        Map<String ,Object>map1 = new HashMap<String ,Object>();
        map1.put("image", R.mipmap.ic_launcher);
        map1.put("name", "课程表");
        cataList.add(map1);

        Map<String ,Object>map2 = new HashMap<String ,Object>();
        map2.put("image", R.mipmap.ic_launcher);
        map2.put("name", "一键锁屏");
        cataList.add(map2);

        Map<String ,Object>map3 = new HashMap<String ,Object>();
        map3.put("image", R.mipmap.ic_launcher);
        map3.put("name", "记事本");
        cataList.add(map3);

        Map<String ,Object>map4 = new HashMap<String ,Object>();
        map4.put("image", R.mipmap.ic_launcher);
        map4.put("name", "学习交流区");
        cataList.add(map4);

        Map<String ,Object>map5 = new HashMap<String ,Object>();
        map5.put("image", R.mipmap.ic_launcher);
        map5.put("name", "计时锁屏");
        cataList.add(map5);

        Map<String ,Object>map6 = new HashMap<String ,Object>();
        map6.put("image", R.mipmap.ic_launcher);
        map6.put("name", "考试倒计时");
        cataList.add(map6);

        return cataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {
            Intent intent = new Intent(Catalogue.this, Syllabus_main.class);
            startActivity(intent);
        }

        else if (position == 1) {
            Intent intent = new Intent(Catalogue.this, Lock_activity.class);
            startActivity(intent);
        }
        else if (position == 2) {
            Intent intent = new Intent(Catalogue.this, Note_main.class);
            startActivity(intent);
        }
        else if (position == 3) {
            Intent intent = new Intent(Catalogue.this, Communicate.class);
            startActivity(intent);
            Toast.makeText(Catalogue.this, "该项目正在努力开发中", Toast.LENGTH_SHORT).show();
        }
        else if (position == 4) {
            Intent intent = new Intent(Catalogue.this, Lock_main.class);
            startActivity(intent);
            Toast.makeText(Catalogue.this, "该项目正在努力开发中", Toast.LENGTH_SHORT).show();
        }
        else if (position == 5) {
            Toast.makeText(Catalogue.this, "该项目正在努力开发中", Toast.LENGTH_SHORT).show();
        }
    }
}
