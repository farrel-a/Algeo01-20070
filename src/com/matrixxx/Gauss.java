package com.matrixxx;

import java.util.Arrays;
import java.util.Scanner;

public class Gauss {
    public static void main(String[] args) {
        System.out.println("Masukkan jumlah baris:");
        Scanner scanner = new Scanner(System.in);
        int baris = scanner.nextInt();
        System.out.println("Masukkan jumlah kolom:");
        int kolom = scanner.nextInt();
        System.out.println("Masukkan elemen matriks:");

        Matrix mat = new Matrix(baris, kolom);
        mat.isiOtomatis3();
        mat.tulisMatrix();
        System.out.println();

        ElimMaju(mat);

        GaussSolver(mat);




    }
     public static int Kemungkinan (Matrix matrix){
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
            System.out.println(flag);
            if (flag == -1){
                return 2;
            }
            if (matrix.Mat[row][flag] != 0){
                while (row< matrix.getRow()&&matrix.Mat[row][flag]!=0&&flag< matrix.getCol()){
                    row++;
                    flag++;
                }
                System.out.println(row);
                System.out.println(flag);
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

     public static void GaussSolver(Matrix matrix){
        int x = Kemungkinan(matrix);
        if (x==3){
            System.out.println("Memiliki banyak solusi");
        }
        else if (x==1){
            double[] solusi = new double[matrix.getCol()-1];
            for (int sol = 0; sol<matrix.getCol()-1; sol++){
                solusi[sol] = matrix.Mat[sol][matrix.getCol()-1];
            }
            for (int i = solusi.length-1; i >=0; i--){
                for (int j = matrix.getCol()-2; j > i; j--){
                    solusi[i] -= solusi[j]*matrix.Mat[i][j];
                }
            }
            System.out.println("SOLUSI:");
            for (int i=0; i< solusi.length; i++){
                System.out.println("x"+(i+1)+" = "+solusi[i]);
            }
        }
        else {
            char[] kons = "rstuvwxyz".toCharArray();

        }
     }



    public static void ElimMaju(Matrix matrix){
        int col = matrix.getCol();
        int row = matrix.getRow();



        for (int k=0; k<row; k++){
            //mencari dan mengurutkan row yang memiliki nol dari kiri
            int max = 0;
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
            System.out.println(Arrays.toString(arrayZero));
            System.out.println();
            Matrix temp = matrix.copyMatrix();
            temp.tulisMatrix();
            System.out.println();
            int[] sortedZero = Arrays.copyOf(arrayZero, arrayZero.length);
            Arrays.sort(sortedZero);
            System.out.println(Arrays.toString(arrayZero));
            System.out.println(Arrays.toString(sortedZero));
            if (!Arrays.equals(arrayZero, sortedZero)){
                for (int i = k; i< arrayZero.length; i++){
                    int x = 0;
                    System.out.println(arrayZero[i]);
                    while((arrayZero[i] != sortedZero[x])){
                        x++;
                    }

                    System.out.println(Arrays.toString(sortedZero));
                    System.out.println(i);
                    System.out.println(x);
                    sortedZero[x] = matrix.getCol();
                    matrix.Mat[x] = temp.Mat[i];
                    System.out.println(Arrays.toString(sortedZero));
                }
            }

            matrix.tulisMatrix();
            System.out.println();
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
                            System.out.println(matrix.Mat[i][j]);
                            System.out.print(matrix.Mat[k][j] + " ");
                            System.out.println(divider);
                            matrix.Mat[i][j] -= matrix.Mat[k][j] * divider;
                            Rounder(matrix, 1e-9);
                            System.out.println(matrix.Mat[i][j]);
                            System.out.println();
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
                            System.out.println(matrix.Mat[i][j]);
                            System.out.print(matrix.Mat[k][j] + " ");
                            System.out.println(divider);
                            matrix.Mat[i][j] -= matrix.Mat[k][j] * divider;
                            Rounder(matrix, 1e-9);
                            System.out.println(matrix.Mat[i][j]);
                            System.out.println();
                        }
                    }
                    matrix.Mat[i][k] = 0;
                }

            }

            matrix.tulisMatrix();
            System.out.println();

        }
        matrix.tulisMatrix();
        System.out.println();
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
       /* for (int r = 0; r< matrix.getRow(); r++){
            double divider = matrix.Mat[r][r];
            for (int c = 0; c< matrix.getCol(); c++){
                matrix.Mat[r][c] = matrix.Mat[r][c]/divider;
            }
        }*/
        Rounder(matrix, 1e-9);
        System.out.println("Matriks Eselon adalah:");
        System.out.println();
        matrix.tulisMatrix();
    }




   /* public static  double[] BackSub (Matrix matrix){
        int unknown = matrix.getCol();
        double [] result = new double[unknown];

    }*/
    public static void Rounder ( Matrix matrix, double constraint){
        for (int i=0; i<matrix.getRow(); i++){
            for (int j=0; j<matrix.getCol(); j++){
                if (Math.abs(matrix.Mat[i][j]) < constraint){
                    matrix.Mat[i][j] = 0;
                }
            }
        }
    }
}
