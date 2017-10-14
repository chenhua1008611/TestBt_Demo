package com.example.administrator.myapplication.bt;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.R;

/**
 * Created by admin on 2017/9/20.
 */

public class BtViewResultActivity extends BtBaseActivity implements View.OnClickListener {

    private Button btnAbstact;
    private Button btnDetail;
    private FragmentManager fragmentManager;
    private BtAbstractFragment abstractFragment;
    private BtDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        initViews();
        fragmentManager = getFragmentManager();
        setTabSelection(0);
    }

    private void initViews() {
        btnAbstact = (Button) findViewById(R.id.btn_start_exam_abstact);
        btnDetail = (Button) findViewById(R.id.btn_end_exam_detail);
        btnAbstact.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
    }

    public void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                btnAbstact.setBackgroundColor(Color.parseColor("#9EA82E"));
                btnAbstact.setTextColor(Color.WHITE);
                if (abstractFragment == null) {
                    abstractFragment = new BtAbstractFragment();
                    transaction.add(R.id.content, abstractFragment);
                } else {
                    transaction.show(abstractFragment);
                }
                break;
            case 1:
                btnDetail.setBackgroundColor(Color.parseColor("#9EA82E"));
                btnDetail.setTextColor(Color.WHITE);
                if (detailFragment == null) {
                    detailFragment = new BtDetailFragment();
                    transaction.add(R.id.content, detailFragment);
                } else {
                    transaction.show(detailFragment);
                }
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        if (v == btnAbstact) {
            setTabSelection(0);
        } else if (v == btnDetail) {
            setTabSelection(1);
        }
    }

    private void clearSelection() {
        btnAbstact.setBackgroundColor(Color.WHITE);
        btnAbstact.setTextColor(Color.parseColor("#333333"));
        btnDetail.setBackgroundColor(Color.WHITE);
        btnDetail.setTextColor(Color.parseColor("#333333"));

    }

    private void hideFragments(FragmentTransaction transaction) {
        if (abstractFragment != null) {
            transaction.hide(abstractFragment);
        }
        if (detailFragment != null) {
            transaction.hide(detailFragment);
        }
    }

}
