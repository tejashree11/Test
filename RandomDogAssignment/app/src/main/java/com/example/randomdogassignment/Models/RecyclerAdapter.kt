package com.example.randomdogassignment.Models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.randomdogassignment.R
import java.io.*
import java.net.URL


class RecyclerAdapter(val context:Context,val dogArrayList:ArrayList<DogModel>) :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.sample_recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogs = dogArrayList[position]
        Log.i("Current Dog Image-->>", dogs.img);
     GetImageFromUrl(holder.itemImage).execute(dogs.img)

        Glide.with(context)
            .load(dogs.img)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(holder.itemImage)

    }

    override fun getItemCount(): Int {
        return dogArrayList.size
    }

    fun bitmapToFile(bitmap: Bitmap, fileNameToSave: String): File? { // File name like "image.png"
        //create a file to write bitmap data
        var file: File? = null
        return try {
            file = File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave)
            file.createNewFile()

            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitmapdata = bos.toByteArray()

            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }



    class GetImageFromUrl(var imageView: ImageView) :
        AsyncTask<String?, Void?, Bitmap>() {
        var bitmap: Bitmap? = null
        var bitmap1: Bitmap? = null

        override fun doInBackground(vararg url: String?): Bitmap? {
            val stringUrl = url[0]
            Log.e("Tag--String URL-->",stringUrl.toString());
            bitmap = null
            val inputStream: InputStream
            try {
                inputStream = URL(stringUrl).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)

            } catch (e: IOException) {
                e.printStackTrace()
            }

            Log.i("Bitmap------>", bitmap.toString())
            return bitmap;
        }

        override fun onPostExecute(bitmap: Bitmap) {
            super.onPostExecute(bitmap)
            imageView.setImageBitmap(bitmap)
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }
    }

}


