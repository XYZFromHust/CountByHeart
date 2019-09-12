package com.hustacm1701.countbyheart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.hustacm1701.countbyheart.object.Calculator;
import com.hustacm1701.countbyheart.object.Info;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "main activity ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
