package com.matrixxx;

public class InverseSPL {
    public double[] inverseSPL(Matrix mat)
    {
        //Ax = B --> x = (A^-I) B
        double[] solusi = new double[mat.getCol()-1];  //solusi x1, x2, x3,...,xn
        Matrix mat2 = new Matrix(mat.getCol()-1,mat.getCol()-1); //Matriks A
        double[] B = new double[mat.getRow()];

        int i,j;
        double sum;

        for (i=0 ; i<mat.getRow() ; i++) //matriks B
        {
            B[i] = mat.getElmt(i, mat.getCol()-1);
        }

        for (i=0 ; i<mat2.getRow();i++) //Pengisian Matriks A dari mat
        {
            for (j=0 ; j<mat2.getCol();j++)
            {
                mat2.Mat[i][j] = mat.getElmt(i, j);
            }
        }

        mat2 = mat2.inverse();

        for (i=0 ; i<mat2.getRow() ; i++) //(A^-I) B = x
        {
            sum = 0;
            for (j=0 ; j<mat2.getCol() ; j++)
            {
                sum += (mat2.getElmt(i, j) * B[j]);
            }
            solusi[i] = sum;
        }
        

        return solusi;
    }
}
