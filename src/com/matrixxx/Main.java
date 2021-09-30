package com.matrixxx;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        do
        {
            showMainMenu();
            showCommand();
            choice = sc.nextInt();
            mainMenuRoute(choice);
        }
        while(choice != 6);
        sc.close();
    }

    public static void showMainMenu()
    {
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi Linear Berganda");
        System.out.println("6. Keluar");
    }

    public static void showCommand()
    {
        System.out.print(">>> ");
    }

    public static void mainMenuRoute(int c)
    {
        switch (c)
        {
            case 1 :
            SPL();
            break;

            case 2 :
            determinan();
            break;

            case 3:
            Inverse();

            case 6: break;

            default:
            System.out.println("Invalid Command");
            break;
        }

    }

    public static void SPL()
    {

    }

    public static void Inverse()
    {
        int c;
        showInputType();
        showCommand();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if (c==1) //keyboard input - inverse
        {
            int row,col;
            do{
                System.out.print("Masukkan Jumlah Baris: ");
                do
                {
                    row = sc.nextInt();
                }
                while(row<0);

                System.out.print("Masukkan Jumlah Kolom: ");
                do
                {
                    col = sc.nextInt();
                }
                while(col<0);

                if (row != col){System.out.println("Matrix harus N x N !");}
            }
            while(row != col);
            System.out.println("Silakan isi matrix: ");
            Matrix mat = new Matrix(row,col);
            mat.isiMatrix();
            System.out.println("\nIsi Matriks:");
            mat.tulisMatrix();
            Determinant determinant = new Determinant();
            double det = determinant.detReduction(mat);
            if (closeZero(det))
            {
                System.out.println("Matrix tidak bunya inverse/balikan");
            }
            else
            {
                System.out.println("\nMatrix Inverse/Balikan: ");
                mat = mat.inverse();
                mat.tulisMatrix();
            }

        }

        else if (c==2) //file input - inverse
        {
            Matrix mat = new Matrix("txt\\matrix.txt");
            System.out.println("\nIsi Matriks:");
            mat.tulisMatrix();
            Determinant determinant = new Determinant();
            double det = determinant.detCofactor(mat);
            if (closeZero(det))
            {
                System.out.println("Determinan = 0\nMatrix tidak bunya inverse/balikan");
            }
            else
            {
                System.out.println("\nMatrix Inverse/Balikan: ");
                mat = mat.inverse();
                mat.tulisMatrix();
            }
        }
        else{System.out.println("Invalid input");}

    }

    public static boolean closeZero(double x)
    {
        return (-0.00000000000001 <= x && x <= 0.00000000000001);
    }

    public static void determinan()
    {
        int c;
        detSubMenu();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if (c==1) //Metode Reduksi Baris
        {
            showInputType();
            showCommand();
            c = sc.nextInt();
            if (c==1) //Keyboard Input - reduction
            {
                int row,col;
                do{
                    System.out.print("Masukkan Jumlah Baris: ");
                    do
                    {
                        row = sc.nextInt();
                    }
                    while(row<0);

                    System.out.print("Masukkan Jumlah Kolom: ");
                    do
                    {
                        col = sc.nextInt();
                    }
                    while(col<0);

                    if (row != col){System.out.println("Matrix harus N x N !");}
                }
                while(row != col);
                System.out.println("Silakan isi matrix: ");
                Matrix mat = new Matrix(row,col);
                mat.isiMatrix();
                System.out.println("\nIsi Matriks:");
                mat.tulisMatrix();
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                mat = determinant.ElimMajuDet(mat);
                System.out.println("Matriks Segitiga Atas");
                mat.tulisMatrix();
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet +"\n");

            }
            else if (c==2) //txt file input (matrix.txt) - reduction
            {
                Matrix mat = new Matrix("txt\\matrix.txt");
                System.out.println("\nIsi Matriks:");
                mat.tulisMatrix();
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                System.out.println("Matriks Segitiga Atas");
                mat.tulisMatrix();
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet+"\n");
            }
            else{System.out.println("Invalid input");}
        }

        else if (c==2) //Metode Kofaktor
        {
            showInputType();
            showCommand();
            c = sc.nextInt();
            if (c==1) //keyboard input - cofactor
            {
                int row,col;
                do{
                    System.out.print("Masukkan Jumlah Baris: ");
                    do
                    {
                        row = sc.nextInt();
                    }
                    while(row<0);

                    System.out.print("Masukkan Jumlah Kolom: ");
                    do
                    {
                        col = sc.nextInt();
                    }
                    while(col<0);

                    if (row != col){System.out.println("Matrix harus N x N !");}
                }
                while(row != col);
                while(col<0);
                System.out.println("Silakan isi matrix: ");
                Matrix mat = new Matrix(row,col);
                mat.isiMatrix();
                System.out.println("\nIsi Matriks:");
                mat.tulisMatrix();
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet +"\n");
            }

            else if (c==2) //file input - cofactor
            {
                Matrix mat = new Matrix("txt\\matrix.txt");
                System.out.println("\nIsi Matriks:");
                mat.tulisMatrix();
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet+"\n");
            }

            else{System.out.println("Invalid Input");}
        }
        else{System.out.println("Invalid Input");}

    }

    public static void detSubMenu()
    {
        System.out.println("1. Metode Reduksi Baris");
        System.out.println("2. Metode Ekspansi Kofaktor");
        showCommand();
    }

    public static void showInputType()
    {
        System.out.println("Input Types:");
        System.out.println("1. Keyboard Input");
        System.out.println("2. File Input");
    }
}
