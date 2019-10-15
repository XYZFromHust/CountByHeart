package com.hustacm1701.countbyheart.object;

import java.util.ArrayList;

public abstract class TaskLib {
    ArrayList<Task> tasks = null;
//    添加题目
    abstract public void add(int number);
//    返回tasks
    abstract public ArrayList<Task> getTasks();
}
