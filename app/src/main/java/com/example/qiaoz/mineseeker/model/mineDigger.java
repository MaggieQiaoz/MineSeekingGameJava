package com.example.qiaoz.mineseeker.model;

import android.content.Context;
import android.inputmethodservice.Keyboard;

import com.example.qiaoz.mineseeker.options;

import java.util.Random;

//save data in this mine dinning game and able to pass it in game activity class

public class mineDigger {
    Context myContext;
    private int numMines=6;
    private int rows=4;
    private int cols=6;
    private int mineScaned=0;
    private int[][] mines;
    private optiondata DATA;

    private static mineDigger instance;
    private mineDigger(){
        //private to stop anyone to instantiating.
    }

    public static mineDigger getInstance(){
        if(instance==null){
            instance=new mineDigger();
        }
        return instance;
    }

    public int getMineScaned() {
        return mineScaned;
    }

    public void mineDigged(int row,int col,int Const){
        mines[row][col]=Const;
    }

    public void setMines(int row,int col) {
        mines=new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mines[i][j] = 0;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getNumMines() {
        return numMines;
    }

    public void setNumMines(int n) {
        numMines = n;
    }

    public void setCols(int cols) {
        instance.cols = cols;
    }

    public void setRows(int rows) {
        instance.rows = rows;
    }

    public void setMineScaned(int mineScaned) {
        instance.mineScaned = mineScaned;
    }

    public int checkMine(int row, int col) {
        return mines[row][col];
    }

    public void updateMine(int row, int col, int numMine) {
        instance.setRows(row);
        instance.setCols(col);
        instance.setNumMines(numMine);
        instance.setMines(row,col);
        instance.setMineScaned(0);
        for (int i = 0; i < numMine; i++) {
            Random rn = new Random();
            int R = rn.nextInt(row);
            int C = rn.nextInt(col);
            if (instance.mines[R][C] != 1) {instance.mines[R][C] = 1; }
            else { i--; }
        }
    }

}

