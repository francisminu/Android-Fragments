package com.android.minuf.cs478_project3_app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by minuf on 10/22/2016.
 */
public class RestaurantsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1){

        // Display the Toast showing RestaurantsReceiver was clicked
        Toast.makeText(arg0,"Restaurants Receiver was Clicked!!!",
                Toast.LENGTH_LONG).show();
    }

}
