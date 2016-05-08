package com.example.andy.seg;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by clover on 2016/3/6.
 */
public class Lock_activity extends AppCompatActivity {
//    private Button bt_unlock;
//    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

    private DevicePolicyManager policeManager;

    private ComponentName componentName;

    private Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);//关键代码
        setContentView(R.layout.lock_activity);

        LockScreen();

        bt = (Button) findViewById(R.id.bt_unlock);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bind();
            }
        });


        //隐去电池等图标和一切修饰部分（状态栏部分）
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置无标题
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        bt_unlock = (Button) findViewById(R.id.bt_unlock);
//        bt_unlock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


    }


    public void LockScreen(){
        policeManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, LockReceiver.class);
        if (policeManager.isAdminActive(componentName)) {
            policeManager.lockNow();
            android.os.Process.killProcess(android.os.Process.myPid());
        } else {

        }
    }

    public void Bind() {
        if(componentName!=null){
            policeManager.removeActiveAdmin(componentName);
            activeManager();
        }
    }

    //重写此方法用来在第一次激活设备管理器之后锁定屏幕
    @Override
    protected void onResume() {
        if (policeManager!=null && policeManager.isAdminActive(componentName)) {
            policeManager.lockNow();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        super.onResume();
    }

    private void activeManager() {
        //使用隐式意图调用系统方法来激活指定的设备管理器
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "一键锁屏");
        startActivity(intent);
    }

    //    @Override
//    public boolean onKeyDown( int keyCode, KeyEvent event) {
////         TODO Auto-generated method stub
//        if (keyCode == event. KEYCODE_HOME) {
//            return true;
//        } else if(keyCode == event.KEYCODE_BACK) {
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

//    @Override
//    public void onAttachedToWindow() {
//        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
//        super.onAttachedToWindow();
//    }
}
