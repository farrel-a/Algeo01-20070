package com.matrixxx;

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
        mat.isiMatrix();
        mat.tulisMatrix();
        System.out.println();


    }

    public boolean isFirstElmtZero(Matrix matrix){
        boolean flag = false;
        int i = 0;
        while (!flag && i<matrix.getCol()){
            if (matrix.Mat[i][0]==0.0){
                flag = true;
            }
            else {
                i++;
            }
        }
        return flag;
    }
}
