package com.hustacm1701.countbyheart;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {
    private ImageView imageView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        imageView = findViewById(R.id.start_view);

//        动画设置：
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f,1.0f);
//        alphaAnimation.setDuration(1000);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                startActivity(new Intent(StartActivity.this,MainActivity.class));
//                finish();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        imageView.startAnimation(alphaAnimation);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView,"alpha",0.0f,0.8f,1.0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView,"scaleX",1.0f,1.2f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView,"scaleY",1.0f,1.2f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animator1,animator2,animator3);
        set.setDuration(1000);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }
        });
        set.start();

        Window window = getWindow();//设置系统栏是否适应的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
