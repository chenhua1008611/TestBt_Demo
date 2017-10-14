package com.example.administrator.myapplication.bt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chenhao.bluetoothlib.BluetoothUtils;
import com.chenhao.bluetoothlib.btinterface.IClientListenerContract;
import com.chenhao.bluetoothlib.utils.ByteUtils;
import com.chenhao.bluetoothlib.utils.ToastUtils;
import com.example.administrator.myapplication.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class BtMainActivity extends BtBaseActivity implements View.OnClickListener, OnChartValueSelectedListener {

    private Button btnParam;
    private Button btnViewResult;
    private Button btnBlutoothSet;
    private Button btnCommitExam;
    private Button btnNextStudent;
    private String macAddress;

    private Button btnStart;
    private Button btnEnd;
    private Button btnCancle;
    private boolean isConn;
    private BluetoothAdapter mBtAdapter;
    private static final int REQUEST_ENABLE_BT = 3;

    private LineChart mChart;

    private TextView txtName;
    private TextView txtRoomNo;
    private TextView txtCurrentTime;
    private TextView txtKaoshiKemu;
    private TextView txtKaoshiShichang;
    Timer timer = new Timer();
    Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (txtCurrentTime!=null){
                txtCurrentTime.setText("现在时间："+getNormalTime()+"（实时系统时间）");
            }
        }
    };

    private TextView txtZhouDai;
    private TextView txtChuZheng;
    private ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        btnParam = (Button) findViewById(R.id.btn_set_exam);
        btnViewResult = (Button) findViewById(R.id.img_view_result);
        btnBlutoothSet = (Button) findViewById(R.id.btn_bluetooth_seting);
        btnCommitExam = (Button) findViewById(R.id.img_commit_exam);
        btnNextStudent = (Button) findViewById(R.id.img_next_student);
        btnParam.setOnClickListener(this);
        btnViewResult.setOnClickListener(this);
        btnBlutoothSet.setOnClickListener(this);
        btnCommitExam.setOnClickListener(this);
        btnNextStudent.setOnClickListener(this);
        btnNextStudent.setVisibility(View.VISIBLE);
        btnViewResult.setVisibility(View.VISIBLE);
        btnBlutoothSet.setVisibility(View.VISIBLE);

        btnStart = (Button) findViewById(R.id.btn_start_exam);
        if (isConn){
            btnStart.setEnabled(true);
            btnStart.setOnClickListener(this);
            btnStart.setBackgroundColor(Color.parseColor("#9EA82E"));
            btnStart.setTextColor(Color.WHITE);
        }else{
            btnStart.setEnabled(false);
            btnStart.setOnClickListener(null);
            btnStart.setBackgroundColor(Color.WHITE);
            btnStart.setTextColor(Color.BLACK);
        }
        btnEnd = (Button) findViewById(R.id.btn_end_exam);
        btnCancle = (Button) findViewById(R.id.btn_cancle_exam);

        btnEnd.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

        txtName = (TextView) findViewById(R.id.txt_kaosheng_name);
        txtRoomNo = (TextView) findViewById(R.id.txt_kaosheng_fangjianhao);
        txtCurrentTime = (TextView) findViewById(R.id.txt_current_time);
        txtKaoshiKemu = (TextView) findViewById(R.id.txt_kaoshi_timu);
        txtKaoshiShichang = (TextView) findViewById(R.id.txt_kaoshi_shichnag);
        txtName.setText("考生姓名：张三");
        txtRoomNo.setText("考站房间号：301");
        txtCurrentTime.setText("现在时间："+getNormalTime()+"（实时系统时间）");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handle.sendMessage(new Message());
            }
        },0,1000);
        txtKaoshiKemu.setText("考试题目：血压考试A");
        txtKaoshiShichang.setText("考试时长：15分钟（点击开始考试，15分钟后系统将自动结束考试）");

        txtZhouDai = (TextView) findViewById(R.id.txt_main_cuff_ng);
        txtChuZheng = (TextView) findViewById(R.id.txt_main_palpation_ng);

        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setDrawGridBackground(true);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        initUpper();
        initChart();
    }

    private  String getNormalTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String time = format.format(new Date()) ;
        return time;
    }

    private void initUpper() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        LimitLine ll1 = new LimitLine(140f, "Upper Limit");
        ll1.setLineWidth(2.5f);
