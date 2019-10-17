package com.hustacm1701.countbyheart;
/*
版权归XYZFromHust所有
主要功能：实现历史答题情况界面
*/
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.hustacm1701.countbyheart.object.History;
import com.hustacm1701.countbyheart.object.Today;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HistoryActivity extends AppCompatActivity {
    private LineChart lineChart ;
    private List<String> dates = new ArrayList<>();
    private List<Float> precision = new ArrayList<>();
    private final static String TAG = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        init();
    }

    private void init(){
//          test
//        Random random = new Random();
//        for (int i = 0;i<2;i++){
//            History history = new History(2019+"/"+10+"/"+i*3+1,random.nextInt(100)+1);
//            history.save();
//        }
//        History history = new History("2019/10/5",60);
//        history.save();
//        history = new History("2019/10/6",80);
//        history.save();
//        history = new History("2019/10/4",90);
//        history.save();
//        history = new History("2019/10/10",20);
//        history.save();
//        history = new History("2019/10/1",80);
//        history.save();
//        history = new History("2019/10/2",30);
//        history.save();
//        history = new History("2019/10/3",45);
//        history.save();
//        history = new History("2019/10/11",60);
//        history.save();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
        }

        final List<History> histories = LitePal.findAll(History.class);
        histories.sort(new Comparator<History>() {
            @Override
            public int compare(History history, History t1) {
                return history.getCmp() - t1.getCmp();
            }
        });
//        Log.d(TAG, "init: "+todays.size());
        lineChart = findViewById(R.id.line_chart);

        List<Entry> entries = new ArrayList<>();
        for (int i = 0;i<histories.size();i++){
            entries.add(new Entry(i,histories.get(i).getPrecision()));
            dates.add(histories.get(i).getDate().substring(5));
        }
        Log.e(TAG, "init: "+dates.size() );
        LineDataSet set = new LineDataSet(entries,"data");
        set.setColor(Color.parseColor("#04BDA7"));
        set.setLineWidth(2f);
        lineChart.setData(new LineData(set));

//        lineChart设置
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setDragEnabled(true);
        lineChart.setTouchEnabled(true);
        lineChart.animateX(2500);
        lineChart.animateY(1500);
        lineChart.setScaleEnabled(false);
        lineChart.setNoDataText("没有打卡记录哦~");
        lineChart.setNoDataTextColor(R.color.colorPrimary);
        float ratio = dates.size()/7;
        lineChart.zoom(ratio,0,0,0);
        lineChart.setDescription(null);


        Legend legend = lineChart.getLegend();
        legend.setEnabled(false);


        XAxis xAxis = lineChart.getXAxis();
        xAxis.setLabelCount(Math.min(dates.size(),7));
        YAxis leftAxis = lineChart.getAxisLeft();
        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        xAxis.setDrawGridLines(false);
//        leftAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(100);

        LimitLine limitLine = new LimitLine(60f,"打卡临界点");
        limitLine.setLineWidth(1f);
        leftAxis.addLimitLine(limitLine);

        xAxis.setValueFormatter(new XAxisValueFormatter());
        xAxis.setGranularity(1);

        leftAxis.setValueFormatter(new YAxisValueFormatter());
        lineChart.invalidate();

    }

    private class XAxisValueFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            if (dates.size()==1)
                return dates.get(0);
            int position = (int) value;
            if (position < dates.size() && position >= 0) {
//                Log.e(TAG, "getFormattedValue: "+position+"dates:"+dates.size() );
                return dates.get(position);
            } else {
                return null;
            }
        }
    }
    private class YAxisValueFormatter extends ValueFormatter{
        @Override
        public String getFormattedValue(float value) {
            return (int)value+"%";
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}


















