package com.psi.utascheduleplanner;

/**
 * Created by Dennis on 4/27/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TimeRestrictionfragment extends Fragment {

    UserDataBaseAdapter userDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timerestrictionview, container, false);

        return view;
    }
}