//        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.enableDashedLine(0f, 0f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
        ll1.setTypeface(tf);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);
        leftAxis.setAxisMaximum(70f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);

        leftAxis.setDrawLimitLinesBehindData(true);


    }

    private void initChart() {
        // listener for selecting and drawing
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);

        mChart.setData(new LineData());
        mChart.invalidate();

    }


    private void addEntry(int value) {
        LineData data = mChart.getData();
        ILineDataSet set = data.getDataSetByIndex(0);
        // set.addEntry(...); // can be called as well
        if (set == null) {
            set = createSet();
            data.addDataSet(set);
        }
        // choose a random dataSet
        int randomDataSetIndex = (int) (Math.random() * data.getDataSetCount());
//        float yValue = (float) (Math.random() * 10) + 50f;
        float yValue = value;
        data.addEntry(new Entry(data.getDataSetByIndex(randomDataSetIndex).getEntryCount(), yValue), randomDataSetIndex);
        data.notifyDataChanged();
        // let the chart know it's data has changed
        mChart.notifyDataSetChanged();

        mChart.setVisibleXRangeMaximum(6);
        //mChart.setVisibleYRangeMaximum(15, AxisDependency.LEFT);
//
//            // this automatically refreshes the chart (calls invalidate())
        mChart.moveViewTo(data.getEntryCount() - 7, 50f, YAxis.AxisDependency.LEFT);

    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }


    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "DataSet 1");
        set.setLineWidth(2.5f);
//        set.setCircleRadius(4.5f);
        set.setCircleRadius(1.0f);
        set.setDrawCircles(false);
//        set.setColor(Color.rgb(240, 99, 99));
        set.setColor(Color.GREEN);
//        set.setCircleColor(Color.rgb(240, 99, 99));
        set.setCircleColor(Color.TRANSPARENT);
//        set.setHighLightColor(Color.rgb(190, 190, 190));
        set.setHighLightColor(Color.WHITE);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setValueTextSize(10f);
        set.setDrawValues(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawHighlightIndicators(false);
        set.setDrawVerticalHighlightIndicator(false);

        return set;
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v == btnParam) {
            intent.setClass(BtMainActivity.this, BtParamSettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else if (v == btnViewResult) {
            intent.setClass(BtMainActivity.this, BtViewResultActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else if (v == btnBlutoothSet) {
            if (!mBtAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }else{
                intent.setClass(BtMainActivity.this, BtDeviceListActivity2.class);
                startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }

        } else if (v == btnCommitExam) {
            intent.setClass(BtMainActivity.this, BtSubmitResultActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else if (v == btnNextStudent) {
//            intent.setAction( "com.myaction.example.administrator.myapplication");
//            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            startActivityForResult(intent, 0);
//            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
            finish();
        } else if (v == btnStart) {
            setMessage("10");
        } else if (v == btnEnd) {
            setMessage("20");
        } else if (v == btnCancle) {

        }else if (v == imgBack){
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            macAddress = data.getStringExtra("device_address");
            conn(macAddress);
        }else if (requestCode == 0&&resultCode == Activity.RESULT_OK){
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
        }else if (requestCode == REQUEST_ENABLE_BT){
            if (resultCode == Activity.RESULT_OK){
                Intent intent = new Intent();
                intent.setClass(BtMainActivity.this, BtDeviceListActivity2.class);
                startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
            }else{
                Toast.makeText(BtMainActivity.this, R.string.bt_not_enabled_leaving,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void conn(String address) {
        BluetoothUtils.getInstance().connectDevice(address, new IClientListenerContract.IConnectListener() {
            @Override
            public void onConnectSuccess(BluetoothDevice bluetoothDevice) {
                ToastUtils.showShort(BtMainActivity.this, "连接成功!");
                Log.e("tag===", "onConnectSuccess");
                isConn = true;
                btnStart.setEnabled(true);
                btnStart.setBackgroundColor(Color.parseColor("#9EA82E"));
                btnStart.setTextColor(Color.WHITE);
            }

            @Override
            public void onConnectFailure(String msg) {
                ToastUtils.showShort(BtMainActivity.this, "连接失败!");
                Log.e("tag===", "onConnectFailure");
                isConn = false;
            }

            @Override
            public void onConnecting() {
                ToastUtils.showLong(BtMainActivity.this, "连接中...");
                Log.e("tag===", "onConnecting");
                isConn = false;
            }
        });

    }

    /**
     * 发送消息
     */
    public void setMessage(String str) {
        byte[] data = new byte[10];
//        data[0] = Byte.parseByte("10");
        data[0] = Byte.parseByte(str);
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
                Log.e("tag===", "onDataSendSuccess");
                getMessage();
            }

            @Override
            public void onDataSendFailure(String msg) {
                Log.e("tag===", "onDataSendFailure");
            }
        });
    }

    /**
     * 获得消息
     */
    public void getMessage() {
        BluetoothUtils.getInstance().recevieMessage(new IClientListenerContract.IDataReceiveListener() {
            @Override
            public void onDataSuccess(byte[] data, int length) {
                Log.e("tag===", "onDataSuccess" + ByteUtils.toString(data));
                if (data != null) {
                    if (data[0] == 0){
                        txtZhouDai.setText("No");
                    }else{
                        txtZhouDai.setText("Yes");
                    }

                    if (data[1] == 0){
                        txtChuZheng.setText("No");
                    }else{
                        txtChuZheng.setText("Yes");
                    }

                    addEntry(data[2]*100+data[3]);
                }
            }

            @Override
            public void onDataFailure(String msg) {
                Log.e("tag===", "onDataFailure" + msg);

            }
        });
    }


}
