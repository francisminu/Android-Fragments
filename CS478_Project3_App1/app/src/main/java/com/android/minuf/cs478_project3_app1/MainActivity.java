package com.android.minuf.cs478_project3_app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // This will be used as the identifier in both App2 and App3 for HotelsReceiver
    private static final String HOTELS_TOAST_ACTION =
            "com.android.minuf.cs478_project3_part1.showHotelsToast";

    // This will be used as the identifier in both App2 and App3 for RestaurantsReceiver
    private static final String RESTAURANTS_TOAST_ACTION =
            "com.android.minuf.cs478_project3_part1.showRestaurantsToast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button hotelsButton = (Button) findViewById(R.id.hotelsButton);
        Button restaurantsButton = (Button) findViewById(R.id.restaurantsButton);


        hotelsButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent hotelsIntent = new Intent(HOTELS_TOAST_ACTION);
                sendOrderedBroadcast(hotelsIntent,null); // calls the orderedbroadcast so that response from App2 is displayed before App3
            }
        });

        restaurantsButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent restaurantsIntent = new Intent(RESTAURANTS_TOAST_ACTION);
                sendOrderedBroadcast(restaurantsIntent,null);// calls the orderedbroadcast so that response from App2 is displayed before App3
            }
        });

    }
}
