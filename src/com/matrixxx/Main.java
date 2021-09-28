package com.matrixxx;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Masukkan jumlah baris:");
        Scanner scanner = new Scanner(System.in);
        int baris = scanner.nextInt();
        System.out.println("Masukkan jumlah kolom:");
        int kolom = scanner.nextInt();
        System.out.println("Masukkan elemen matriks:");
        //file reader testing
        //Matrix mat = new Matrix(); //tidak butuh argumen untuk konstruktor dengan file
       /* mat.readMatrixFile();
        mat.tulisMatrix();
        System.out.println(mat.getRow());
        System.out.println(mat.getCol());*/
        Matrix mat = new Matrix(baris, kolom);
        mat.isiOtomatis2();
        mat.tulisMatrix();
        System.out.println();
        Gauss.ElimMaju(mat);





    }
}
