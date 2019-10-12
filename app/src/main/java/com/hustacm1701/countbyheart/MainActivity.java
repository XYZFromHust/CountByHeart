package com.hustacm1701.countbyheart;
/*
版权归XYZFromHust所有
主要功能：实现主界面布局
*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hustacm1701.countbyheart.object.Info;
import com.hustacm1701.countbyheart.object.Today;
public class MainActivity extends AppCompatActivity {
    private final static String TAG = "main activity ";
    private TextView day_count,today_number,today_left,today_correct,today_precision,start;
    private Info info = Info.getInstance();
    private Today today = Today.getInstance();
    private ImageView calendar ;
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
        today_number = (TextView) findViewById(R.id.today_number);
        today_left =(TextView) findViewById(R.id.today_left);
        today_correct =(TextView) findViewById(R.id.today_correct);
        today_precision = findViewById(R.id.today_precision);
        start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (today.isHasComplete()){
                    Toast.makeText(MainActivity.this,"今天的学习任务都完成啦~",Toast.LENGTH_SHORT).show();
                    return;
                }
//          跳转到答题界面的逻辑
                Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(intent);
            }
        });
        calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HistoryActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        day_count.setText(info.getPunchDay()+"");
        today_number.setText(info.getTaskNumber()+"");
        today_left.setText(today.getLeftNumber()+"");
        today_correct.setText(today.getCorrectNumber()+"");
        today_precision.setText(today.getPrecision());

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
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
}
