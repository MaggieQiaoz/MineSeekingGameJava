package com.example.qiaoz.mineseeker.model;


//save data that is selected in option page and able to use in multiple games.
public class optiondata {
    private int numMines=6;
    private int rows=4;
    private int cols=6;
    private static optiondata data;
    private optiondata(){
        //private to stop anyone to instantiating.
    }

    public static optiondata getInstance(){
        if(data==null){
            data=new optiondata();
        }
        return data;
    }

    public int getRows() {
        return data.rows;
    }

    public int getCols() {
        return data.cols;
    }

    public int getNumMines() {
        return data.numMines;
    }

    public void setRows(int rows) {
        data.rows = rows;
    }

    public void setCols(int cols) {
        data.cols = cols;
    }

    public void setNumMines(int numMines) {
        data.numMines = numMines;
    }
}
