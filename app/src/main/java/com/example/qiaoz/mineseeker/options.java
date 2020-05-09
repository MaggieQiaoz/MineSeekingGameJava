package com.example.qiaoz.mineseeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.qiaoz.mineseeker.model.mineDigger;
import com.example.qiaoz.mineseeker.model.optiondata;

//UI of option page.
public class options extends AppCompatActivity {


    private optiondata DATA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createOKButton();
        createRatioMineNunButton();
        createRadioSizeButtons();
        DATA=optiondata.getInstance();

        //Toast.makeText(this,""+savedRow+","+savedCol+","+savedMines,Toast.LENGTH_SHORT).show();
    }

    private void createOKButton() {
        Button but=(Button)findViewById(R.id.OKbutton);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int savedRow=getRow(options.this);
                int savedCol=getCol(options.this);
                int savedMines=getMineNum(options.this);
                updateGame(savedRow,savedCol,savedMines);
                finish();
            }
        });
    }

    private void updateGame(int R,int C,int M) {
        DATA.setRows(R);
        DATA.setCols(C);
        DATA.setNumMines(M);
    }

    private void createRatioMineNunButton() {
        RadioGroup group = (RadioGroup) findViewById(R.id.mines_num);
        int[] numMines = getResources().getIntArray(R.array.mines_num);
        for (int i = 0; i < numMines.length; i++) {
            final int numMine = numMines[i];

            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.num_mines, numMine));
            button.setTextColor(Color.WHITE);

            //set onclick callback
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(options.this, " you clicked " + numMine, Toast.LENGTH_SHORT).show();

                    saveMinesInstalled(numMine);}
            });
            //add to radio group
            group.addView(button);
            if(numMine==getMineNum(this)){
                button.setChecked(true);
            }
        }
    }

    private void createRadioSizeButtons () {
            RadioGroup group2 = (RadioGroup) findViewById(R.id.game_size);
            int[] numRows = getResources().getIntArray(R.array.game_size_row);
            int[] numCols = getResources().getIntArray(R.array.game_size_col);

            for (int i = 0; i < numRows.length; i++) {
                final int numRow = numRows[i];
                final int numCol = numCols[i];

                RadioButton button = new RadioButton(this);
                button.setText(getString(R.string.rows_by, numRow, numCol));
                button.setTextColor(Color.WHITE);

                //set onclick callback
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(options.this, " you clicked " + numRow, Toast.LENGTH_SHORT).show();
                        saveRowAndColInstalled(numRow,numCol);
                    }
                });
                //add to radio group
                group2.addView(button);
                if(numCol==getCol(this)&&numRow==getRow(this)){
                    button.setChecked(true);
                }
            }
        }

    private void saveRowAndColInstalled(int numRow, int numCol) {
        SharedPreferences pref1=this.getSharedPreferences("AppPrefs1",MODE_PRIVATE);
        SharedPreferences.Editor editor1=pref1.edit();
        editor1.putInt("Num of row",numRow);
        editor1.apply();

        SharedPreferences pref2=this.getSharedPreferences("AppPrefs2",MODE_PRIVATE);
        SharedPreferences.Editor editor2=pref2.edit();
        editor2.putInt("Num of col",numCol);
        editor2.apply();
    }

    private void saveMinesInstalled(int numMine) {
        SharedPreferences pref1=this.getSharedPreferences("AppPrefs3",MODE_PRIVATE);
        SharedPreferences.Editor editor1=pref1.edit();
        editor1.putInt("Num of mines",numMine);
        editor1.apply();
    }

    static public int getRow(Context context){
        SharedPreferences pref1=context.getSharedPreferences("AppPrefs1",MODE_PRIVATE);

        int defaultValue=context.getResources().getInteger(R.integer.default_row);
        return pref1.getInt("Num of row",defaultValue);
    }

    static public int getCol(Context context){
        SharedPreferences prefs=context.getSharedPreferences("AppPrefs2",MODE_PRIVATE);
        int defaultValue=context.getResources().getInteger(R.integer.default_col);

        return prefs.getInt("Num of col",defaultValue);

    }

    static public int getMineNum(Context context){
        SharedPreferences prefs=context.getSharedPreferences("AppPrefs3",MODE_PRIVATE);
        int defaultValue=context.getResources().getInteger(R.integer.default_num_mines);
        return prefs.getInt("Num of mines",defaultValue);

    }
    public static Intent makeIntent (Context context){
            return new Intent(context, options.class);
        }
    }
