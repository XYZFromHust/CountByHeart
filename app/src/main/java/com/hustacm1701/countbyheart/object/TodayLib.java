package com.hustacm1701.countbyheart.object;
/*
* TodayLib用来生成今天的题库
* 包括两种方式：
*   根据随机数生成题库
*   根据用户的输入生成题库
*同样采用单例设计模式
* */
import java.util.ArrayList;
import java.util.List;

public class TodayLib {
    private static TodayLib todayLib = null;
    private ArrayList<Task> tasks = null;
    private int maxSize;
    private int level = Info.getInstance().getLevel();
    private TodayLib(int maxSize){
        tasks = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public static TodayLib getInstance(int size){
        if (todayLib==null)
            todayLib=new TodayLib(size);
        return todayLib;
    }

    public void randomAdd(int number){
        int n = Math.min(number,maxSize-tasks.size());
        for (int i=0;i<number;i++){
            tasks.add(new Task(level));
        }
    }

    public void stringAdd(List<String> strings){
        int n =tasks.size();
        for (String string:strings){
            if (n==maxSize){
                break;
            }
            tasks.add(new Task(string));
            n++;
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

