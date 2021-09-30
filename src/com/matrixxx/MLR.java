package com.matrixxx;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;  

//Multiple Linear Regression
public class MLR {
    public void callFile(String path)
    {
        //dataset counter
        //counter : dataset counter, n : how many (x,y)
        int counter = 0; boolean flag = false; int n=0;
        File file = new File(path);
        try{
            Scanner lineReader = new Scanner(file);
            if (lineReader.hasNextLine()){counter=1;}
            else{counter=0;}
            while(lineReader.hasNextLine())
            {
                Scanner row = new Scanner(lineReader.nextLine());
                if (!row.hasNextDouble()){counter+=1;flag = true;}
                else if (!flag){n+=1;}

            }
            lineReader.close();
        }
        
        catch (FileNotFoundException e) 
            {
                System.out.println("File not found.");
                e.printStackTrace();
            }
        
        Points[] arrpoints = new Points[counter];

        try{
            int j = 0;
        Scanner lineReader = new Scanner(file);
        for (int i = 0 ; i<counter ; i++)
        {
            arrpoints[i] = new Points(n); 
            
            while(lineReader.hasNextLine())
            {
                Scanner row = new Scanner(lineReader.nextLine());
                
                if (row.hasNextDouble())
                {
                    double x = row.nextDouble();
                    double y = row.nextDouble();
                    arrpoints[i].points[j] = new Point(x,y);
                    j++;
                }
                else{j=0; break;}
            }

        }
        lineReader.close();
        }

        catch (FileNotFoundException e) 
        {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        int i; int j; int k;
        Matrix mat = new Matrix(counter+1, counter+2);
        for (i = 0 ; i<mat.getRow();i++)
        {
            for (j=0 ; j<mat.getCol();j++)
            {
                if (i==0 && j==0)
                {mat.Mat[i][j] = n;}

                else if (i==0 && j!=0 && j != counter+1)
                {
                    mat.Mat[i][j] = 0;
                    // System.out.println(mat.getCol());
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += arrpoints[j-1].points[k].x;
                    }

                }

                else if (i==0 && j == counter+1)
                {
                    mat.Mat[i][j] = 0;
                    for (k=0;k<n;k++)
                    {
                        mat.Mat[i][j] += arrpoints[0].points[k].y;
                    }
                }
                
                else if (i!=0 && j==0)
                {
                    mat.Mat[i][j] = 0;
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += arrpoints[i-1].points[k].x;
                    }
                }

                else if (i!=0 && j!=0 && j!=counter+1) 
                {
                    mat.Mat[i][j] = 0;
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += (arrpoints[i-1].points[k].x * arrpoints[j-1].points[k].x);
                    }
                }
                
                else if (i!=0 && j==counter+1)
                {
                    // System.out.println(i +" "+ j);
                    mat.Mat[i][j] = 0;
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += (arrpoints[0].points[k].y * arrpoints[i-1].points[k].x);
                    }
                }
                
            }
        }
        System.out.println("Matriks Regresi Linear Berganda: ");
        mat.tulisMatrix(); //MATRIX MLR (Multiple Linear Regression)
        mat = mat.ElimMaju(mat);
        System.out.println("Matriks Eselon: ");
        mat.tulisMatrix(); //Matrix reduksi
        Gauss gauss = new Gauss();
        gauss.GaussSolver(mat);

    }
}

