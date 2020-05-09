package com.example.qiaoz.mineseeker;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

//UI of welcome page, could be skiped by button.
public class welcomePage extends AppCompatActivity {

    private boolean clicked = false;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        ImageView imageView = findViewById(R.id.welcome);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);

        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin);
        AnimationSet s = new AnimationSet(false);//false means don't share interpolators
        s.addAnimation(animation1);
        s.addAnimation(animation2);
        imageView.startAnimation(s);
        timer = new Timer();
        setSkipButton();

    }

    private void setSkipButton() {
        final Button button = (Button) findViewById(R.id.button);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }};
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicked = true;
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    timer.cancel();
                    finish();
                }
            });
        timer.schedule(task, 10000);
    }
}
