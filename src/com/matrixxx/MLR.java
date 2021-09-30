package com.matrixxx;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;  

//Multiple Linear Regression
public class MLR {
    public double[] callInput()
    {
        System.out.print("Masukkan Jumlah Data: ");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        System.out.print("Masukkan Jumlah Peubah: ");
        int ds = sc.nextInt(); //ds = datasets
        Points[] arrpoints = new Points[ds];
        double[] y = new double[data];

        for(int i = 0;i<data;i++)
        {
            System.out.print("Y" + i+1 +": ");
            y[i] = sc.nextDouble();
        }
        for (int i = 0 ; i<ds ; i++)
        {
            System.out.println("Dataset ke-"+(i+1));

            for (int j = 0 ; j<data ; j++)
            {
                System.out.println("X"+(i+1)+(j+1)+": ");
                double x_in = sc.nextDouble();
                double y_in = y[j];
                arrpoints[i].points[j] = new Point(x_in,y_in);
            }
        }
        Matrix mat = new Matrix(ds+1, ds+2);
        int n = data;
        int i,j,k;
        for (i = 0 ; i<mat.getRow();i++)
        {
            for (j=0 ; j<mat.getCol();j++)
            {
                if (i==0 && j==0)
                {mat.Mat[i][j] = n;}

                else if (i==0 && j!=0 && j != ds+1)
                {
                    mat.Mat[i][j] = 0;
                    // System.out.println(mat.getCol());
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += arrpoints[j-1].points[k].x;
                    }

                }

                else if (i==0 && j == ds+1)
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

                else if (i!=0 && j!=0 && j!=ds+1)
                {
                    mat.Mat[i][j] = 0;
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += (arrpoints[i-1].points[k].x * arrpoints[j-1].points[k].x);
                    }
                }

                else if (i!=0 && j==ds+1)
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
        double[] solusi = gauss.GaussSolverFunction(mat);
        return solusi;
    }

    public Matrix callInputMatrix()
    {
        System.out.print("Masukkan Jumlah Data: ");
        Scanner sc = new Scanner(System.in);
        int data = sc.nextInt();
        System.out.print("Masukkan Jumlah Peubah: ");
        int ds = sc.nextInt(); //ds = datasets
        Points[] arrpoints = new Points[ds];
        double[] y = new double[data];

        for(int i = 0;i<data;i++)
        {
            System.out.print("Y" + (i+1) +": ");
            y[i] = sc.nextDouble();
        }
        for (int i = 0; i<ds ; i++)
        {
            arrpoints[i] = new Points(data);
            System.out.println("Dataset ke-"+(i+1));
            for (int j = 0 ; j<data ; j++)
            {
                System.out.print("X"+(i+1)+(j+1)+": ");
                double x_in = sc.nextDouble();
                double y_in = y[j];
                Point a = new Point(x_in,y_in);
                arrpoints[i].points[j] = a;
            }
        }
        Matrix mat = new Matrix(ds+1, ds+2);
        int n = data;
        int i,j,k;
        for (i = 0 ; i<mat.getRow();i++)
        {
            for (j=0 ; j<mat.getCol();j++)
            {
                if (i==0 && j==0)
                {mat.Mat[i][j] = n;}

                else if (i==0 && j!=0 && j != ds+1)
                {
                    mat.Mat[i][j] = 0;
                    // System.out.println(mat.getCol());
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += arrpoints[j-1].points[k].x;
                    }

                }

                else if (i==0 && j == ds+1)
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

                else if (i!=0 && j!=0 && j!=ds+1)
                {
                    mat.Mat[i][j] = 0;
                    for (k=0 ; k<n ; k++)
                    {
                        mat.Mat[i][j] += (arrpoints[i-1].points[k].x * arrpoints[j-1].points[k].x);
                    }
                }

                else if (i!=0 && j==ds+1)
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
        return mat;
    }

    public double[] callFile(String path)
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
        double[] solusi = gauss.GaussSolverFunction(mat);
        return solusi;
    }

    public Matrix callFileMatrix(String path)
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
        return mat;
    }

}

