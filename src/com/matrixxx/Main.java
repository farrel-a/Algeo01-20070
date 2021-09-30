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

    public static void showSubSPLMenu()
    {
        System.out.println("1. Metode Eliminasi Gauss");
        System.out.println("2. Metode Eliminasi Gauss-Jordan");
        System.out.println("3. Metode Matriks Balikan");
        System.out.println("4. Kaidah Cramer");
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
            break;

            case 4:
            Interpolasi();
            break;

            case 5:
            mlr();
            break;

            case 6: break;

            default:
            System.out.println("Invalid Command");
            break;
        }

    }

    public static void mlr()
    {
        int c=0;
        showInputType();
        showCommand();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if (c==1) //keyboard input - mlr
        {
            Save file = new Save("regresi.txt");
            String content = "";
            MLR mlr = new MLR();
            Matrix mat = mlr.callInputMatrix();
            System.out.println("Matriks Regresi Linear Berganda: ");
            content+="Matriks Regresi Linear Berganda: \n";
            mat.tulisMatrix();
            content+=mat.tulisMatrixString();content+="\n";
            mat = mat.ElimMaju(mat);
            System.out.println("Matriks Eselon: ");
            content+="\nMatriks Eselon: \n";
            mat.tulisMatrix();
            System.out.println();
            content+=mat.tulisMatrixString(); content+="\n";

            double[] solusi = {};
            Gauss gauss = new Gauss();
            solusi = gauss.GaussSolverFunction(mat);

            int i;
            double[] x = new double[solusi.length];
            x[0] = 1;
            for(i = 1 ; i<x.length ; i++)
            {
                System.out.print("Masukkan X"+i+": ");
                x[i] = sc.nextDouble();
            }
            System.out.print("\ny = ");
            content += "\ny = ";
            for (i=0 ; i<solusi.length;i++)
            {
                if (i==0)
                {
                    String strsol = String.format("%.3f", solusi[i]);
                    System.out.print(strsol + " + ");
                    content += (strsol + " + ");
                }
                else
                {
                    String strsol = String.format("%.3f", solusi[i]);
                    System.out.print(strsol+" x"+(i));
                    content += strsol+" x"+(i);
                    if (i != solusi.length-1){System.out.print(" + ");
                       content+=" + "; }
                }

            }
            System.out.println("\n");
            content += "\n";

            double y = 0;
            for (i = 0; i<solusi.length;i++)
            {
                if (i==0) {y+=solusi[i];}
                else{
                    y = y + x[i]*solusi[i];
                }
            }
            for(i = 1 ; i<x.length ; i++)
            {
                System.out.println("X"+i +" = " + x[i]);
                content += ("X"+i +" = " + x[i]);
                content += "\n";
            }
            System.out.println("Y = " + y);
            content += ("Y = " + y);
            System.out.println();
            content += "\n";
            file.write(content);

        }
        else if (c==2) //file input - mlr
        {
            Save file = new Save("regresi.txt");
            String content = "";
            MLR mlr = new MLR();
            Matrix mat = mlr.callFileMatrix("..\\test\\mlrpoints.txt");
            double[] solusi;
            solusi = mlr.callFile("..\\test\\mlrpoints.txt");
            content+="Matriks Regeresi Linear Berganda: \n";
            content += mat.tulisMatrixString();
            mat = mat.ElimMaju(mat);
            content+="\nMatriks Eselon:\n";
            content+=mat.tulisMatrixString();
            int i;
            double[] x = new double[solusi.length];
            x[0] = 1;
            for(i = 1 ; i<x.length ; i++)
            {
                System.out.print("Masukkan X"+i+": ");
                x[i] = sc.nextDouble();
            }
            System.out.print("\ny = ");
            content += "\ny = ";
            for (i=0 ; i<solusi.length;i++)
            {
                if (i==0)
                {
                    String strsol = String.format("%.3f", solusi[i]);
                    System.out.print(strsol + " + ");
                    content += (strsol + " + ");
                }
                else
                {
                    String strsol = String.format("%.3f", solusi[i]);
                    System.out.print(strsol+" x"+(i));
                    content += strsol+" x"+(i);
                    if (i != solusi.length-1){System.out.print(" + ");
                       content+=" + "; }
                }

            }
            System.out.println("\n");
            content += "\n";

            double y = 0;
            for (i = 0; i<solusi.length;i++)
            {
                if (i==0) {y+=solusi[i];}
                else{
                    y = y + x[i]*solusi[i];
                }
            }
            for(i = 1 ; i<x.length ; i++)
            {
                System.out.println("X"+i +" = " + x[i]);
                content += ("X"+i +" = " + x[i]);
                content += "\n";
            }
            System.out.println("Y = " + y);
            content += ("Y = " + y);
            System.out.println();
            content += "\n";
            file.write(content);
        }
        else{System.out.println("Invalid Input");}
    }

    public static void cramer()
    {
        showInputType();
        showCommand();
        int c = 0;
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if (c==1)
        {
            int row,col;
            System.out.print("Masukkan jumlah baris: ");
            row = sc.nextInt();
            System.out.print("Masukkan jumlah kolom: ");
            col = sc.nextInt();
            if (row == col-1)
            {
                System.out.println("Masukkan Matriks: ");
                Matrix mat = new Matrix(row, col);
                mat.isiMatrix();
                Save file = new Save("CramerResult.txt");
                String content = "";
                Cramer cramer = new Cramer();
                double[] solusi = cramer.call(mat);

                System.out.println("Matriks: ");
                content+="Matriks: \n";

                mat.tulisMatrix();System.out.println();
                content+=mat.tulisMatrixString(); content+="\n";
                content+="\nSolusi:\n";
                for (int i = 0 ; i<solusi.length ; i++)
                {
                    System.out.println("X"+(i+1)+" = " +solusi[i]);
                    content+=("X"+(i+1)+" = " +solusi[i]+"\n");
                }
                file.write(content);
                System.out.println();
            }
            else
            {
                System.out.println("Tidak bisa menggunakan Kaidah Cramer");
            }
        }

        else if (c==2)
        {
            Matrix mat = new Matrix("..\\test\\matrix.txt");
            if (mat.getRow()==(mat.getCol()-1))
            {
                Save file = new Save("CramerResult.txt");
                String content = "";
                Cramer cramer = new Cramer();
                double[] solusi = cramer.call(mat);

                System.out.println("Matriks: ");
                content+="Matriks: \n";

                mat.tulisMatrix();System.out.println();
                content+=mat.tulisMatrixString(); content+="\n";
                content+="\nSolusi:\n";
                for (int i = 0 ; i<solusi.length ; i++)
                {
                    System.out.println("X"+(i+1)+" = " +solusi[i]);
                    content+=("X"+(i+1)+" = " +solusi[i]+"\n");
                }
                file.write(content);
                System.out.println();
            }
            else
            {
                System.out.println("Tidak bisa menggunakan Kaidah Cramer");
            }
        }

        else{
            System.out.println("Invalid Input");
        }
    }
    public static void ElimGauss(){
        int c;
        showInputType();
        showCommand();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if (c==1){ //keyboard input
            Save file = new Save("ElimGauss.txt");
            String content = "";

            System.out.println("Masukkan jumlah baris:");
            Scanner scanner = new Scanner(System.in);
            int baris = scanner.nextInt();
            System.out.println("Masukkan jumlah kolom:");
            int kolom = scanner.nextInt();
            System.out.println("Masukkan elemen matriks:");

            Matrix mat = new Matrix(baris, kolom);
            mat.isiMatrix();
            content += "Akan dicari penyelesaian SPL (Gauss) untuk matriks berikut ini \n";
            mat.tulisMatrix();
            System.out.println();
            Gauss.ElimMaju(mat);
            System.out.println("Matriks Eselon: ");
            content+="\nMatriks Eselon: \n";
            mat.tulisMatrix();
            System.out.println();
            content+=mat.tulisMatrixString(); content+="\n";
            Gauss.GaussSolver(mat, "SPL", content, file);

        }
    }

    public static void GaussJordan(){
        int c;
        showInputType();
        showCommand();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if (c==1){ //keyboard input
            Save file = new Save("ElimGaussJordan.txt");
            String content = "";
            Gauss gauss = new Gauss();

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

            Gauss.ElimMaju(mat);
            Gauss.reducedEF(mat);
            Gauss.GaussSolver(mat, "SPL", content, file);

        }
    }

    public static void SPL()
    {
        //SPL ISI DI SINI
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        showSubSPLMenu();
        showCommand();
        choice = sc.nextInt();

        switch (choice)
        {
            case 1:
            ElimGauss();
            break;

            case 2:
            GaussJordan();
            break;

            case 3:
            //SPL MATRIKS BALIKAN DI SINI
            break;

            case 4:
            //CRAMER DI SINI
            cramer();
            break;

            default:
            System.out.println("Invalid Input");
            break;

        }

    }
    public static void Interpolasi(){
        int c;
        showInputType();
        showCommand();
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        if (c==1){
            Save file = new Save("regresi.txt");
            String content = "";

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            scanner.close();

            Points points = new Points(n);
            points.readPoints();
            points.writePointstoFile(content);

            Matrix interMatrix;
            interMatrix = points.toMatrix();

            Gauss.ElimMaju(interMatrix);
            Gauss.GaussSolver(interMatrix, "polinom", content, file);

        }
    }

    public static void Inverse()
    {
        Save file = new Save("inverse.txt");
        String content = "";
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
            content += "Isi Matriks:\n";
            mat.tulisMatrix();
            content += mat.tulisMatrixString();
            content+="\n";
            Determinant determinant = new Determinant();
            double det = determinant.detReduction(mat);
            if (closeZero(det))
            {
                System.out.println("Matrix tidak bunya inverse/balikan");
                content+="Matriks tidak punya inverse/balikan";
            }
            else
            {
                System.out.println("\nMatrix Inverse/Balikan: ");
                content+="Matriks Inverse/Balikan:\n";
                mat = mat.inverse();
                mat.tulisMatrix();
                content+=mat.tulisMatrixString();
                content+="\n";
            }
            file.write(content);
        }

        else if (c==2) //file input - inverse
        {
            Matrix mat = new Matrix("..\\test\\matrix.txt");
            System.out.println("\nIsi Matriks:");
            content+="Isi Matriks:\n";
            mat.tulisMatrix();
            content+=mat.tulisMatrixString();content+="\n";
            Determinant determinant = new Determinant();
            double det = determinant.detCofactor(mat);
            if (closeZero(det))
            {
                System.out.println("Determinan = 0\nMatrix tidak bunya inverse/balikan");
                content+="Determinan = 0\nMatrix tidak bunya inverse/balikan\n";
            }
            else
            {
                System.out.println("\nMatrix Inverse/Balikan: ");
                content += "Matriks Inverse/Balikan:\n";
                mat = mat.inverse();
                mat.tulisMatrix();
                content+=mat.tulisMatrixString();
                content+="\n";
            }
            file.write(content);
        }
        else{System.out.println("Invalid input");}

    }

    public static boolean closeZero(double x)
    {
        return (-0.00000000000001 <= x && x <= 0.00000000000001);
    }

    public static void determinan()
    {
        String content="";
        Save file = new Save("determinan.txt");
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
                content += "Isi Matriks:\n";
                mat.tulisMatrix();
                content += mat.tulisMatrixString();
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                mat = determinant.ElimMajuDet(mat);
                System.out.println("Matriks Segitiga Atas:");
                content += "Matriks Segitiga Atas:\n";
                mat.tulisMatrix();
                content += mat.tulisMatrixString();
                content += "\n";
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet +"\n");
                content += "Determinan = " + strdet +"\n";

                file.write(content);

            }
            else if (c==2) //txt file input (matrix.txt) - reduction
            {
                Matrix mat = new Matrix("..\\test\\matrix.txt");
                content +="Isi Matriks:\n";
                System.out.println("\nIsi Matriks:");
                content += mat.tulisMatrixString();
                content += "\n";
                mat.tulisMatrix();
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                content += "Matriks Segitiga Atas:\n";
                mat = determinant.ElimMajuDet(mat);
                System.out.println("Matriks Segitiga Atas:");
                mat.tulisMatrix();
                content+=mat.tulisMatrixString();
                content+="\n";
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet+"\n");
                content+="Determinan = " + strdet+"\n";
                file.write(content);
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
                content += "Isi Matriks:\n";
                System.out.println("\nIsi Matriks:");
                mat.tulisMatrix();
                content += mat.tulisMatrixString();
                content += "\n";
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet +"\n");
                content += "Determinan = " + strdet +"\n";
                file.write(content);
            }
            
            else if (c==2) //file input - cofactor
            {
                Matrix mat = new Matrix("..\\test\\matrix.txt");
                content += "Isi Matriks:\n";
                System.out.println("\nIsi Matriks:");
                mat.tulisMatrix();
                content += mat.tulisMatrixString();
                Determinant determinant = new Determinant();
                double det = determinant.detReduction(mat);
                String strdet = String.format("%.3f",det);
                System.out.println("Determinan = " + strdet+"\n");
                content +="Determinan = " + strdet+"\n";
                file.write(content);
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
