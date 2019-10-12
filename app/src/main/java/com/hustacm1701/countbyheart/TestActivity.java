package com.hustacm1701.countbyheart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hustacm1701.countbyheart.object.Task;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        int level = 1;
        for (int i = 0;i<100;i++){
            Task task = new Task(6);
            System.out.println(task.toString()+task.getAnswer());
        }
    }
}
