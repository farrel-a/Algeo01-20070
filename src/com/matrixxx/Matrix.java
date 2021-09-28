package com.matrixxx;

import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.io.File;  

public class Matrix {
    double[][] Mat;
    private int Row;
    private int Col;

    //KONSTRUKTOR
    Matrix (){      //konstruktor dengan file
        readMatrixFile();
    }
    Matrix(int Row, int Col){  //konstruktor tanpa file
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
    //ABAIKAN BUAT GW DEBUG AJA INI BIAR CEPET -NANDO
    public void isiOtomatis(){
        double tempMat [][] =  { {1,3,-2,0,2,0,0},
                {2,6,-5,-2,4,-3,-1},
                {0,0,5,10,0,15,5},
                {2,6,0,8,4,18,6}};
        this.Mat = tempMat;
    }
    public void isiOtomatis2(){
        double tempMat [][] =  {{8,1,3,2,0},
                {2,9,-1,-2,1},
                {1,3,2,-1,2},
                {1,0,6,4,3}};
        this.Mat = tempMat;
    }
    public void isiOtomatis3(){
        double tempMat [][] =  { {2,0,8,0,8},
                {0,1,0,4,6},
                {-4,0,6,0,6},
                {0,-2,0,3,-1},
                {2,0,-4,0,-4},
                {0,1,0,-2,0}};
        this.Mat = tempMat;
    }
    //Prosedur tulis isi matrix
    public void tulisMatrix(){
        int i, j;
        for (i=0; i<this.Row; i++){
            for (j=0; j<this.Col; j++){
                //String elem = String.format("%.3f", this.Mat[i][j]);
                System.out.print(this.Mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void readMatrixFile()
    {
        this.Row = 0;
        this.Col = 0;
        //membaca size matrix dari file
        try {
            File file = new File("txt\\matrix.txt");
            Scanner reader = new Scanner(file);
            int i = 0;
            while (reader.hasNextLine()) 
            {
                this.Row+=1;
                Scanner colReader = new Scanner(reader.nextLine());
                while(colReader.hasNextDouble())
                {
                    if (i == 0){this.Col+=1;}
                    colReader.nextDouble();
                }
                i += 1;
            }
            reader.close();
            } 
        catch (FileNotFoundException e) 
            {
                System.out.println("File not found.");
                e.printStackTrace();
            }

        //isi matrix
        this.Mat = new double[this.Row][this.Col];
        File file = new File("txt\\matrix.txt");
        try{
            Scanner rowReader = new Scanner(file);
            for (int i = 0 ; i<this.Row ; i++)
            {
                Scanner colReader = new Scanner(rowReader.nextLine());
                for (int j = 0 ; j<this.Col ; j++)
                {
                    double data = colReader.nextDouble();
                    this.Mat[i][j] = data;
                }
                colReader.close();
            }
            rowReader.close();
        }
        catch (FileNotFoundException e) 
            {
                System.out.println("File not found.");
                e.printStackTrace();
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
    public void kaliKons(int row, double x){
        for (int j=0; j<this.Col; j++){
            this.Mat[row-1][j] = this.Mat[row-1][j]*x;
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
    public int getIndexCol(int row, double x){
        int j = 0;
        boolean found = false;
        while (!found && j<this.getCol()){
            if (this.Mat[row][j]==x){
                found = true;
            }
        }
        return  j;
    }
    public boolean isOnRow (int row, double x){
        boolean flag = false;
        int j = 0;
        while (!flag && j<this.getCol()){
            if (this.Mat[row][j]== x){
                flag = true;
            }
        }
        return flag;
    }

    public void roundMatrix ( double constraint){
        for (int i=0; i<this.getRow(); i++){
            for (int j=0; j<this.getCol(); j++){
                if (Math.abs(this.getElmt(i, j)) < constraint){
                    this.Mat[i][j] = 0;
                }
            }
        }
    }
    public Matrix copyMatrix(){
        Matrix copyMat = new Matrix(this.Row, this.Col);
        for (int i=0; i<this.Row; i++){
            for (int j = 0; j<this.Col; j++){
                copyMat.Mat[i][j] = this.Mat[i][j];
            }
        }
        return copyMat;
    }
    public boolean isRowAllZero(int row){
        int j = 0;
        boolean flag = true;
        if (this.Mat[row][j] != 0){
            flag = false;
        }
        while (j<this.getCol()&& flag){
            if (this.Mat[row][j] != 0){
                flag = false;
            }
            else{
                j++;
            }
        }
        return flag;
    }

    public Matrix matCofactor()
    {
        Matrix mc = new Matrix(this.getRow(), this.getCol());
        int a = 0, b = 0; //index m1
        int multiply = 1;
        Determinant det = new Determinant();
        double deter;
        for (int i = 0;i<this.getRow();i++)
        {
            if (i%2==0){multiply = 1;}
            else{multiply = -1;}
            for (int j = 0;j<this.getCol();j++)
            {
                Matrix m1 = new Matrix(this.getRow()-1, this.getCol()-1);
                for (int c = 0 ; c < this.getRow() ; c++)
                {
                    for (int d = 0 ; d < this.getCol() ; d++)
                    {
                        if (c!=i && d!=j)
                        {
                            m1.Mat[a][b] = this.getElmt(c, d);
                            if (b+1<m1.getCol()){b++;}
                            else if (a+1<m1.getRow()){a++;b=0;}
                        }
                    }
                }
                a = 0;b=0;
                deter = det.detCofactor(m1);
                mc.Mat[i][j] = multiply*deter;
                multiply *=-1;
            }
        }
        return mc;
    }

    public Matrix adj()
    {
        Matrix madj;
        madj = this.matCofactor();
        return madj.transpose();
    }

    public Matrix inverse()
    {
        Determinant det = new Determinant();
        double deter = det.detCofactor(this);
        Matrix Minverse = this.adj();
        for (int i=0 ; i < Minverse.getRow() ; i++)
        {
            for (int j=0 ; j < Minverse.getCol() ; j++)
            {
                Minverse.Mat[i][j]*=(1/deter);
            }
        }
        return Minverse;
    }
    public int FirstNonZero (int i){
        int j = 0;
        if (this.Mat[i][j]==0){
            while (this.Mat[i][j] == 0 && j<this.Col){
                j++;
            }
        }
        if (j==this.Col){
            return -1;
        }
        else {
            return j;
        }
    }


}
