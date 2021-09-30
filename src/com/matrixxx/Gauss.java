package com.matrixxx;

import java.text.DecimalFormat;
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
        mat.isiOtomatis4();
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
            System.out.println("Tidak Memiliki Solusi!");
        }
        else if (x==1 /*|| x == 2*/){
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
            //mencari row pertama dari bawah yang elemennya tidak 0
            int idxNotZero = matrix.getRow()-1;
            while (matrix.isRowAllZero(idxNotZero)&&idxNotZero>=0){
                idxNotZero--;
            }
            int countNonZero = idxNotZero+1;
            System.out.println(idxNotZero);
            // Solusi disimpan dalam bentuk array 2d dengan jml baris = jml variabel yang dicari
            //dan kolom adalah jumlah baris yang elemennya tidak nol pada matriks eselon + 4.
            //kolom 1 = apakah variabel tersebut dijadikan solusi parametrik?
            //kolom 2 = apakah variabel tersebut memiliki solusi tunggal?
            //kolom 3 = urutan variabel parametrik
            //kolom 4 = nilai solusi tunggal
            // *benar == 1, salah == 0 (untk kolom 1 & 2)

            double[][] solusi = new double[matrix.getCol()-1][matrix.getCol()-countNonZero+3];
            boolean[] declared = new boolean[matrix.getCol()-1];
            System.out.println(Arrays.toString(declared));

            int xParam = 0;
            int cr2 = countNonZero-1;
            int cc2;

            //kolom yang isinya nol semua pasti  variabel pada kolom tersebut parametrik
            for (int j = 0; j< matrix.getCol()-1;j++){
                if (matrix.isKolomAllZero(j)){
                    declared[j] = true;
                    solusi[j][matrix.getCol()-countNonZero-1] = 1;
                    solusi[j][matrix.getCol()-countNonZero+1] = xParam;
                    xParam++;
                }
            }

            //mencari variabel lain yang juga merupakan variabel parametrik
            while(xParam < matrix.getCol()-countNonZero-1){
                cc2 = matrix.getCol()-2;
                while(cc2>=0&&xParam< matrix.getCol()-countNonZero-1){
                    if(declared[cc2]==false&&matrix.Mat[cr2][cc2]!=0){
                        if(matrix.NonZeroElmt(cr2, matrix.getCol()-2)==1){

                            declared[cc2] = true;
                            solusi[cc2][matrix.getCol()-countNonZero] = 1;
                            solusi[cc2][matrix.getCol()-countNonZero+2] = matrix.Mat[cr2][matrix.getCol()-1]/matrix.Mat[cr2][cc2];

                        }
                        else if (cc2!= matrix.FirstNonZero(cr2)&& matrix.fRowIsKolomAllZero(cr2+1,cc2)){
                            declared[cc2] = true;
                            solusi[cc2][matrix.getCol()-countNonZero-1]=1;
                            solusi[cc2][matrix.getCol()-countNonZero+1] = xParam;
                            xParam++;
                        }
                    }
                    cc2--;
                }
                cc2--;
            }
            System.out.println(Arrays.toString(declared));

            cr2 = countNonZero-1;
            cc2 = 0;
            for (int i=cr2;i>=0;i--) {
                cc2 = matrix.FirstNonZero(i);
                if (!declared[cc2]){
                    declared[cc2] = true;
                    solusi[cc2][matrix.getCol()-countNonZero+2] = matrix.Mat[i][matrix.getCol()-1];
                    System.out.println(Arrays.deepToString(solusi));
                    for (int k= matrix.getCol()-2; k>=cc2+1 ;k--){

                        if (solusi[k][matrix.getCol()-countNonZero-1]==0 && solusi[k][matrix.getCol()-countNonZero]==0){
                            for (int j=0; j<=xParam-1; j++){
                                solusi[cc2][j] += solusi[k][j] * -(matrix.Mat[i][k]);
                            }
                            solusi[cc2][matrix.getCol()-countNonZero+2] += solusi[k][matrix.getCol()-countNonZero+2]*-(matrix.Mat[i][k]);
                        }

                        else if (solusi[k][matrix.getCol()-countNonZero-1]==1 && solusi[k][matrix.getCol()-countNonZero]==0){
                            solusi[cc2][(int)solusi[k][matrix.getCol()-countNonZero+1]]+=matrix.Mat[i][k];
                        }

                        else{
                            solusi[cc2][matrix.getCol()-countNonZero+2] -= solusi[k][matrix.getCol()-countNonZero+2]*matrix.Mat[i][k];
                        }

                    }


                }
            }
            System.out.println(Arrays.toString(declared));
            System.out.println(Arrays.deepToString(solusi));
            //tulis hasil solusi
            DecimalFormat df = new DecimalFormat("#.##");
            String[] kons =  {"r","s","t","u","v","w","x","y","z"};
            System.out.println("SPL Memiliki Solusi Banyak (Parametrik) yang memenuhi: ");
            for (int i = 0; i<= matrix.getCol()-2; i++){
                if(solusi[i][matrix.getCol()-countNonZero-1]==0 && solusi[i][matrix.getCol()-countNonZero]==0){
                    System.out.print("x"+(i+1)+" = ");
                    if (solusi[i][matrix.getCol()-countNonZero+2]!=0){
                        System.out.print(df.format(solusi[i][matrix.getCol()-countNonZero+2]));
                    }

                    for (int j=0; j<=xParam-1;j++){
                        int output = 0;
                        if (solusi[i][j]!=0){
                            if (-(solusi[i][j])>0 && (output!=0 || solusi[i][matrix.getCol()-countNonZero+2]!=0)){
                                System.out.print("+");
                            }
                            if (-(solusi[i][j])==-1){
                                System.out.print("-");
                            }
                            if (Math.abs(solusi[i][j])!=1){
                                System.out.print(df.format(-(solusi[i][j])));
                            }
                            System.out.print(kons[j]);
                            output++;
                        }
                    }
                    System.out.println();
                }
                else if (solusi[i][matrix.getCol()-countNonZero-1]==1 && solusi[i][matrix.getCol()-countNonZero]==0){
                    System.out.print("x"+(i+1)+" = "+kons[(int)solusi[i][matrix.getCol()-countNonZero+1]]);
                    System.out.println();
                }
                else {
                    System.out.print("x"+(i+1)+" = "+df.format(solusi[i][matrix.getCol()-countNonZero+2]));
                    System.out.println();
                }
            }


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
