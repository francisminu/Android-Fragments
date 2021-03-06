package com.android.minuf.cs478_project3_app3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by minuf on 10/28/2016.
 */
public class HotelsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1){

        // Opens the HotelsActivity if the hotels broadcast has been received

        Intent intent = new Intent(arg0,HotelsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        arg0.startActivity(intent); // calls the activity from this receiver
    }

}
