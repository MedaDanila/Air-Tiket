package com.example.airticket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airticket.Authorization.Authorization;

public class Splash extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        image = findViewById(R.id.imageSplash);
        logo = findViewById(R.id.textSplash);
        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplication(), Authorization.class));
                finish();
            }
        }, 3000);
    }
}


