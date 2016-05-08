package com.example.andy.seg;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by clover on 2016/3/9.
 */
public class Lock_MyDeviceAdmin {
//    public static final String TAG = "Lock_MyDeviceAdmin";
//
//    private Context mContext;
//
//    private DevicePolicyManager mDPM;
//
//    private ComponentName mCN;
//
//    public Lock_MyDeviceAdmin(Context context) {
//        mContext = context;
//        mDPM = new DevicePolicyManager();
//        context.getSystemService(Context.DEVICE_POLICY_SERVICE);
//        mCN = new ComponentName(context, MyDeviceAdminReceiver.class);
//        myLock();
//    }
//
//    private void myLock() {
//        try {
////mDPM.setActiveAdmin(mCM, true); //强制激活设备管理，这个方法不开放，需要到源码里编译才行
//            boolean active = true;
//            if (active) {
//                mDPM.lockNow(); // 强制锁屏
//            } else {
//                activeDeviceManager();// 去激活设备管理
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    private void activeDeviceManager() {
//        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mCN);
//        mContext.startActivity(intent);
//    }
//
//    public static class MyDeviceAdminReceiver extends DeviceAdminReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d(TAG, "action=" + intent.getAction());
//        }
//
//    }
//
//    private void activeDeviceManager() {
//        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mCM);
//        mContext.startActivity(intent);
//    }
//
//    public static class MyDeviceAdminReceiver extends DeviceAdminReceiver{
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d(TAG, "action=" + intent.getAction());
//        }



}