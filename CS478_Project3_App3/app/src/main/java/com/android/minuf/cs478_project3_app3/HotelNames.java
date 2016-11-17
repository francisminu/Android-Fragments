package com.android.minuf.cs478_project3_app3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HotelNames extends ListFragment {

    private ListSelectionListener mListener = null;
    static final String OLD_POSITION = "oldPos" ;
    Integer mOldPosition = null ;

    // This is the interface that the Activity would implement and the activity will also define the method onListSelection
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        getListView().setItemChecked(pos, true);
        mListener.onListSelection(pos); // the onListSelection() method defined in the activity is called for further proceedings
    }

    @Override
    public void onAttach(Context activity) {

        super.onAttach(activity);
        try {

            mListener = (ListSelectionListener) activity; /* Attaches the instance of activity with the interface. This step
            makes sure that the Activity is implementing the listener */

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListSelectionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onActivityCreated(Bundle savedState) {

        super.onActivityCreated(savedState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_hotel_names, HotelsActivity.hotelNames)); // loads the listview with the hotel names
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedState) {
        return super.onCreateView(inflater,container,savedState);

    }

    /* The below methods show the various methods called during the lifecycle of fragment and the respective activity */

    @Override
    public void onStart() {

        super.onStart();

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {

        super.onStop();
    }

    @Override
    public void onDetach() {

        super.onDetach();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }


}
