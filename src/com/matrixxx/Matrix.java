package com.matrixxx;

import java.util.Scanner;

public class Matrix {
    double[][] Mat;
    private int Row;
    private int Col;

    //KONSTRUKTOR
    Matrix (){}
    Matrix(int Row, int Col){
        this.Row = Row;
        this.Col = Col;
        this.Mat = new double[Row][Col];
    }
    //getter dan setter
    public int getRow(){
        return this.Row;
    }
    public int getCol(){
        return this.Col;
    }
    public void setRow(int Row){
        this.Row = Row;
    }

    public void setCol(int Col) {
        this.Col = Col;
    }

    //Prosedur isi matrix
    public void isiMatrix(){
        int i, j;
        Scanner input = new Scanner(System.in);
        for (i=0; i<this.Row; i++){
            for (j=0; j<this.Col; j++){
                this.Mat[i][j] = input.nextDouble();
            }
        }
    }
    //Prosedur tulis isi matrix
    public void tulisMatrix(){
        int i, j;
        for (i=0; i<this.Row; i++){
            for (j=0; j<this.Col; j++){
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println();
        }
    }
    public Matrix transpose(){
        int i, j;
        Matrix TMat = new Matrix(this.getCol(), this.getRow());
        for (i=0; i<this.Row; i++){
            for (j=0; j<this.Col; j++){
                TMat.Mat[j][i] = this.Mat[i][j];
            }
        }
        return TMat;
    }
    public void bagiKons(int row, double x){
        for (int j=0; j<this.Col; j++){
            this.Mat[row-1][j] = this.Mat[row-1][j]/x;
        }
    }
    public void tukarBaris(int row1, int row2){
        double[] temp = new double[this.Col];
        for (int i=0; i < this.Col; i++){
            temp[i] = this.Mat[row1][i];
            this.Mat[row1][i] = this.Mat[row2][i];
        }
        for (int i=0; i<this.Col; i++){
            this.Mat[row2][i] = temp[i];
        }
    }
    public double getElmt(int row, int col){
        return this.Mat[row][col];
    }


}
