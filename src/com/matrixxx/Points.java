package com.matrixxx;

import java.util.Scanner;
import java.io.FileNotFoundException; 
import java.io.File;  

public class Points {
    Point[] points; //array of Point

    //Konstruktor
    Points(String filepath){
        readPointsFile(filepath); //dengan file points.txt
    }
    Points(int n)
    {
        this.points = new Point[n]; //tanpa file points.txt
    }

    public void readPoints()
    {

        System.out.println("Masukkan titik: ");
        Scanner sc = new Scanner(System.in);
        for(int i = 0 ; i<this.points.length ; i++)
        {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            Point P = new Point(x,y);
            this.points[i] = P;
            
        }
        sc.close();
    }

    public Point getElmt(int i)
    {
        return this.points[i];
    }

    public void writePoints()
    {
        for (int i = 0 ; i<this.points.length ; i++)
        {
            String x = String.format("%.3f", getElmt(i).getX()); //3 decimals floating point
            String y = String.format("%.3f", getElmt(i).getY());
            System.out.println("("+ x + " , " + y+")");
        }
    }

    public void readPointsFile(String filepath)
    {
        File file = new File(filepath);
        try{
            Scanner lineReader = new Scanner(file);
            int n = 0;
            while(lineReader.hasNextLine())
            {
                lineReader.nextLine();
                n+=1;
            }
            this.points = new Point[n];
            lineReader.close();
            Scanner reader = new Scanner(file);
            for (int i=0 ; i<n ; i++)
            {
                Scanner num = new Scanner(reader.nextLine());
                double x_in = num.nextDouble();
                double y_in = num.nextDouble();
                Point P = new Point(x_in, y_in);
                this.points[i] = P;
                num.close();
            }
            reader.close();
        }
        catch (FileNotFoundException e) 
            {
                System.out.println("File not found.");
                e.printStackTrace();
            }
    }

}
