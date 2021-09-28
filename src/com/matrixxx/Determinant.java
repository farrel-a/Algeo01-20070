package com.matrixxx;

import java.util.Scanner;

public class Determinant {
    public void call(int choice, Matrix mat) //1 = kofaktor, 2 = reduksi baris
    {
        if (choice == 1)
        {
            double det = detCofactor(mat);
            System.out.println("Determinan = " + det);
        }

        else if (choice == 2)
        {
            //reduksi baris determinan belum diisi
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
}
