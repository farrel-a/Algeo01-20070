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
 /*       //file reader testing
        //Matrix mat = new Matrix(); //tidak butuh argumen untuk konstruktor dengan file
        mat.readMatrixFile();
        Matrix mat = new Matrix(); //tidak butuh argumen untuk konstruktor dengan file
        mat.tulisMatrix();
        System.out.println(mat.getRow());
        System.out.println(mat.getCol());

        //Determinant tester
        Determinant det = new Determinant();
        det.call(1, mat);

        //adjoint tester
        Matrix mat2;
        mat2 = mat.adj();
        mat2.tulisMatrix();

        System.out.println();

        //Mat Cofactor tester
        Matrix mat3;
        mat3 = mat.matCofactor();
        mat3.tulisMatrix();

        System.out.println();

        //Mat Inverse tester
        Matrix mat4;
        mat4 = mat.inverse();
        mat4.tulisMatrix();

        //Points tester user input
        Points P = new Points(3);
        P.readPoints();
        P.writePoints();

        System.out.println();

        //Points tester file input
        Points P2 = new Points();
        P2.writePoints();


        // System.out.println("Masukkan jumlah baris:");
        // Scanner scanner = new Scanner(System.in);
        // int baris = scanner.nextInt();
        // System.out.println("Masukkan jumlah kolom:");
        // int kolom = scanner.nextInt();
        // System.out.println("Masukkan elemen matriks:");
        System.out.println(mat.getCol());*/
        Matrix mat = new Matrix(baris, kolom);
        mat.isiOtomatis2();
        mat.tulisMatrix();
        System.out.println();
        Gauss.ElimMaju(mat);

        // Matrix mat = new Matrix(baris, kolom);
        // mat.isiMatrix();
        // mat.tulisMatrix();
        // System.out.println();
        // Matrix newMat = new Matrix();
        // newMat = mat.transpose();
        // newMat.tulisMatrix();
        // System.out.println();
        // mat.bagiKons(2,0.10);
        // mat.tulisMatrix();
        // System.out.println();
        // mat.tukarBaris(1, 2);
        // mat.tulisMatrix();
        // scanner.close();



    }
}
