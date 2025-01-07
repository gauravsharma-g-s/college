package com.example.android.college;

import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Hiding the Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_splash_screen);
        ImageView collegeLogo = findViewById(R.id.logo);
        Animation scaleAnim = AnimationUtils.loadAnimation(this,R.anim.move);
        collegeLogo.setAnimation(scaleAnim);
        Intent i = new Intent(SplashScreen.this,MainActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(i);
            finish();
        },4000);
    }
}