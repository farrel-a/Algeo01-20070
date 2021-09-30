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

    public void isiOtomatis()
    {
        Point a = new Point(6.567,12624);
        Point b = new Point(7,21807);
        Point c = new Point(7.258,38391);
        Point d = new Point(7.451,54517);
        Point e = new Point(7.548,51952);
        Point f = new Point(7.839,28228);
        Point g = new Point(8.161,35764);
        Point h = new Point(8.484,20813);
        Point i = new Point(8.709,12408);
        Point j = new Point(9,10534);

        Point[] tempPoints = {a, b, c, d, e, f, g, h, i, j};
        this.points = tempPoints;

    }
    public Matrix toMatrix (){
        Matrix matInterpol = new Matrix(this.points.length, this.points.length+1);
        int i, j;
        for (i=0; i< matInterpol.getRow(); i++){
            for (j=0; j< matInterpol.getCol(); j++){
                if (j==matInterpol.getCol()-1){
                    matInterpol.Mat[i][j] = this.points[i].getY();
                }
                else {
                    matInterpol.Mat[i][j] = Math.pow(this.points[i].getX(), j);
                }
            }
        }
        matInterpol.tulisMatrix();
        return matInterpol;
    }

}
