package com.example.bt_moudle1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2017/10/11.
 */

public class AbstractFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View messageLayout = inflater.inflate(R.layout.fragment_abstact, container, false);
        return messageLayout;
    }
}
