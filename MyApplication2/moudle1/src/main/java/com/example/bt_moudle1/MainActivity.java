package com.example.bt_moudle1;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chenhao.bluetoothlib.BluetoothUtils;
import com.chenhao.bluetoothlib.btinterface.IClientListenerContract;
import com.chenhao.bluetoothlib.utils.ByteUtils;
import com.chenhao.bluetoothlib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends Activity implements View.OnClickListener {

    LineChartView mChartView;
    List<PointValue> values;
    List<Line> lines;

    private float minY = 0f;//Y轴坐标最小值
    private float maxY = 5f;//Y轴坐标最大值

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        initView();
        initChart();
    }


    private void initChart(){
        values = new ArrayList<PointValue>();//折线上的点
        Line line = new Line(values).setColor(Color.GREEN);//声明线并设置颜色
        line.setCubic(true);//设置是平滑的还是直的
        line.setHasPoints(false);//是否显示圆点
        line.setStrokeWidth(1);
        line.setHasLabels(true);
        lines = new ArrayList<Line>();
        lines.add(line);

        mChartView.setInteractive(true);//设置图表是可以交互的（拖拽，缩放等效果的前提）
        mChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);//设置缩放方向
        LineChartData data = new LineChartData();
        Axis axisX = new Axis();//x轴
        Axis axisY = new Axis();//y轴

        axisX.setTextColor(ChartUtils.COLOR_RED);
//        axisX.setTextColor(Color.BLACK);
        axisX.setFormatter(new SimpleAxisValueFormatter().setPrependedText("".toCharArray()));
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        data.setValueLabelsTextColor(Color.TRANSPARENT);
        data.setLines(lines);

        //设置行为属性，支持缩放、滑动以及平移
        mChartView.setInteractive(true);
        mChartView.setZoomType(ZoomType.HORIZONTAL);
        mChartView.setMaxZoom((float) 4);//最大方法比例
        mChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        mChartView.setLineChartData(data);
        mChartView.setVisibility(View.VISIBLE);

        Viewport v = new Viewport(mChartView.getMaximumViewport());
        v.bottom = minY;
        v.top = maxY;
        //固定Y轴的范围,如果没有这个,Y轴的范围会根据数据的最大值和最小值决定,这不是我想要的
        mChartView.setMaximumViewport(v);

        //这2个属性的设置一定要在lineChart.setMaximumViewport(v);这个方法之后,不然显示的坐标数据是不能左右滑动查看更多数据的
        v.left = values.size() - 7;
        v.right = values.size() - 1;
        mChartView.setCurrentViewport(v);
    }


    private void initView() {
        mChartView = (LineChartView) findViewById(R.id.chart);
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
        btnEnd = (Button) findViewById(R.id.btn_end_exam);
        btnCancle = (Button) findViewById(R.id.btn_cancle_exam);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v == btnParam) {
            intent.setClass(MainActivity.this, ParamSettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else if (v == btnViewResult) {
            intent.setClass(MainActivity.this, ViewResultActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else if (v == btnBlutoothSet) {
            intent.setClass(MainActivity.this, DeviceListActivity.class);
            startActivityForResult(intent, 100);
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else if (v == btnCommitExam) {
            intent.setClass(MainActivity.this, SubmitResultActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
        } else if (v == btnNextStudent) {
//            intent.setClass(MainActivity.this,H5Activity.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
        } else if (v == btnStart) {
            setMessage();
        } else if (v == btnEnd) {

        } else if (v == btnCancle) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            macAddress = data.getStringExtra("mac_address");
            conn(macAddress);
        }
    }

    private void conn(String address) {
        BluetoothUtils.getInstance().connectDevice(address, new IClientListenerContract.IConnectListener() {
            @Override
            public void onConnectSuccess(BluetoothDevice bluetoothDevice) {
                ToastUtils.showShort(MainActivity.this, "连接成功!");
                Log.e("tag===", "onConnectSuccess");
                isConn = true;
                if (isConn) {
                    btnStart.setEnabled(false);
                    btnStart.setOnClickListener(null);
                } else {
                    btnStart.setEnabled(true);
                    btnStart.setOnClickListener(MainActivity.this);
                }
            }

            @Override
            public void onConnectFailure(String msg) {
                ToastUtils.showShort(MainActivity.this, "连接失败!");
                Log.e("tag===", "onConnectFailure");
            }

            @Override
            public void onConnecting() {
                ToastUtils.showLong(MainActivity.this, "连接中...");
                Log.e("tag===", "onConnecting");
            }
        });

    }

    /**
     * 发送消息
     */
    public void setMessage() {
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
                //bt_getMsg.setText(new String(data));
                Log.e("tag===", "onDataSuccess" + ByteUtils.toString(data));
                int i = 0;
                if (data!=null&&data.length ==5){
                    i++;
                    values.add(new PointValue(i,data[2]*100+data[3]));
                }
                initChart();
            }

            @Override
            public void onDataFailure(String msg) {
                // bt_getMsg.setText("失败"+msg);
                Log.e("tag===", "onDataFailure" + msg);

            }
        });
    }

}
