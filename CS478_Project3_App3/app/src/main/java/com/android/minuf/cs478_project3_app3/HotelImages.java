package com.android.minuf.cs478_project3_app3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class HotelImages extends Fragment {

    private ImageView imgView = null;
    private int mCurrIdx = -1;
    private int mImageArrayLength;

    int getShownIndex()
    {
        return mCurrIdx;
    }

    void showImageAtIndex(int index)
    {

        if(index < 0 || index > mImageArrayLength)
            return;

        mCurrIdx = index;
        imgView.setImageResource(HotelsActivity.hotelImages[mCurrIdx]); // sets the imageView in the fragment layout with the image of the
        // chosen hotel


    }

    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hotel_images, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        imgView = (ImageView) getActivity().findViewById(R.id.imagesView); // finds the imageView in the layout file
        mImageArrayLength = HotelsActivity.hotelImages.length; // retrieves the length of the images array
    }

}
