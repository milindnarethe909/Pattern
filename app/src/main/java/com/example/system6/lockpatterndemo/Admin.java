package com.example.system6.lockpatterndemo;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by system6 on 3/7/2018.
 */

public class Admin extends DeviceAdminReceiver {

    private static final String TAG=Admin.class.getSimpleName();
    int countPattern=0;

    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Device Administrator: Activated");
        Log.d(TAG,"Device Administrator: Activated");
    }
    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "This is an optional message to warn the user about disabling.";
    }
    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "Device Administrator: Deactivated");
        Log.d(TAG,"Device Administrator: Deactivated");
    }
    @Override
    public void onPasswordFailed(Context context, Intent intent) {
        showToast(context, "Sample Device Admin: pw failed");
        Log.d(TAG,"Sample Device Admin: pw failed");
        ++countPattern;

        DevicePolicyManager mgr = (DevicePolicyManager) context
                .getSystemService(Context.DEVICE_POLICY_SERVICE);

        Log.d(TAG,"Number of Attems in android = "+mgr.getCurrentFailedPasswordAttempts());
        Vibrator v = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        // Vibrate for 2 seconds
        v.vibrate(2000);
    }
    @Override
    public void onPasswordSucceeded(Context context, Intent intent) {
        showToast(context, "Welcome Device Owner");
    }
}