package com.example.bt_moudle1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

import static android.R.attr.lines;

public class MainActivity extends Activity implements View.OnClickListener{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        initView();
        mChartView = (LineChartView)findViewById(R.id.chart);

        values = new ArrayList<PointValue>();//折线上的点
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));
        values.add(new PointValue(4, 2));
        values.add(new PointValue(5, 4));
        values.add(new PointValue(6, 3));
        values.add(new PointValue(7, 4));
        values.add(new PointValue(8, 2));
        values.add(new PointValue(9, 4));
        values.add(new PointValue(10, 3));
        values.add(new PointValue(11, 4));
        values.add(new PointValue(12, 2));
        values.add(new PointValue(13, 4));
        values.add(new PointValue(14, 3));
        values.add(new PointValue(15, 4));
        values.add(new PointValue(16, 2));
        values.add(new PointValue(17, 4));
        values.add(new PointValue(18, 3));
        values.add(new PointValue(19, 4));
        values.add(new PointValue(20, 2));
        values.add(new PointValue(21, 4));
        values.add(new PointValue(22, 3));
        values.add(new PointValue(23, 4));
        values.add(new PointValue(24, 2));
        values.add(new PointValue(25, 4));
        values.add(new PointValue(26, 3));
        values.add(new PointValue(27, 4));
        values.add(new PointValue(28, 2));
        values.add(new PointValue(29, 4));
        values.add(new PointValue(30, 3));
        values.add(new PointValue(31, 4));
        values.add(new PointValue(32, 2));
        values.add(new PointValue(33, 4));
        values.add(new PointValue(34, 3));
        values.add(new PointValue(35, 4));
        values.add(new PointValue(36, 4));
        values.add(new PointValue(37, 3));
        values.add(new PointValue(38, 4));
        values.add(new PointValue(39, 2));
        values.add(new PointValue(40, 4));
        values.add(new PointValue(41, 3));
        values.add(new PointValue(42, 4));
        values.add(new PointValue(43, 2));
        values.add(new PointValue(44, 4));
        values.add(new PointValue(45, 3));
        values.add(new PointValue(46, 4));
        values.add(new PointValue(47, 4));
        values.add(new PointValue(48, 3));
        values.add(new PointValue(49, 4));
        values.add(new PointValue(50, 2));
        values.add(new PointValue(51, 4));
        values.add(new PointValue(52, 3));
        values.add(new PointValue(53, 4));
        values.add(new PointValue(54, 4));
        values.add(new PointValue(55, 3));
        values.add(new PointValue(56, 4));
        values.add(new PointValue(57, 2));
        values.add(new PointValue(58, 4));
        values.add(new PointValue(59, 3));
        values.add(new PointValue(60, 4));

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
        v.left = 61 - 7;
        v.right = 61 - 1;
        mChartView.setCurrentViewport(v);

    }

    private void initView() {
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
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v == btnParam){
            intent.setClass(MainActivity.this,ParamSettingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
        }else if (v == btnViewResult){
            intent.setClass(MainActivity.this,ViewResultActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
        }else if (v == btnBlutoothSet){
            intent.setClass(MainActivity.this,DeviceListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
        }else if (v == btnCommitExam){
            intent.setClass(MainActivity.this,SubmitResultActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
        }else if (v == btnNextStudent){
//            intent.setClass(MainActivity.this,H5Activity.class);
//            startActivity(intent);
//            overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
        }

    }
}
