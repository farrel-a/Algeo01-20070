package com.matrixxx;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save {
    File file;
    String filename;

    //Konstruktor
    Save (String fname) //fname = filename
    {
        this.filename = ("..\\test\\"+fname);
        try{
            this.file = new File(this.filename);
            if (this.file.createNewFile())
            {
                System.out.println("File Created: " + fname);
            }
            else
            {
                System.out.println("File already exists");
            }
        }
        catch (IOException exc)
        {
            System.out.println("An error occurred");
            exc.printStackTrace();
        }
    }

    public void write(String content)
    {
        try
        {
           FileWriter file = new FileWriter(this.filename);
           file.write(content);
           file.close(); 
        }
        catch (IOException exc)
        {
            System.out.println("An error occurred");
            exc.printStackTrace();
        }
    }


}
