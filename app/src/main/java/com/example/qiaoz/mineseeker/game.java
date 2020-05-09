package com.example.qiaoz.mineseeker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiaoz.mineseeker.model.mineDigger;

import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.qiaoz.mineseeker.options.*;

//the game logic for the mine seeking game.
public class game {


    private Context context;
    private int ScansNum1=0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void gamelogic(Activity options,Button button,Button[][] buttons, mineDigger mine_digger, int row, int col, SoundPool sPool, int music1, int music2, int num_rows, int num_cols,int num_mines) {

        if (mine_digger.checkMine(row, col) == 1) {
            int newWidth = button.getWidth();
            int newWeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(options.getResources(), R.mipmap.mine);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newWeight, true);
            Resources resource = options.getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
            mine_digger.mineDigged(row, col, 2);
            ScansNum1++;
            mine_digger.setMineScaned(mine_digger.getMineScaned() + 1);
            sPool.play(music2, 1, 1, 0, 0, 1);
            TextView mineLeft=(TextView) options.findViewById(R.id.mineLeft);
            TextView ScanNum=(TextView) options.findViewById(R.id.ScanNum);
            updateUI(mine_digger,num_mines,ScansNum1,mineLeft,ScanNum);

            Vibrator vibe = (Vibrator) options.getSystemService(Context.VIBRATOR_SERVICE);
            if (vibe.hasVibrator()) { // Vibrator availability checking
                    vibe.vibrate(500);
            }

            for (int i = 0; i < num_rows; i++) {
                Button button2 = buttons[i][col];
                if (mine_digger.checkMine(i, col) == 3) {
                    String bar = button2.getText().toString();
                    if (bar != null) {
                        int msg = Integer.parseInt(bar) - 1;
                        button2.setText("" + msg);
                    }
                }
            }
            for (int i = 0; i < num_cols; i++) {
                Button button2 = buttons[row][i];
                if (mine_digger.checkMine(row, i) == 3) {
                    String bar = button2.getText().toString();
                    if (bar != null) {
                        int msg = Integer.parseInt(bar) - 1;
                        button2.setText("" + msg);
                    }
                }
            }
        }

        else if (mine_digger.checkMine(row, col) == 0) {
            int msg = 0;
            for (int i = 0; i < num_rows; i++) {
                Button button2 = buttons[i][col];
                    Animation shake = AnimationUtils.loadAnimation(options.getApplicationContext(), R.anim.shake);
                    button2.startAnimation(shake);
                if (mine_digger.checkMine(i, col) == 1) {
                    msg++;
                }
            }
            for (int i = 0; i < num_cols; i++) {
                Button button2 = buttons[row][i];
                    Animation shake = AnimationUtils.loadAnimation(options.getApplicationContext(), R.anim.shake);
                    button2.startAnimation(shake);
                if (mine_digger.checkMine(row, i) == 1) {
                    msg++;
                }
            }
            button.setText("" + msg);
            mine_digger.mineDigged(row, col, 3);
            ScansNum1++;
            sPool.play(music1, 1, 1, 0, 0, 1);
            TextView mineLeft=(TextView) options.findViewById(R.id.mineLeft);
            TextView ScanNum=(TextView) options.findViewById(R.id.ScanNum);
            updateUI(mine_digger,num_mines,ScansNum1,mineLeft,ScanNum);
        }

        else if (mine_digger.checkMine(row, col) == 2) {

            int msg = 0;
            for (int i = 0; i < num_rows; i++) {
                Button button2 = buttons[i][col];

                    Animation shake = AnimationUtils.loadAnimation(options.getApplicationContext(), R.anim.shake);
                    button2.startAnimation(shake);

                if (mine_digger.checkMine(i, col) == 1) {
                    msg++;
                }
            }
            for (int i = 0; i < num_cols; i++) {
                Button button2 = buttons[row][i];

                    Animation shake = AnimationUtils.loadAnimation(options.getApplicationContext(), R.anim.shake);
                    button2.startAnimation(shake);
                if (mine_digger.checkMine(row, i) == 1) {
                    msg++;
                }
            }
            button.setText("" + msg);
            mine_digger.mineDigged(row, col, 3);

            sPool.play(music1, 1, 1, 0, 0, 1);
        }
    }

    private void updateUI(mineDigger mine_digger, int num_mines, int ScansNum,TextView mineLeft, TextView ScanNum) {


        String msg1=String.format(Locale.getDefault(),
                "Found %d of %d mines.",mine_digger.getMineScaned(),num_mines);
        String msg2=String.format(Locale.getDefault(),
                "# Scans used: %d",ScansNum1);
        mineLeft.setText(msg1);
        ScanNum.setText(msg2);

    }
}
