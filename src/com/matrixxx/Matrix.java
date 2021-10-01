package com.matrixxx;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;

public class Matrix {
    double[][] Mat;
    private int Row;
    private int Col;
    int tukar; //jumlah penukaran

    //KONSTRUKTOR
    Matrix (String path){      //konstruktor dengan file
        readMatrixFile(path);
        this.tukar = 0;
    }
    Matrix(int Row, int Col){  //konstruktor tanpa file
        this.Row = Row;
        this.Col = Col;
        this.Mat = new double[Row][Col];
        this.tukar = 0;
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

    public int getTukar()
    {
        return this.tukar;
    }

    //Prosedur isi matrix
    public void isiMatrix(){
        int i, j;
        Scanner input = new Scanner(System.in);
        for (i=0; i<this.Row; i++){
            for (j=0; j<this.Col; j++){
                // System.out.print("Masukkan Baris ke-"+(i+1)+" Kolom ke-"+(j+1));
                this.Mat[i][j] = input.nextDouble();
                // System.out.println();
            }
        }
    }
    //ABAIKAN BUAT GW DEBUG AJA INI BIAR CEPET -NANDO
    public void isiOtomatis(){
        double tempMat [][] =  { {8,1,3,2,0},
                {2,9,-1,-2,1},
                {1,3,2,-1,2},
                {1,0,6,4,3}};
        this.Mat = tempMat;
    }
    public void isiOtomatis2(){
        double tempMat [][] =  {{1,-1,0,0,1,3},
                {1,1,0,-3,0,6},
                {2,-1,0,1,-1,5},
                {-1,2,0,-2,-1,-1}};
        this.Mat = tempMat;
    }
    public void isiOtomatis3(){
        double tempMat [][] =  { {0,1,0,0,1,0,2},
                {0,0,0,1,1,0,-1},
                {0,1,0,0,0,1,1}};
        this.Mat = tempMat;
    }
    public void isiOtomatis4(){
        double tempMat [][] =  { {1,-1,2,-1,-1},
                {2,1,-2,-2,-2},
                {-1,2,-4,1,1},
                {3,0,0,-3,-3}};
        this.Mat = tempMat;
    }
    //Prosedur tulis isi matrix
    public void tulisMatrix(){
        int i, j;
        for (i=0; i<this.Row; i++){
            for (j=0; j<this.Col; j++){
                String elem = String.format("%.3f", this.Mat[i][j]);
                System.out.print(elem + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String tulisMatrixString(){
        int i, j;
        String s ="";
        for (i=0; i<this.Row; i++){
            for (j=0; j<this.Col; j++){
                String elem = String.format("%.3f", this.Mat[i][j]);
                s += (elem + " ");
            }
            s += "\n";
        }
        s += "\n";

        return s;
    }

    public void readMatrixFile(String path)
    {
        this.Row = 0;
        this.Col = 0;
        //membaca size matrix dari file
        try {
            File file = new File(path);
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
        File file = new File("..\\test\\matrix.txt");
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
        this.tukar+=1;
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
        if (this.getRow() == 1 && this.getCol() ==1){
            this.Mat[0][0]=1; return this;}
        else{
        Matrix madj;
        madj = this.matCofactor();
        return madj.transpose();}
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
    public boolean isKolomAllZero (int col){
        int i = 0;
        while (i<this.Row){
            if (this.Mat[i][col] != 0){
                return false;
            }
            i++;
        }
        return true;

    }
    public boolean fRowIsKolomAllZero (int row, int col){
        boolean flag = true;
        int i = row;
        while (flag && i<this.Row){
            if (this.Mat[i][col]!=0){
                flag = false;
            }
            i++;
        }
        return flag;
    }
    public int NonZeroElmt(int n, int m){
        int count = 0;
        for(int i=0;i<=m;i++){
            if (this.Mat[n][i]!=0){
                count++;
            }
        }
        return count;
    }


    public int Kemungkinan (Matrix matrix){
        int i = matrix.getRow()-1;
        if (matrix.isRowAllZero(i)){
            while (matrix.isRowAllZero(i)&&i>=0){
                i--;
            }
        }
        //mencari index pertama yg bukan 0 pada baris
        int x = matrix.FirstNonZero(i);
        if (x == matrix.getCol()-1){
            return 3;
        }
        else {
            int row = 0;
            int flag = matrix.FirstNonZero(row);
            // System.out.println(flag);
            if (flag == -1){
                return 2;
            }
            if (matrix.Mat[row][flag] != 0){
                while (row< matrix.getRow()&&matrix.Mat[row][flag]!=0&&flag< matrix.getCol()){
                    row++;
                    flag++;
                }
                // System.out.println(row);
                // System.out.println(flag);
                if (row == matrix.getCol()-1){
                    return 1;
                }
                else{
                    return 2;
                }
            }
            else {
                return 2;
            }
        }
    }


    public Matrix ElimMaju(Matrix matrix){
        int col = matrix.getCol();
        int row = matrix.getRow();

        for (int k=0; k<row; k++){
            //mencari dan mengurutkan row yang memiliki nol dari kiri
            // int max = 0;
            int [] arrayZero = new int[matrix.getRow()];
            for (int i = k; i< matrix.getRow(); i++){
                int j = 0;
                if (matrix.Mat[i][j] == 0){
                    while (matrix.Mat[i][j]==0 && j< matrix.getCol()){
                        j++;
                        if(j>= matrix.getCol()){
                            break;
                        }
                    }
                }

                arrayZero[i] = j;
            }
            //mengurutkan matriks dari menjadi matriks eselon
            // System.out.println(Arrays.toString(arrayZero));
            // System.out.println();
            Matrix temp = matrix.copyMatrix();
            // temp.tulisMatrix();
            // System.out.println();
            int[] sortedZero = Arrays.copyOf(arrayZero, arrayZero.length);
            Arrays.sort(sortedZero);
            // System.out.println(Arrays.toString(arrayZero));
            // System.out.println(Arrays.toString(sortedZero));
            if (!Arrays.equals(arrayZero, sortedZero)){
                for (int i = k; i< arrayZero.length; i++){
                    int x = 0;
                    // System.out.println(arrayZero[i]);
                    while((arrayZero[i] != sortedZero[x])){
                        x++;
                    }

                    // System.out.println(Arrays.toString(sortedZero));
                    // System.out.println(i);
                    // System.out.println(x);
                    sortedZero[x] = matrix.getCol();
                    matrix.Mat[x] = temp.Mat[i];
                    matrix.tukar += 1;
                    // System.out.println(Arrays.toString(sortedZero));
                }
            }

            // matrix.tulisMatrix();
            // System.out.println();

/*            int IdxMax = k;
            int ElMax = (int)matrix.getElmt(k, k);
            for (int i=k+1; i< row; i++){
                if (Math.abs(matrix.Mat[i][k] )> ElMax){
                    ElMax = (int)matrix.Mat[i][k];
                    IdxMax = i;
                }
            }
            if (IdxMax != k){
                matrix.tukarBaris(k, IdxMax);
            }*/
            if (col>row){
                for (int i = k+1; i<row; i++){
                    double divider;
                    int tempRow = k;
                    //scan index  row pertama yang bukan 0
                    if (matrix.Mat[k][tempRow] == 0){
                        while (matrix.Mat[k][tempRow] == 0 && tempRow< matrix.getRow()){
                            tempRow++;
                            if (tempRow>= temp.getRow()){
                                break;
                            }
                        }
                    }
                    if (matrix.Mat[k][tempRow] != 0){
                        divider = matrix.Mat[i][tempRow]/matrix.Mat[k][tempRow];
                        for (int  j = k+1; j<col; j++){
                            // System.out.println(matrix.Mat[i][j]);
                            // System.out.print(matrix.Mat[k][j] + " ");
                            // System.out.println(divider);
                            matrix.Mat[i][j] -= matrix.Mat[k][j] * divider;
                            Rounder(matrix, 1e-9);
                            // System.out.println(matrix.Mat[i][j]);
                            // System.out.println();
                        }
                    }
                    matrix.Mat[i][k] = 0;
                }
            }
            else {
                for (int i = k+1; i<col; i++){
                    double divider;
                    int tempRow = k;
                    //scan index  row pertama yang bukan 0
                    if (matrix.Mat[k][tempRow] == 0){
                        while (matrix.Mat[k][tempRow] == 0 && tempRow< matrix.getCol()){
                            tempRow++;
                            if (tempRow>= temp.getRow()){
                                break;
                            }
                        }
                    }
                    if (matrix.Mat[k][tempRow] != 0){
                        divider = matrix.Mat[i][tempRow]/matrix.Mat[k][tempRow];
                        for (int  j = 0; j<col; j++){
                            // System.out.println(matrix.Mat[i][j]);
                            // System.out.print(matrix.Mat[k][j] + " ");
                            // System.out.println(divider);
                            matrix.Mat[i][j] -= matrix.Mat[k][j] * divider;
                            Rounder(matrix, 1e-9);
                            // System.out.println(matrix.Mat[i][j]);
                            // System.out.println();
                        }
                    }
                    matrix.Mat[i][k] = 0;
                }

            }

        }

        for (int r = 0; r< matrix.getRow(); r++){
            int c = 0;
            if (matrix.Mat[r][c] == 0){
                while (matrix.Mat[r][c]==0 && c< matrix.getCol()){
                    c++;
                    if (c>= matrix.getCol()){
                        break;
                    }
                }
            }

            if (c< matrix.getCol()){
                double divider = matrix.Mat[r][c];
                for (int tcol = 0; tcol< matrix.getCol(); tcol++){
                    matrix.Mat[r][tcol]=matrix.Mat[r][tcol]/divider;
                }
            }
            //else berarti barisnya isinya 0 semua
        }
        Rounder(matrix, 1e-9);
        return matrix;
    }

    public void Rounder ( Matrix matrix, double constraint){
        for (int i=0; i<matrix.getRow(); i++){
            for (int j=0; j<matrix.getCol(); j++){
                if (Math.abs(matrix.Mat[i][j]) < constraint){
                    matrix.Mat[i][j] = 0;
                }
            }
        }
    }


}
