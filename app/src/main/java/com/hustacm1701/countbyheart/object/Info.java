package com.hustacm1701.countbyheart.object;
/*
* 这是一个记录本软件基本信息的类，主要的作用就是从文件中读取一些基本的信息
* 包括：
*   总共打卡的天数
*   当前出题的难度等级
*   当前的出题数目
*   //往日错题库
*
* 在使用时,不允许自己创建Info，只可以获得即得对象
* 如果对难度级别和每天的题目数有所更改，不会在当天生效，只会在次日或者之后的日子中生效，经过测试通过
*
* */
import android.content.Context;
import android.content.SharedPreferences;
import com.hustacm1701.countbyheart.MyApplication;
import java.util.Calendar;

public class Info {
    private static Info info = null;
    private int punchDay;//打卡天数
    private int level;//难度等级
    private int taskNumber;//题目数量
    private int nextTaskNumber,nextLevel;
//    private ErrorLib errorLib;//往日错题库
    private SharedPreferences preferences = MyApplication.getContext().getSharedPreferences("info_data", Context.MODE_PRIVATE);
    private Info(){
        punchDay = preferences.getInt("punchDay",0);
        boolean change = preferences.getBoolean("change",false);
        if (!change){
            level    = preferences.getInt("level",1);
            taskNumber = preferences.getInt("taskNumber",30);
        }else {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            Calendar changeDay = Calendar.getInstance();
            changeDay.set(preferences.getInt("year",year),preferences.getInt("month",month),preferences.getInt("day",day),0,0,0);
            calendar.set(year,month,day,0,0,0);
            if (changeDay.before(calendar)){
                level    = preferences.getInt("nextLevel",1);
                taskNumber = preferences.getInt("nextTaskNumber",30);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("change",false);
//                把今天的日期再记录一下
                editor.putInt("leave",level);
                editor.putInt("taskNumber",taskNumber);
                editor.apply();
            }else {
                level    = preferences.getInt("level",1);
                taskNumber = preferences.getInt("taskNumber",30);
            }
        }
        nextLevel = preferences.getInt("nextLevel",level);
        nextTaskNumber = preferences.getInt("nextTaskNumber",taskNumber);
//        errorLib = ErrorLib.getInstance();
    }
//    for test
//    public Info(Calendar calendar){
//        punchDay = preferences.getInt("punchDay",0);
//        boolean change = preferences.getBoolean("change",false);
//        if (!change){
//            level    = preferences.getInt("level",1);
//            taskNumber = preferences.getInt("taskNumber",30);
//        }else {
//            int year = calendar.get(Calendar.YEAR);
//            int month = calendar.get(Calendar.MONTH);
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//            Calendar changeDay = Calendar.getInstance();
//            changeDay.set(preferences.getInt("year",year),preferences.getInt("month",month),preferences.getInt("day",day),0,0,0);
//            calendar.set(year,month,day,0,0,0);
//            if (changeDay.before(calendar)){
//                level    = preferences.getInt("nextLevel",1);
//                taskNumber = preferences.getInt("nextTaskNumber",30);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putBoolean("change",false);
//                editor.apply();
//            }else {
//                level    = preferences.getInt("level",1);
//                taskNumber = preferences.getInt("taskNumber",30);
//            }
//        }
//        errorLib = ErrorLib.getInstance();
//    }
    public static Info getInstance(){
        if (info==null){
            info = new Info();
        }
        return info;
    }

    public static Info getTodayInstance(){

        return new Info();
    }

    public int getPunchDay() {
        return punchDay;
    }

    public void setPunchDay(int punchDay) {
        this.punchDay = punchDay;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("punchDay",punchDay);
        editor.apply();
    }
    public void punch(){
        setPunchDay(punchDay+1);
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        nextLevel = level;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("nextLevel",level);
        Calendar calendar = Calendar.getInstance();
        editor.putInt("year",calendar.get(Calendar.YEAR));
        editor.putInt("month",calendar.get(Calendar.MONTH));
        editor.putInt("day",calendar.get(Calendar.DAY_OF_MONTH));
        editor.putBoolean("change",true);
        editor.apply();
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(int taskNumber) {
        nextTaskNumber = taskNumber;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("nextTaskNumber",taskNumber);
        Calendar calendar = Calendar.getInstance();
        editor.putInt("year",calendar.get(Calendar.YEAR));
        editor.putInt("month",calendar.get(Calendar.MONTH));
        editor.putInt("day",calendar.get(Calendar.DAY_OF_MONTH));
        editor.putBoolean("change",true);
        editor.apply();
    }

    public int getNextTaskNumber() {
        return nextTaskNumber;
    }

    public int getNextLevel() {
        return nextLevel;
    }
//    public ErrorLib getErrorLib() {
//        return errorLib;
//    }
}
