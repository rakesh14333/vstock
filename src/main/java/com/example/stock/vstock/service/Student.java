package com.example.stock.vstock.service;

public class Student {
    public int getMarks(){
        return 100;
    }
    public int getAverage(){
        return 25;
    }
    public int getTotal(){
        return 200;
    }

    public int[] getSummary(){
        int[] value={this.getMarks(),this.getAverage(),this.getTotal()};
        return value;
    }
}
