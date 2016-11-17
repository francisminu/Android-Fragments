package com.android.minuf.cs478_project3_app3;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;



public class HotelsActivity extends AppCompatActivity implements HotelNames.ListSelectionListener {

    public static String[] hotelNames;
    public static int[] hotelImages = {R.drawable.hyatt,R.drawable.congress,
    R.drawable.renaissance,R.drawable.ritz,R.drawable.allegro,R.drawable.fourseasons};

    int currentIndex = -1;
    static int flag = 0;
    static String OLD_ITEM = "old_item" ;

    private final HotelImages mHotelImagesFragment = new HotelImages(); //This is the second fragment that displays the Images of the chosen Hotel
    private HotelNames mHotelNamesFragment = null ;
    FragmentManager fragmentManager;

    private FrameLayout namesLayout, imagesLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The below statement retrieves the names of the hotels stored in the strings file
        hotelNames = getResources().getStringArray(R.array.HotelNames);
        setContentView(R.layout.activity_hotels);

        // This statement adds the Action bar to the Activity - The layout of the Action bar is kept separate so that all
        // the Activities in the application can have the same Action Bar */
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Retrieves the frame layouts which have been specified in the layout file
        namesLayout = (FrameLayout) findViewById(R.id.names_frame);
        imagesLayout = (FrameLayout) findViewById(R.id.images_frame);

        //Fragment manager manages the transactions of Fragments, like adding a fragment, replacing a fragment etc.,
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mHotelNamesFragment = new HotelNames();
        fragmentTransaction.replace(R.id.names_frame,mHotelNamesFragment); // This adds the namesfragments to the Frame Layout specified for the names fragment

        // Each time a fragment is added, it is added tp BackSTack so that specified operations can be performed on the fragment
                 fragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

        fragmentTransaction.commit(); // commits the fragment activities that have happened so far, ie., adding the fragment


        /* The below if statement checks if the state has been retained*/
        if(savedInstanceState != null)
        {
            //Log.i("In savedstate: " , currentIndex + "" );
            //Log.i("In savedstate olditem",savedInstanceState.getInt(OLD_ITEM) + "");

            currentIndex = savedInstanceState.getInt(OLD_ITEM); // retrieves the previously stored index to retain instance during orientation change

        }

    }

    // This method dipslays the OptionsMenu with options that allow the App users to toggle between the Hotels and Restaurants
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater(); // Inflates the Menu
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Below method handles the selection of each item from the Options Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
            {
                Intent intent = new Intent(this,HotelsActivity.class); // If hotels is chosen, HotelsActivity will be opened
                startActivity(intent);
                return true;
            }
            case R.id.item2:
            {
                Intent intent = new Intent(this,RestaurantsActivity.class); // If restaurants is chosen, the App would open the RestaurantsActivity
                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // This method sets the layout for the fragments based on the requirements
    private void setLayout() {

        if (!mHotelImagesFragment.isAdded()) { //this checks if the imagesFragment  has already been added


            if(getResources().getConfiguration().orientation == 1)
            {
                /*In portrait mode, the Names Fragment and Images Fragment should be displayed separately initially
                This is acquired through the below code. The layout of Names is set as MATCH_PARENT
                where as that of Images Fragment is set as 0, so that it i snot visible at first */
                namesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                imagesLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));

            }
            else
            {
                /*In Landscape mode, the Names Fragment and Images Fragment should be displayed separately initially
                This is acquired through the below code. The layout of Names is set as MATCH_PARENT
                where as that of Images Fragment is set as 0, so that it i snot visible at first */
                namesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                imagesLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            }

        } else {
            //Enters this else condition if one of the items in the Names Fragment list has been clicked
            if(getResources().getConfiguration().orientation == 1){
                /*In portrait mode, the Names Fragment should not be displayed on click of an item in the
                 * names fragment. Thus, the namesfragment is set as width - 0 and width and height of
                  * images fragment is set as MATCH_PARENT so that only images fragment is visible */

                namesLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
                imagesLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));

            }
            else
            {
                /* in landscape mode, on click of an item on the names fragement should shrink the names fragment to occupy only 1/3 of the
                 * screen space and the images fragment should be displayed such that it occupies 2/3 of the screen space. */

                namesLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                imagesLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }
    }


    public void onStart()
    {

        super.onStart();
        // This is called when the orientation changes and the instance has tp be retained
        if(currentIndex >= 0) {
            mHotelNamesFragment.setSelection(currentIndex);
            mHotelNamesFragment.getListView().setItemChecked(currentIndex,true);
            //mHotelImagesFragment.showImageAtIndex(currentIndex);
        }
    }

    @Override
    public void onListSelection(int index) {

        //The below code adds the images fragment if it has not been already added to the activity
        if (!mHotelImagesFragment.isAdded()) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.images_frame,mHotelImagesFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            fragmentManager.executePendingTransactions();


        }

        //if (currentIndex != index) {
            mHotelImagesFragment.showImageAtIndex(index); // calls the images fragment which displays the respective image corresponding to
                            // the chosen item in the list
            currentIndex = index ;
        //}
    }

    // This method is used to retain the instance of the fragments during orientation change
    public void onSaveInstanceState(Bundle outState) {
        //Log.i("In onSaveInstanceState",currentIndex + "");
        if (currentIndex >= 0) {
            outState.putInt(OLD_ITEM, currentIndex) ;
        }
        else if(currentIndex == -1)
        {
            outState.putInt(OLD_ITEM, -1) ;
        }
    }


}
