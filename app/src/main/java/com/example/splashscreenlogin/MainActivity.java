package com.example.splashscreenlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    private static int TIMEOUT=2800;

    Animation top,bottom;
    ImageView image;
    TextView title;
    TextView slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  ="newuser";
            String channelName ="newuser";
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "You are subscribed as general.";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


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
                Intent intent = new Intent(MainActivity.this, LoginPage.class);

                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(image,"logo_image");
                pairs[1]=new Pair<View,String>(title,"logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                    finish();
                }
            }
        },TIMEOUT);
    }
}
