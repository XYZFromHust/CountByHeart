package com.hustacm1701.countbyheart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hustacm1701.countbyheart.object.History;
import com.hustacm1701.countbyheart.object.Info;
import com.hustacm1701.countbyheart.object.Today;

public class RecordActivity extends AppCompatActivity {

    private Today today = Today.getInstance();
    private TextView punchDay,today_number, today_precision, today_time;
    private Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        AnswerActivity last = AnswerActivity.ansActivity;
        if (last!=null){
            last.finish();
        }

        init();
    }

    private void init(){
        fitWindows();
//      初始化控件：
        punchDay = findViewById(R.id.punch_day);
        today_precision = findViewById(R.id.today_precision);
        today_time = findViewById(R.id.today_time);
        today_number = findViewById(R.id.today_number);
        confirm = findViewById(R.id.confirm);
        today_number.setText(today.getTaskNumber()+"");
        today_time.setText((int)(today.getUsedTime()/60)+1+"分钟");
        today_precision.setText(today.getPrecision());
        today.setHasComplete();

        ImageView imageView = findViewById(R.id.image);

//        只有当准确率在60以上时才判定为打卡成功
        if (today.getPrecision_()>=60){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic1));
            History history = new History(today.getDate(),today.getPrecision_());
            history.save();
            Info info = Info.getInstance();
            info.punch();
            punchDay.setText(info.getPunchDay()+"");
        }else {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic3));
            punchDay.setText("打卡失败");
        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    protected void fitWindows(){
        Window window = getWindow();//设置系统栏是否适应的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
