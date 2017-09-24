package com.example.bt_moudle1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/24.
 */
public class DeviceListActivity  extends Activity{

    private ListView listPairedDevices;
    private DeviceAdapter deviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_device_list);
        initView();
    }

    private void initView() {
        listPairedDevices = (ListView) findViewById(R.id.listPairedDevices);
    }

    private class DeviceAdapter extends BaseAdapter {

        private List<String> mlists;

        public DeviceAdapter(List<String> lists) {
            this.mlists = lists;
        }

        @Override
        public int getCount() {
            return mlists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder ;
            if (convertView == null) {
                holder = new Holder();
                convertView = LayoutInflater.from(DeviceListActivity.this).inflate(R.layout.layout_device_name, parent, false);
                holder.txtName = (TextView) convertView.findViewById(R.id.txt_device_name);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.txtName.setText(mlists.get(position));
            return convertView;
        }

        class Holder {
            TextView txtName;
        }
    }
}
