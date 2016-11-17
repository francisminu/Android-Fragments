package com.android.minuf.cs478_project3_app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by minuf on 10/22/2016.
 */
public class HotelsReceiver extends BroadcastReceiver {
//Receiver that recieves the Restaurant broadcast
    @Override
    public void onReceive(Context arg0, Intent arg1){
        Log.i("Message_Check","Entered");
        // Display the Toast showing HotelsReceiver was clicked
        Toast.makeText(arg0,"Hotels Receiver was Clicked!!!",
                Toast.LENGTH_LONG).show();
    }

}
