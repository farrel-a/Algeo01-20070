package com.matrixxx;

public class Cramer {
    public double[] call(Matrix mat)
    {
        double[] solusi = new double[mat.getCol()-1];
        Matrix mat2 = new Matrix(mat.getCol()-1,mat.getCol()-1);


        double[] y = new double[mat.getRow()];
        int i,j;

        for (i=0;i<mat.getRow();i++)   //menyimpan semua nilai y
        {
            y[i] = mat.getElmt(i, mat.getCol()-1);
        }

        for (i=0 ; i<mat2.getRow();i++) //mat2 sebagai isi x nya saja dari mat
        {
            for (j=0 ; j<mat2.getCol();j++)
            {
                mat2.Mat[i][j] = mat.getElmt(i, j);
            }
        }
        
        Determinant determinant = new Determinant();
        double detCram = 0.0;
        double det = determinant.detCofactor(mat2);
        if (det==0.0){
            solusi = new double[0];
            return solusi;} //determinan nol tidak ada solusi

        Matrix mat3 = mat2.copyMatrix(); //copy mat2 ke mat3 untuk perubahan matriks di cramer

        for(i=0 ; i<mat2.getCol() ;i++)  //perhitungan cramer
        {
            for (j=0 ; j<mat2.getRow() ;j++)
            {   
                mat3.Mat[j][i] = y[j];      //penggantian elemen dengan y
            }
            detCram = determinant.detCofactor(mat3);
            solusi[i] = detCram/det;  //pengisian solusi x1, x2, .., xn
            mat3 = mat2.copyMatrix();  //kembalikan seperti semula
        }

        return solusi;
    }
}
