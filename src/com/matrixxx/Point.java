package com.matrixxx;

public class Point {
    double x;
    double y;

    //Konstruktor
    Point(){}
    Point(double X, double Y)
    {
        this.x = X;
        this.y = Y;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public void setX(double x_arg)
    {
        this.x = x_arg;
    }

    public void setY(double y_arg)
    {
        this.y = y_arg;
    }
}
