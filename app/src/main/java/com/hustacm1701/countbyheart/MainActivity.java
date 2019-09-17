package com.hustacm1701.countbyheart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hustacm1701.countbyheart.object.Info;
import com.hustacm1701.countbyheart.object.Today;
public class MainActivity extends AppCompatActivity {
    private final static String TAG = "main activity ";
    private TextView day_count,today_number,today_left,today_correct,today_precision,start;
    private Info info = Info.getInstance();
    private Today today = Today.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
//        toolbar 相关设置：
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        初始化相关的控件：
        day_count =(TextView) findViewById(R.id.day_count);
        day_count.setText(info.getPunchDay()+"");

        today_number = (TextView) findViewById(R.id.today_number);
        today_number.setText(info.getTaskNumber()+"");

        today_left =(TextView) findViewById(R.id.today_left);
        today_left.setText(today.getLeftNumber()+"");

        today_correct =(TextView) findViewById(R.id.today_correct);
        today_correct.setText(today.getCorrectNumber()+"");

        today_precision = findViewById(R.id.today_precision);
        today_precision.setText(today.getPrecision());

        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//          跳转到答题界面的逻辑
            }
        });
    }
//      设置menu 即setting
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.setting:
//                跳转到设置界面的逻辑
                break;
            default:
                break;
        }
        return true;
    }
}
