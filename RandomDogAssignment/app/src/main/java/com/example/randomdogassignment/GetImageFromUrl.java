package com.example.randomdogassignment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
    Bitmap bitmap;
    ImageView image;
    String urlImage = "https:\\/\\/images.dog.ceo\\/breeds\\/basenji\\/n02110806_5744.jpg";
    ImageView imageView;
    public GetImageFromUrl(ImageView img){
        this.imageView = img;
    }
    @Override
    protected Bitmap doInBackground(String... url) {
        String stringUrl = url[0];
        bitmap = null;
        InputStream inputStream;
        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("Bitmap------",bitmap.toString());
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap){
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}