package com.hustacm1701.countbyheart;
/*
版权归XYZFromHust所有
主要功能：实现答题界面
功能介绍；
initView        初始化主界面
confirmAns      判断答案是否正确
slideCard       答题时滑动卡片
*/
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.hustacm1701.countbyheart.object.Task;
import com.hustacm1701.countbyheart.object.Today;
import com.hustacm1701.countbyheart.object.TodayLib;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private TextInputEditText answer;
    private TextView question,progress;
    private ImageView next,ok;
    private Toolbar toolbar;
    private Today today = Today.getInstance();
    private TodayLib lib = today.getTodayLib();
    private ArrayList<Task> tasks = lib.getTasks();
    private Task nowTask = null;
    public static AnswerActivity ansActivity = null;
    private long startTime = 0,endTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ansActivity = this;
        startTime = System.currentTimeMillis();
        initView();
    }

    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
        }

//        初始化控件
        answer = findViewById(R.id.task_answer);
        question = findViewById(R.id.task_question);
        next = findViewById(R.id.next);
        ok = findViewById(R.id.ok);
        progress = findViewById(R.id.progress);
        updateProgress();

        lib.randomAdd(today.getLeftNumber());

        nowTask = tasks.get(0);
        question.setText(nowTask.getContent());
        tasks.remove(0);

//        添加监听：
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmAns();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                today.addCompletedNumber(1);
                slideCard();
            }
        });

        answer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent event) {
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_ACTION_SEND
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())
                ){
                    confirmAns();
                }
                return true;
            }
        });
    }

    private void confirmAns(){
//                判断提交的答案是否正确
        String ans = answer.getText().toString().trim();
        if(ans.equals("")){
            Toast.makeText(AnswerActivity.this,"小朋友要输入答案哟~",Toast.LENGTH_SHORT).show();
            return;
        }
        int a = Integer.valueOf(ans);
        today.addCompletedNumber(1);
        if (!nowTask.isRight(a)){
            AlertDialog dialog = new AlertDialog.Builder(AnswerActivity.this)
                    .setIcon(R.drawable.error)
                    .setTitle("算错了~呜呜呜~")
                    .setMessage("正确答案是："+nowTask.getAnswer())
                    .setNegativeButton("知道啦", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            slideCard();
                        }
                    }).create();
            dialog.show();
        }else {
            today.addCorrectNumber(1);
        }
        slideCard();
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


    private void slideCard(){
        updateProgress();
//        滑动卡片
        if (today.getCompletedNumber() == today.getTaskNumber()){
            next.setClickable(false);
            ok.setClickable(false);
//          今天的任务已经完成的操作：
            endTime = System.currentTimeMillis();
            today.addUsedTime((endTime-startTime)/1000);
            Intent intent = new Intent(AnswerActivity.this, RecordActivity.class);
            startActivity(intent);
        }else {
            if (tasks.size()==0){
                lib.randomAdd(today.getLeftNumber());
            }
            nowTask = tasks.get(0);
            tasks.remove(0);
            question.setText(nowTask.getContent());
            answer.setText("");
        }
    }

    private void updateProgress(){
        progress.setText(""+today.getCompletedNumber()+"/"+today.getTaskNumber());
    }
//      点击屏幕外侧，隐藏键盘，取消焦点
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                hideKeyboard(ev,view,AnswerActivity.this);
                if (view!=null)
                    view.clearFocus();
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
    private void hideKeyboard(MotionEvent event, View view,Activity activity) {
        try {
            if (view != null) {
                int[] location = { 0, 0 };
                view.getLocationInWindow(location);
                int left = location[0], top = location[1], right = left
                        + view.getWidth(), bootom = top + view.getHeight();
                // 判断焦点位置坐标是否在空间内，如果位置在控件外，则隐藏键盘
                if (event.getRawX() < left || event.getRawX() > right
                        || event.getY() < top || event.getRawY() > bootom) {
                    // 隐藏键盘
                    IBinder token = view.getWindowToken();
                    InputMethodManager inputMethodManager = (InputMethodManager) activity
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(token,
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
