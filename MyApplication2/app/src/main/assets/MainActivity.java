package com.test.bluedemo;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chenhao.bluetoothlib.BluetoothUtils;
import com.chenhao.bluetoothlib.btinterface.IClientListenerContract;
import com.chenhao.bluetoothlib.utils.ByteUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_open;
    private Button bt_close;
    private Button bt_serch;
    private Button bt_connect;
    private Button bt_send;
    private Button bt_getMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        BluetoothUtils.init(this);
    }

    private void initViews() {
        bt_open = (Button) findViewById(R.id.bt_open);
        bt_close = (Button) findViewById(R.id.bt_close);
        bt_serch = (Button) findViewById(R.id.bt_serch);
        bt_connect = (Button) findViewById(R.id.bt_connect);
        bt_open.setOnClickListener(this);
        bt_close.setOnClickListener(this);
        bt_serch.setOnClickListener(this);
        bt_connect.setOnClickListener(this);

        bt_send = (Button) findViewById(R.id.bt_send_msg);
        bt_send.setOnClickListener(this);
        bt_getMsg = (Button) findViewById(R.id.bt_get_msg);
        bt_getMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_open:
                BluetoothUtils.getInstance().openBluetooth(null);
                break;
            case R.id.bt_close:
                BluetoothUtils.getInstance().closeBluetooth(null);
                break;
            case R.id.bt_serch:
                BluetoothUtils.getInstance().searchBluetoothDevices(new IClientListenerContract.ISearchDeviceListener() {
                    @Override
                    public void onSearchStart() {

                    }

                    @Override
                    public void onFindDevice(BluetoothDevice bluetoothDevice) {

                    }

                    @Override
                    public void onSearchEnd(List<BluetoothDevice> bluetoothDevices) {

                    }
                });
                break;
            case R.id.bt_connect:
//                00:01:95:28:AF:28
//                00:01:95:28:AD:F8
//                00:01:95:28:AD:DA

                BluetoothUtils.getInstance().connectDevice("00:01:95:28:AD:DA", new IClientListenerContract.IConnectListener() {
                    @Override
                    public void onConnectSuccess(BluetoothDevice bluetoothDevice) {
                        bt_connect.setText("onConnectSuccess");
                        Log.e("tag===","onConnectSuccess");
                    }

                    @Override
                    public void onConnectFailure(String msg) {
                        bt_connect.setText("onConnectFailure");
                        Log.e("tag===","onConnectFailure");
                    }

                    @Override
                    public void onConnecting() {
                        bt_connect.setText("onConnecting");
                        Log.e("tag===","onConnecting");
                    }
                });
                break;
            case R.id.bt_send_msg:
                setMessage();
                break;
            case R.id.bt_get_msg:
                getMessage();
                break;

        }
    }

    /**
     * 发送消息
     */
    public void setMessage(){
        byte[] data = new byte[10];
        data[0] = Byte.parseByte("10");
        data[0] = Byte.parseByte("3");
        data[0] = Byte.parseByte("120");
        data[0] = Byte.parseByte("80");
        data[0] = Byte.parseByte("80");
        data[0] = Byte.parseByte("0");
        data[0] = Byte.parseByte("1");
        data[0] = Byte.parseByte("1");
        data[0] = Byte.parseByte("12");
        data[0] = Byte.parseByte("33");

        BluetoothUtils.getInstance().sendMessage(data, new IClientListenerContract.IDataSendListener() {
            @Override
            public void onDataSendSuccess(byte[] data) {
                bt_send.setText(new String(data));
                Log.e("tag===","onDataSendSuccess");
            }

            @Override
            public void onDataSendFailure(String msg) {
                bt_send.setText("失败"+msg);
                Log.e("tag===","onDataSendFailure");
            }
        });
    }

    /**
     * 获得消息
     */
    public void getMessage(){
        BluetoothUtils.getInstance().recevieMessage(new IClientListenerContract.IDataReceiveListener() {
            @Override
            public void onDataSuccess(byte[] data, int length) {
                //bt_getMsg.setText(new String(data));
                Log.e("tag===","onDataSuccess"+ ByteUtils.toString(data));
            }

            @Override
            public void onDataFailure(String msg) {
               // bt_getMsg.setText("失败"+msg);
                Log.e("tag===","onDataFailure"+msg);

            }
        });
    }

    public void listenStatus(){
        BluetoothUtils.getInstance().addBlueStasusLitener(new IClientListenerContract.IBluetoothStatusListener() {
            @Override
            public void discoverStart() {

            }

            @Override
            public void discoverEnd(ArrayList<BluetoothDevice> mBlueDevices) {

            }

            @Override
            public void bluetoothFound(BluetoothDevice bluetoothDevice) {

            }

            @Override
            public void bluetoothOpen() {

            }

            @Override
            public void bluetoothClose() {

            }

            @Override
            public void bluetoothConnectFailure() {

            }

            @Override
            public void bluetoothConnected(BluetoothDevice bluetoothDevice) {

            }

            @Override
            public void bluetoothDisconnect() {

            }
        });
    }
}
