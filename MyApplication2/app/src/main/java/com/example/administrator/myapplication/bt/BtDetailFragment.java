package com.example.administrator.myapplication.bt;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;

/**
 * Created by admin on 2017/10/11.
 */

public class BtDetailFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.fragment_details, container, false);
        return messageLayout;
    }
}
