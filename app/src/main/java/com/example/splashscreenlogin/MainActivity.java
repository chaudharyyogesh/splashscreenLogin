package com.example.splashscreenlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splashscreenlogin.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private static int TIMEOUT=3000;

    Animation top,bottom;
    ImageView image;
    TextView title;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

//        binding animations
        top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottom=AnimationUtils.loadAnimation(this,R.anim.bottom_anim);
//        binding variables to animation
        image=findViewById(R.id.imageView);
        title=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView2);
//        setting animation
        image.setAnimation(top);
        title.setAnimation(bottom);
        slogan.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Loginactivity=new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Loginactivity);
                finish();
            }
        },TIMEOUT);
    }
}