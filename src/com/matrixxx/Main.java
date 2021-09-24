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

        Matrix mat = new Matrix(baris, kolom);
        mat.isiMatrix();
        mat.tulisMatrix();
        System.out.println();
        Matrix newMat = new Matrix();
        newMat = mat.transpose();
        newMat.tulisMatrix();
        System.out.println();
        mat.bagiKons(2,0.10);
        mat.tulisMatrix();
        System.out.println();
        mat.tukarBaris(1, 2);
        mat.tulisMatrix();



    }
}
