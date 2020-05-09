package com.example.qiaoz.mineseeker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qiaoz.mineseeker.model.mineDigger;
import com.example.qiaoz.mineseeker.model.optiondata;

import java.util.Locale;
import java.util.ResourceBundle;

//application page UI of the mine digging game
public class MineDigging extends AppCompatActivity {


    private mineDigger mine_digger;
    private optiondata DATA;
    private game g1=new game();
    private int num_rows=4;
    private int num_cols=6;
    private int num_mines=6;
    private int ScansNum=0;
    private SoundPool sPool;
    private int music1;
    private int music2;
    Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_digging);
        mine_digger=mineDigger.getInstance();
        DATA=optiondata.getInstance();
        update();
        buttons=new Button[num_rows][num_cols];
        mine_digger.updateMine(DATA.getRows(),DATA.getCols(),DATA.getNumMines());
        sPool=new SoundPool(10, AudioManager.STREAM_SYSTEM,5);
        music1=sPool.load(this,R.raw.click,1);
        music2=sPool.load(this,R.raw.right,1);

        String startMessage=String.format(Locale.getDefault(),
                "Found %d of %d mines.",mine_digger.getMineScaned(),num_mines);
        TextView textView=(TextView)findViewById(R.id.mineLeft) ;
        textView.setText(startMessage);
        populateButtons();
    }

    private void update() {
        this.num_rows=DATA.getRows();
        this.num_cols=DATA.getCols();
        this.num_mines=DATA.getNumMines();
    }

    private void populateButtons() {
        TableLayout table=(TableLayout)findViewById(R.id.tableForButtons);
        for(int row = 0; row<num_rows; row++){
            TableRow tableRow= new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);
            for(int col = 0; col<num_cols; col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;
                Button but=new Button(this);
                but.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                but.setPadding(0,0,0,0);
                but.setOnClickListener(new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_ROW,FINAL_COL);
                    }
                });
                tableRow.addView(but);
                buttons[row][col]=but;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void gridButtonClicked(int row, int col) {
        Button button=buttons[row][col];

        //lock button size
        lockButtonSizes();

        //only work in jelly bean
        g1.gamelogic(this,button,buttons,mine_digger,row,col,sPool,music1,music2,num_rows,num_cols,num_mines);
        if(mine_digger.getMineScaned()==this.num_mines){
            android.support.v4.app.FragmentManager manager=getSupportFragmentManager();
            congratulations dialog =new congratulations();
            dialog.show(manager,"MessageDialog");

            Log.i("TAG","Jst showed the dialog.");

        }
    }

    private void lockButtonSizes() {
        for(int row=0;row<num_rows;row++){
            for(int col=0;col<num_cols;col++){
                Button button=buttons[row][col];

                int width=button.getWidth();
                button.setMinimumWidth(width);
                button.setMaxWidth(width);

                int height= button.getHeight();
                button.setMinimumHeight(height);
                button.setMaxHeight(height);

            }
        }
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context,MineDigging.class);
    }

}
