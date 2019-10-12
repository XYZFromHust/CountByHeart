package com.hustacm1701.countbyheart.object;
/*
* Today类用来记录今天的task相关信息，并且继承自LitePalSupport,可以将数据存储到数据库
* Today包含的信息：
*   今天的时间ID--date
*   今天的题库
*   //错误题库
*   难度级别
*   task数目
*   已经完成的数目
*   正确的数目
*   错误的数目
*   准确度
*   今天任务是否完成
*
* 另外完成了一些接口定义，具体可以查看相应的函数
* emmm只是初步定义，后面要是有问题再改
* */
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.hustacm1701.countbyheart.MyApplication;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;
import java.util.Calendar;

public class Today{
    private static final String TAG = "Today ";
    private Calendar date_ = Calendar.getInstance();//当前的日期实例
    private TodayLib todayLib;//今天的题目库
//    private ErrorLib errorLib;//错误的题库，从Info中获得
//    @Column(ignore = true)
    private SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("today_data", Context.MODE_PRIVATE);
    private SharedPreferences.Editor editor = preferences.edit();
    private boolean hasComplete;
    private static Today today;
//      需要写进数据库的数据
    private String date = date_.get(Calendar.YEAR)+"/"+date_.get(Calendar.MONTH)+"/"+date_.get(Calendar.DAY_OF_MONTH);//日期实例的字符串形式
    private int level;//难度级别
    private int taskNumber;//任务数目
    private int completedNumber;//已完成数目
    private int correctNumber;//正确数目
    private int errorNumber;//错误数目
    private long usedTime;//已经使用的时间
    private float precision;//准确度



    private Today(){
        Info info = Info.getInstance();
        int year = date_.get(Calendar.YEAR);
        int month =date_.get(Calendar.MONTH);
        int day = date_.get(Calendar.DAY_OF_MONTH);
        date_.set(year,month,day,0,0,0);
        Calendar last = Calendar.getInstance();
        last.set(preferences.getInt("year",year),preferences.getInt("month",month),preferences.getInt("day",day),0,0,0);
        if (date_.after(last)) {
//            不同的日期
            completedNumber = 0;
            correctNumber = 0;
            errorNumber = 0;
            usedTime = 0;
            precision = 0;
            hasComplete = false;
            editor.putInt("completedNumber",0);
            editor.putInt("correctNumber",0);
            editor.putInt("errorNumber",0);
            editor.putLong("usedTime",0);
            editor.putFloat("precision",0);
            editor.putBoolean("hasComplete",false);
            editor.apply();
        }else {
            completedNumber = preferences.getInt("completedNumber",0);
            correctNumber = preferences.getInt("correctedNumber",0);
            errorNumber = preferences.getInt("errorNumber",0);
            usedTime = preferences.getLong("usedTime",0);
            precision = preferences.getFloat("precision",0);
            hasComplete = preferences.getBoolean("hasComplete",false);
        }
        level = info.getLevel();
        taskNumber = info.getTaskNumber();
//        加载lib
        todayLib = TodayLib.getInstance(taskNumber-completedNumber);
    }
    public static Today getInstance(){
        if (today==null){
            today = new Today();
        }
        return today;
    }

    public Calendar getDate_() {
        return date_;
    }

    public String getDate() {
        return date;
    }

    public int getLevel() {
        return level;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getCompletedNumber() {
        return completedNumber;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public long getUsedTime() {
        return usedTime;
    }

    public String getPrecision() {
        setPrecision(); // 修改无法自动更改的问题
        int x =(int) (precision*100);
        return x+"%";
    }
    public int getPrecision_() {
        return (int)(precision*100);
    }

    public TodayLib getTodayLib() {
        return todayLib;
    }

//    public ErrorLib getErrorLib() {
//        return errorLib;
//    }

    public void addCorrectNumber(int add){
        correctNumber+=add;
        if (correctNumber>completedNumber){
            Log.e(TAG, "addCorrectNumber: 当前正确task大于总task");
            correctNumber-=add;
        }else {
            editor.putInt("correctedNumber",correctNumber);
            editor.apply();
        }
    }
    public void subCorrectNumber(int sub){
        correctNumber-=sub;
        if (correctNumber<0){
            Log.e(TAG, "subCorrectNumber: 当前正确task小于0");
            correctNumber+=sub;
        }else {
            editor.putInt("correctedNumber",correctNumber);
            editor.apply();
        }
    }
    public void addErrorNumber(int add){
        errorNumber+=add;
        if (errorNumber>completedNumber){
            Log.e(TAG, "addErrorNumber: 当前错误task大于总task");
            errorNumber-=add;
        }else {
            editor.putInt("errorNumber",errorNumber);
            editor.apply();
        }
    }
    public void subErrorNumber(int sub){
        errorNumber-=sub;
        if (errorNumber<0){
            Log.e(TAG, "subErrorNumber: 当前错误task小于0");
            errorNumber += sub;
        }else {
            editor.putInt("errorNumber",errorNumber);
            editor.apply();
        }
    }

    public void addCompletedNumber(int add){
        completedNumber+=add;
        if (completedNumber>taskNumber){
            Log.e(TAG, "addCompletedNumber: 当前完成task大于总task");
            completedNumber-=add;
        }else {
            editor.putInt("completedNumber",completedNumber);
            editor.apply();
        }
    }

    public void subCompletedNumber(int sub){
        completedNumber-=sub;
        if (completedNumber<0){
            Log.e(TAG, "subCompletedNumber: 当前完成task小于0");
            completedNumber+=sub;
        }else {
            editor.putInt("completedNumber",completedNumber);
            editor.apply();
        }
    }

    public void setPrecision(){
        if (completedNumber==0)
            return;
        precision =1.0F * correctNumber / completedNumber;
        editor.putFloat("precision",precision);
        editor.apply();
    }

    public int getLeftNumber(){
        return taskNumber - completedNumber;
    }

    public void addUsedTime(long add){
        usedTime+=add;
        editor.putLong("usedTime",usedTime);
        editor.apply();
    }
    public void addTaskNumber(int add){
        taskNumber+=add;
    }

    public boolean isHasComplete() {
        return hasComplete;
    }

    public void setHasComplete() {
        hasComplete = true;
        editor.putBoolean("hasComplete",hasComplete);
        int year = date_.get(Calendar.YEAR);
        int month =date_.get(Calendar.MONTH);
        int day = date_.get(Calendar.DAY_OF_MONTH);
        editor.putInt("year",year);
        editor.putInt("month",month);
        editor.putInt("day",day);
        editor.apply();
    }
}
