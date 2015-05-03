package com.psi.utascheduleplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;

public class Usersettingsfragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    Button delButton;

    String userName = "TestName";

    UserDataBaseAdapter userDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usersettingview, container, false);

        MainActivity mainInfo = (MainActivity) getActivity();
        userName = mainInfo.getCurUser();

        userDB = new UserDataBaseAdapter(view.getContext());
        try {
            userDB.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        delButton = (Button) view.findViewById(R.id.delCurrentUser);
        delButton.setOnClickListener(this);
        delButton.setOnLongClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(), "Press and hold to delete account. " + userName, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View view) {
        Toast.makeText(getActivity(), "Account deleted.", Toast.LENGTH_LONG).show();

        userDB.delUser(userName);
        userDB.close();
        Intent loginScreen = new Intent(getActivity(), Login.class);
        startActivity(loginScreen);
        return true;
    }


}