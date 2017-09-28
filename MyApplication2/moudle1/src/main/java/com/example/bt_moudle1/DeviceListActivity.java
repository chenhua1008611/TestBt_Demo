package com.example.bt_moudle1;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.chenhao.bluetoothlib.BluetoothUtils;
import com.chenhao.bluetoothlib.btinterface.IClientListenerContract;
import com.chenhao.bluetoothlib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/24.
 */
public class DeviceListActivity  extends Activity implements AdapterView.OnItemClickListener,IClientListenerContract.ISearchDeviceListener {

    private ListView listPairedDevices;
    private DeviceAdapter deviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_device_list);
        initView();
        deviceAdapter = new DeviceAdapter();
        listPairedDevices.setAdapter(deviceAdapter);
        if (!BluetoothUtils.getInstance().getBluetoothEnable()){
            BluetoothUtils.getInstance().openBluetooth(new IClientListenerContract.IBlueClientIsOpenListener() {
                @Override
                public void onOpen() {
                    BluetoothUtils.getInstance().searchBluetoothDevices(DeviceListActivity.this);
                }
                @Override
                public void onClose() {

                }
            });
        }else{
            BluetoothUtils.getInstance().searchBluetoothDevices(this);
        }
    }

    @Override
    public void onSearchStart() {
        deviceAdapter.clear();
        Log.e("eeeee","开始搜索");
    }

    @Override
    public void onFindDevice(BluetoothDevice bluetoothDevice) {
        deviceAdapter.addDevice(bluetoothDevice);
        deviceAdapter.notifyDataSetChanged();
        Log.e("eeeee","onFindDevice"+bluetoothDevice.getName());
    }

    @Override
    public void onSearchEnd(List<BluetoothDevice> bluetoothDevices) {
        if (bluetoothDevices!=null&&bluetoothDevices.size()>0){
            Log.e("eeeee","onSearchEnd"+bluetoothDevices.get(0).getName());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        deviceAdapter.clear();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BluetoothUtils.getInstance().cancelBluetoothSearch();
        Intent intent = new Intent();
        intent.putExtra("mac_address",deviceAdapter.getDevice(position));
        setResult(200, intent);
        finish();
    }

    private void initView() {
        listPairedDevices = (ListView) findViewById(R.id.listNewDevices);
        listPairedDevices.setOnItemClickListener(this);
    }

    private class DeviceAdapter extends BaseAdapter {

        private List<BluetoothDevice> mlists;

        public DeviceAdapter() {
            mlists = new ArrayList<>();
        }

        public void addDevice(BluetoothDevice device){
            if(!mlists.contains(device)) {
                mlists.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mlists.get(position);
        }

        public void clear() {
            mlists.clear();
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
            holder.txtName.setText(mlists.get(position).getName());
            return convertView;
        }

        class Holder {
            TextView txtName;
        }
    }
}
