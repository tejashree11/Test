package com.example.randomdogassignment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.randomdogassignment.Models.DogModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;

public class CacheImageManager {
    public static Bitmap getImage(Context context, DogModel dogmodel){
            String filename= context.getCacheDir()+"/"+dogmodel.getImg();
            File file=new File(filename);
            Bitmap bitmap=null;
        try {
            bitmap= BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static void putImage(Context context,DogModel dogmodel,Bitmap bitmap){
        String filename= context.getCacheDir()+"/"+dogmodel.getImg();
        File file=new File(filename);
        FileOutputStream outputStream=null;
        try {
            outputStream= new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,outputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
