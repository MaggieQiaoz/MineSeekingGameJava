package com.example.qiaoz.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.qiaoz.mineseeker.model.mineDigger;

//main page of this game, could navigate to game page, option page and help page.
public class MainActivity extends AppCompatActivity {

    //private static int SPLASH_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setStartButton();
        setOptionButton();
        setHelpButton();

        ImageView imageView = findViewById(R.id.imageView2);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.spin);
        imageView.startAnimation(animation1);


    }

    private void setOptionButton() {
        Button but = (Button) findViewById(R.id.optionButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = options.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setHelpButton() {
        Button but = (Button) findViewById(R.id.helpButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = helpScreen.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void setStartButton() {
        Button but = (Button) findViewById(R.id.startButton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MineDigging.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context,congratulations.class);
    }
}
