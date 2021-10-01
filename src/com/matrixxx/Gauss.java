package com.matrixxx;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
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
        reducedEF(mat);
        mat.tulisMatrix();
        //GaussSolver(mat, "SPL");




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

    public static void GaussSolver(Matrix matrix, String write, String toFile, Save file)
    {
        int x = Kemungkinan(matrix);
        if (x==3){
            if (write == "SPL"){
                System.out.println("SPLTidak Memiliki Solusi!");
                toFile += "Tidak Memiliki Solusi!\n";
                file.write(toFile);
            }
            else {
                System.out.println("Polinom Interpolasi tidak dapat dicari untuk titik-titik ini!");
                toFile += "Polinom Interpolasi tidak dapat dicari untuk titik-titik ini!\n";
                file.write(toFile);
            }
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
            if (write == "SPL"){
                System.out.println("SOLUSI:");
                toFile += "\nSOLUSI:\n";
                for (int i=0; i< solusi.length; i++){
                    System.out.println("x"+(i+1)+" = "+solusi[i]);
                    toFile += "x"+(i+1)+" = "+solusi[i] +"\n";
                }
                file.write(toFile);
            }
            else if (write == "polinom"){
                System.out.println("Polinom interpolasi yang melewati titik-titik tersebut adalah:");
                toFile += "Polinom interpolasi yang melewati titik-titik tersebut adalah:\n";
                System.out.print("P(x) = ");
                toFile += "P(x) = ";
                String tanda;
                for (int i=0; i<solusi.length; i++){
                    if  (solusi[i]!=0){
                        if (solusi[i] < 0 ){
                            tanda = " - ";
                        }
                        else {
                            if (i == 0){
                                tanda = " ";
                            }
                            else {
                                tanda = " + ";
                            }
                        }
                        if (i!=0){
                            if (Math.abs(solusi[i]) != 1){
                                System.out.print(tanda + Math.abs(solusi[i]) + "x^" + i);
                                toFile += tanda + Math.abs(solusi[i]) + "x^" + i;
                            }
                            else {
                                System.out.print(tanda + "x^" + i);
                                toFile += tanda + "x^" + i;
                            }
                        }
                        else {
                            System.out.print(tanda + Math.abs(solusi[i]));
                            toFile += tanda + Math.abs(solusi[i]);
                        }
                    }
                    //System.out.print(solusi[i]);
                }
                toFile += "\n";
                System.out.println();
                boolean flag = true;
                double solve;
                Scanner sc = new Scanner(System.in);
                while (flag){
                    double sum = 0;
                    System.out.print("Masukkan nilai X yang ingin ditaksir (masukkan 999.999 jika ingin keluar): ");
                    toFile += "Masukkan nilai X yang ingin ditaksir (masukkan 999.999 jika ingin keluar): ";
                    solve = sc.nextDouble();
                    toFile += solve + "\n";
                    if (solve == 999.999){
                        System.out.println("--------------------KEMBALI KE MENU UTAMA-------------------------");
                        toFile += "--------------------KEMBALI KE MENU UTAMA-------------------------\n";
                        flag = true;
                        break;
                    }
                    else {
                        System.out.print("P("+solve+") = ");
                        toFile += "P("+solve+") = ";
                        for (int i = 0; i<solusi.length; i++){
                            sum += solusi[i]*Math.pow(solve, i);
                        }
                    }
                    System.out.println(sum);
                    toFile += sum + "\n";
                }
                file.write(toFile);
            }
        }
        else {
            //mencari row pertama dari bawah yang elemennya tidak 0 (solusi banyak)
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
            System.out.println(Arrays.deepToString(solusi));

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
            while(xParam < matrix.getCol()-countNonZero-1) {
                cc2 = matrix.getCol() - 2;
                while (cc2 >= 0 && xParam < matrix.getCol() - countNonZero - 1) {
                    if (!declared[cc2] && matrix.Mat[cr2][cc2] != 0) {
                        if (matrix.NonZeroElmt(cr2, matrix.getCol() - 2) == 1) {

                            declared[cc2] = true;
                            solusi[cc2][matrix.getCol() - countNonZero] = 1;
                            solusi[cc2][matrix.getCol() - countNonZero + 2] = matrix.Mat[cr2][matrix.getCol() - 1] / matrix.Mat[cr2][cc2];

                        } else if (cc2 != matrix.FirstNonZero(cr2) && matrix.fRowIsKolomAllZero(cr2 + 1, cc2)) {
                            declared[cc2] = true;
                            solusi[cc2][matrix.getCol() - countNonZero - 1] = 1;
                            solusi[cc2][matrix.getCol() - countNonZero + 1] = xParam;
                            xParam++;
                        }
                    }
                    cc2--;
                }
                cr2--;
            }



            System.out.println(Arrays.toString(declared));
            System.out.println(Arrays.deepToString(solusi));

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
                    toFile += "x"+(i+1)+" = ";
                    if (solusi[i][matrix.getCol()-countNonZero+2]!=0){
                        System.out.print(df.format(solusi[i][matrix.getCol()-countNonZero+2]));
                        toFile += df.format(solusi[i][matrix.getCol()-countNonZero+2]);
                    }

                    for (int j=0; j<=xParam-1;j++){
                        int output = 0;
                        if (solusi[i][j]!=0){
                            if (-(solusi[i][j])>0 && (output!=0 || solusi[i][matrix.getCol()-countNonZero+2]!=0)){
                                System.out.print("+");
                                toFile += "+";
                            }
                            if (-(solusi[i][j])==-1){
                                System.out.print("-");
                                toFile += "-";
                            }
                            if (Math.abs(solusi[i][j])!=1){
                                System.out.print(df.format(-(solusi[i][j])));
                                toFile += df.format(-(solusi[i][j]));
                            }
                            System.out.print(kons[j]);
                            toFile += kons[j];
                            output++;
                        }
                    }
                    System.out.println();
                    toFile += "\n";
                }
                else if (solusi[i][matrix.getCol()-countNonZero-1]==1 && solusi[i][matrix.getCol()-countNonZero]==0){
                    System.out.print("x"+(i+1)+" = "+kons[(int)solusi[i][matrix.getCol()-countNonZero+1]]);
                    toFile += "x"+(i+1)+" = "+kons[(int)solusi[i][matrix.getCol()-countNonZero+1]];
                    System.out.println();
                    toFile += "\n";
                }
                else {
                    System.out.print("x"+(i+1)+" = "+df.format(solusi[i][matrix.getCol()-countNonZero+2]));
                    toFile += "x"+(i+1)+" = "+df.format(solusi[i][matrix.getCol()-countNonZero+2]);
                    System.out.println();
                    toFile += "\n";
                }
            }


        }
    }

    public static double[] GaussSolverFunction(Matrix matrix){
        int x = Kemungkinan(matrix);
        double[] solusi = {};
        if (x==3){
            System.out.println("Memiliki banyak solusi");
            return solusi;
        }
        else if (x==1){
            solusi = new double[matrix.getCol()-1];
            for (int sol = 0; sol<matrix.getCol()-1; sol++){
                solusi[sol] = matrix.Mat[sol][matrix.getCol()-1];
            }
            for (int i = solusi.length-1; i >=0; i--){
                for (int j = matrix.getCol()-2; j > i; j--){
                    solusi[i] -= solusi[j]*matrix.Mat[i][j];
                }
            }
            return solusi;
        }
        else {
            char[] kons = "rstuvwxyz".toCharArray();
            return solusi;

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
            Matrix temp = matrix.copyMatrix();
            int[] sortedZero = Arrays.copyOf(arrayZero, arrayZero.length);
            Arrays.sort(sortedZero);
            if (!Arrays.equals(arrayZero, sortedZero)){
                for (int i = k; i< arrayZero.length; i++){
                    int x = 0;
                    System.out.println(arrayZero[i]);
                    while((arrayZero[i] != sortedZero[x])){
                        x++;
                    }
                    sortedZero[x] = matrix.getCol();
                    matrix.Mat[x] = temp.Mat[i];
                }
            }



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
        // System.out.println("Matriks Eselon adalah:");
        // System.out.println();
        // matrix.tulisMatrix();
    }

    public static void Rounder ( Matrix matrix, double constraint){
        for (int i=0; i<matrix.getRow(); i++){
            for (int j=0; j<matrix.getCol(); j++){
                if (Math.abs(matrix.Mat[i][j]) < constraint){
                    matrix.Mat[i][j] = 0;
                }
            }
        }
    }
//membuat matriks eselon tereduksi (eliminasi gauss Jordan)
    public static void reducedEF (Matrix mat){
        for (int i = mat.getRow()-1; i>0; i--){
            boolean flag = false;
            int j = 0;
            while (!flag && j< mat.getCol()){
                if (mat.Mat[i][j]==1){
                    for (int n = i-1; n>=0; n--){
                        double multiplier = -(mat.Mat[n][j]/mat.Mat[i][j]);
                        for (int m = 0; m< mat.getCol(); m++){
                            mat.Mat[n][m] += mat.Mat[i][m] * multiplier;
                        }
                    }
                    flag = true;
                }
                j++;
            }
        }
    }

}

