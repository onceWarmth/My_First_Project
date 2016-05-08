package com.example.andy.seg;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarContextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by clover on 2016/2/12.
 */
public class Enroll2 extends AppCompatActivity{
    private EditText userName;
    private EditText userPass;
    private EditText userPass_ok;
    private Button bt;
    private MyDatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enroll);
        dbHelper = new MyDatabaseHelper(this, "UserStore.db", null, 1);
        userName = (EditText) findViewById(R.id.et_username);
        userPass = (EditText) findViewById(R.id.et_passsword);
        userPass_ok = (EditText) findViewById(R.id.et_pass_OK);
        bt = (Button) findViewById(R.id.btn_enroll_ok);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pass = userPass.getText().toString();
                String pass_ok = userPass_ok.getText().toString();
                if (userName.length() > 0 && userPass.length() > 0 && userPass_ok.length() > 0) {
                    if (pass.equals(pass_ok)) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.beginTransaction();
                        ContentValues values = new ContentValues();
                        values.put("userName", name);
                        values.put("userPass", pass);
                        db.insert("User", null, values);//插入数据
                        db.setTransactionSuccessful();
                        db.endTransaction();
                        finish();
                    } else {
                        Toast.makeText(Enroll2.this, "确认密码错误", Toast.LENGTH_SHORT).show();
                    }

                } else if (userName.length() == 0) {
                    Toast.makeText(Enroll2.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (userPass.length() == 0) {
                    Toast.makeText(Enroll2.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (userPass_ok.length() == 0) {
                    Toast.makeText(Enroll2.this, "请输入确认密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}