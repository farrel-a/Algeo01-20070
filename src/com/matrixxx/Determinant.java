package com.matrixxx;

import java.util.Arrays;
import java.lang.Math;

public class Determinant {
    public void call(int choice, Matrix mat) //1 = kofaktor, 2 = reduksi baris
    {
        if (choice == 1)
        {
            double det = detCofactor(mat);
            String strdet = String.format("%.3f",det);  //3 decimals floating point
            System.out.println("Determinan = " + strdet);
        }

        else if (choice == 2)
        {
            
            double det = detReduction(mat);
            String strdet = String.format("%.3f",det);  //3 decimals floating point
            System.out.println("Determinan = " + strdet);
        }
    }

    public double detCofactor(Matrix m)
    {
        int row = m.getRow();
        int col = m.getCol();
        double det = 0,res;
        if (row==1 && col==1)   //base 1
        {
            double a = m.getElmt(0, 0);
            return a;
        }
    
        else if (row==2 && col==2) //base 2
        {
            double a;
            a = ((m.getElmt(0, 0)*m.getElmt(1, 1)) - (m.getElmt(0, 1)*m.getElmt(1, 0)));
            return a;
        }

        else                            //rekurens
        {
            
            int i;
            for (i=0;i<col;i++)
            {
                if (i%2==0)
                {
                    Matrix m1 = new Matrix(row-1, col-1);
                    int a,b,c,d;
                    c = 0;d = 0;
                    for (a=0;a<row;a++)
                    {
                        for (b=0;b<col;b++)
                        {
                            if (!(a==0 || b==i))
                            {
                                m1.Mat[c][d] = m.Mat[a][b];
                                if (d<m1.getCol()-1){d+=1;}
                                else{c+=1;d=0;}
                               
                            }
                        }
                    }
                    // res = ELMT(m,0,i)*determinant(m1);
                    res = m.Mat[0][i]*detCofactor(m1);
                    det+= res;
                }
    
                else
                {
                    Matrix m1;
                    m1 = new Matrix(row-1, col-1);
                    int a,b,c,d;
                    c = 0;d = 0;
                    for (a=0;a<row;a++)
                    {
                        for (b=0;b<col;b++)
                        {
                            if (!(a==0 || b==i))
                            {
                                m1.Mat[c][d] = m.Mat[a][b];
                               if (d<m1.getCol()-1){d+=1;}
                               else{c+=1;d=0;}
                            }
                        }
                    }
                    res = -(m.getElmt(0,i)*detCofactor(m1));
                    det += res;
                }
            }
            return det;
        }
    }

    public double detReduction (Matrix m)
    {
        double deter = 1;
        Matrix temp = m.copyMatrix();
        temp = ElimMajuDet(temp);
        for (int i = 0; i<temp.getRow() ; i++)
        {
            deter *= temp.getElmt(i, i);
        }
        return deter;
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


    public Matrix ElimMajuDet(Matrix matrix){
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

            Matrix temp = matrix.copyMatrix();

            int[] sortedZero = Arrays.copyOf(arrayZero, arrayZero.length);
            Arrays.sort(sortedZero);

            if (!Arrays.equals(arrayZero, sortedZero)){
                for (int i = k; i< arrayZero.length; i++){
                    int x = 0;

                    while((arrayZero[i] != sortedZero[x])){
                        x++;
                    }
                    sortedZero[x] = matrix.getCol();
                    matrix.Mat[x] = temp.Mat[i];
                    // matrix.tukar += 1;
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
                            matrix.Mat[i][j] -= matrix.Mat[k][j] * divider;
                            Rounder(matrix, 1e-9);
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
                            matrix.Mat[i][j] -= matrix.Mat[k][j] * divider;
                            Rounder(matrix, 1e-9);
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
                // double divider = matrix.Mat[r][c];
                for (int tcol = 0; tcol< matrix.getCol(); tcol++){
                    // matrix.Mat[r][tcol]=matrix.Mat[r][tcol]/divider;
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