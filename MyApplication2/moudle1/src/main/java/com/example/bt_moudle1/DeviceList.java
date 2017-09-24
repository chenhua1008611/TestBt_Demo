//package com.example.bt_moudle1;
//
//import android.app.Activity;
//import android.app.Activity;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.res.Resources;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.Iterator;
//import java.util.Set;
//
///**
// * Created by admin on 2017/9/20.
// */
//
//public class DeviceList extends Activity {
//    private static final boolean D = true;
//    public static final String EXTRA_DEVICE_ADDRESS = "DEVICE_ADDRESS";
//    private BluetoothAdapter bluetoothAdapter;
//    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent) {
//            paramAnonymousContext = paramAnonymousIntent.getAction();
//            if ("android.bluetooth.device.action.FOUND".equals(paramAnonymousContext)) {
//                paramAnonymousContext = (BluetoothDevice) paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
//                if (paramAnonymousContext.getBondState() != 12) {
//                    DeviceList.this.newDevicesArrayAdapter.add(paramAnonymousContext.getName() + "     " + paramAnonymousContext.getAddress());
//                }
//            }
//            do {
//                do {
//                    return;
//                }
//                while (!"android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(paramAnonymousContext));
//                DeviceList.this.setProgressBarIndeterminateVisibility(false);
//            } while (DeviceList.this.newDevicesArrayAdapter.getCount() != 0);
//            paramAnonymousContext = DeviceList.this.getResources().getText(2131099700).toString();
//            DeviceList.this.newDevicesArrayAdapter.add(paramAnonymousContext);
//        }
//    };
//    private ArrayAdapter<String> newDevicesArrayAdapter;
//    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
//        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
//            DeviceList.this.bluetoothAdapter.cancelDiscovery();
//            paramAnonymousAdapterView = ((TextView) paramAnonymousView).getText().toString();
//            paramAnonymousAdapterView = paramAnonymousAdapterView.substring(paramAnonymousAdapterView.length() - 17);
//            paramAnonymousView = new Intent();
//            paramAnonymousView.putExtra("DEVICE_ADDRESS", paramAnonymousAdapterView);
//            DeviceList.this.setResult(-1, paramAnonymousView);
//            DeviceList.this.finish();
//        }
//    };
//    private ArrayAdapter<String> pairedDevicesArrayAdapter;
//
//    private void doDiscovery() {
//        Log.d("DEBUG_BLUETOOTH", "doDiscovery()");
//        setProgressBarIndeterminateVisibility(true);
//        findViewById(2131492950).setVisibility(0);
//        if (this.bluetoothAdapter.isDiscovering()) {
//            this.bluetoothAdapter.cancelDiscovery();
//        }
//        this.bluetoothAdapter.startDiscovery();
//    }
//
//    protected void onCreate(Bundle paramBundle) {
//        super.onCreate(paramBundle);
//        requestWindowFeature(5);
//        setContentView(2130903066);
//        setResult(0);
//        ((Button) findViewById(2131492952)).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                DeviceList.this.doDiscovery();
//                paramAnonymousView.setVisibility(8);
//            }
//        });
//        this.pairedDevicesArrayAdapter = new ArrayAdapter(this, 2130903067);
//        this.newDevicesArrayAdapter = new ArrayAdapter(this, 2130903067);
//        paramBundle = (ListView) findViewById(2131492949);
//        paramBundle.setAdapter(this.pairedDevicesArrayAdapter);
//        paramBundle.setOnItemClickListener(this.onItemClickListener);
//        paramBundle = (ListView) findViewById(2131492951);
//        paramBundle.setAdapter(this.newDevicesArrayAdapter);
//        paramBundle.setOnItemClickListener(this.onItemClickListener);
//        paramBundle = new IntentFilter("android.bluetooth.device.action.FOUND");
//        registerReceiver(this.broadcastReceiver, paramBundle);
//        paramBundle = new IntentFilter("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
//        registerReceiver(this.broadcastReceiver, paramBundle);
//        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        paramBundle = this.bluetoothAdapter.getBondedDevices();
//        if (paramBundle.size() > 0) {
//            findViewById(2131492948).setVisibility(0);
//            paramBundle = paramBundle.iterator();
//            while (paramBundle.hasNext()) {
//                BluetoothDevice localBluetoothDevice = (BluetoothDevice) paramBundle.next();
//                this.pairedDevicesArrayAdapter.add(localBluetoothDevice.getName() + "     " + localBluetoothDevice.getAddress());
//            }
//        }
//        paramBundle = getResources().getText(2131099699).toString();
//        this.pairedDevicesArrayAdapter.add(paramBundle);
//    }
//
//    protected void onDestroy() {
//        super.onDestroy();
//        if (this.bluetoothAdapter != null) {
//            this.bluetoothAdapter.cancelDiscovery();
//        }
//        unregisterReceiver(this.broadcastReceiver);
//    }
//}
