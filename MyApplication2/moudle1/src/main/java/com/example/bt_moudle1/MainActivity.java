package com.example.bt_moudle1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

import static android.R.attr.lines;

public class MainActivity extends AppCompatActivity {


//    Intro.this.overridePendingTransition(2130968586, 2130968587);
//    Intro.this.finish();

    LineChartView mChartView;
    List<PointValue> values;
    List<Line> lines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChartView = (LineChartView)findViewById(R.id.chart);

        values = new ArrayList<PointValue>();//折线上的点
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));

        Line line = new Line(values).setColor(Color.BLUE);//声明线并设置颜色
        line.setCubic(false);//设置是平滑的还是直的
        lines = new ArrayList<Line>();
        lines.add(line);

        mChartView.setInteractive(true);//设置图表是可以交互的（拖拽，缩放等效果的前提）
        mChartView.setZoomType(ZoomType.HORIZONTAL_AND_VERTICAL);//设置缩放方向
        LineChartData data = new LineChartData();
        Axis axisX = new Axis();//x轴
        Axis axisY = new Axis();//y轴
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);
        data.setLines(lines);
        mChartView.setLineChartData(data);//给图表设置数据

    }
}
