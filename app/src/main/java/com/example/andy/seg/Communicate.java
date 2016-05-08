package com.example.andy.seg;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clover on 2016/2/15.
 */
public class Communicate extends AppCompatActivity{
    private ListView list_course;
    private List<Map<String,Object>> cataList;
    private SimpleAdapter simp_adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communicate);
        list_course = (ListView) findViewById(R.id.list_course);
        cataList = new ArrayList<Map<String,Object>>();
        simp_adapter = new SimpleAdapter(this, initCata(), R.layout.cata_item, new String[]{"image", "name"},new int[]{R.id.cata_image,R.id.cata_name});
        list_course.setAdapter(simp_adapter);
    }

    private List<Map<String,Object>> initCata() {
        Map<String ,Object>map1 = new HashMap<String ,Object>();
        map1.put("image", R.mipmap.ic_launcher);
        map1.put("name", "工科数学分析");
        cataList.add(map1);

        Map<String ,Object>map2 = new HashMap<String ,Object>();
        map2.put("image", R.mipmap.ic_launcher);
        map2.put("name", "线性代数");
        cataList.add(map2);

        Map<String ,Object>map3 = new HashMap<String ,Object>();
        map3.put("image", R.mipmap.ic_launcher);
        map3.put("name", "C语言");
        cataList.add(map3);

        return cataList;
    }
}
