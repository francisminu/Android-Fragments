package com.android.minuf.cs478_project3_app3;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class RestaurantsActivity extends AppCompatActivity implements RestaurantNames.ListSelectionListener{

    public static String[] restaurantNames;
    public static int[] restaurantImages = {R.drawable.grill,R.drawable.gejas,
            R.drawable.greek,R.drawable.gage,R.drawable.carnivale,R.drawable.shaws};

    int currentIndex = -1 ;
    static String OLD_ITEM = "old_item" ;

    private final RestaurantImages mRestaurantImagesFragment = new RestaurantImages(); //This is the second fragment that displays the Images of the chosen Restaurant
    private RestaurantNames mRestaurantNamesFragment = null ;
    FragmentManager fragmentManager;

    private FrameLayout namesLayout, imagesLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //The below statement retrieves the names of the Restaurants stored in the strings file
        restaurantNames = getResources().getStringArray(R.array.RestaurantNames);

        setContentView(R.layout.activity_restaurants);

        // This statement adds the Action bar to the Activity - The layout of the Action bar is kept separate so that all
        // the Activities in the application can have the same Action Bar */
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Retrieves the frame layouts which have been specified in the layout file
        namesLayout = (FrameLayout) findViewById(R.id.restnames_frame);
        imagesLayout = (FrameLayout) findViewById(R.id.restimages_frame);

        //Fragment manager manages the transactions of Fragments, like adding a fragment, replacing a fragment etc.,
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mRestaurantNamesFragment = new RestaurantNames();
        fragmentTransaction.replace(R.id.restnames_frame,mRestaurantNamesFragment);  // This adds the namesfragments to the Frame Layout specified for the names fragment


        /* The below if statement checks if the state has been retained*/
        if(savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(OLD_ITEM); // retrieves the previously stored index to retain instance during orientation change
        }

        // Each time a fragment is added, it is added tp BackSTack so that specified operations can be performed on the fragment
        fragmentManager
                .addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

        fragmentTransaction.commit();
    }

    // This method sets the layout for the fragments based on the requirements
    private void setLayout() {

        //this checks if the imagesFragment  has already been added
        if (!mRestaurantImagesFragment.isAdded()) {

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

    // This method dipslays the OptionsMenu with options that allow the App users to toggle between the Hotels and Restaurants
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu); //inflates the menu layout in the Menu resource directory
        return true;
    }

    //Below method handles the selection of each item from the Options Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
            {
                Intent intent = new Intent(this,HotelsActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.item2:
                Intent intent = new Intent(this,RestaurantsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // This is called when the orientation changes and the instance has tp be retained
    public void onStart()
    {
        super.onStart();

        if(currentIndex >= 0) {
            //mRestaurantImagesFragment.showImageAtIndex(currentIndex);
            mRestaurantNamesFragment.setSelection(currentIndex);
            mRestaurantNamesFragment.getListView().setItemChecked(currentIndex,true);
        }
    }

    @Override
    public void onListSelection(int index) {

        //The below code adds the images fragment if it has not been already added to the activity
        if (!mRestaurantImagesFragment.isAdded()) {

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.restimages_frame,
                    mRestaurantImagesFragment);
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.commit();

            fragmentManager.executePendingTransactions();


        }

        //if (currentIndex != index) {


        // calls the images fragment which displays the respective image corresponding to
        // the chosen item in the list
        mRestaurantImagesFragment.showImageAtIndex(index);
        currentIndex = index ;
        //}
    }

    // This method is used to retain the instance of the fragments during orientation change
    public void onSaveInstanceState(Bundle outState) {

        if (currentIndex >= 0) {
            outState.putInt(OLD_ITEM, currentIndex) ;
        }
        else if(currentIndex == -1)
        {
            outState.putInt(OLD_ITEM, -1) ;
        }
    }
}
