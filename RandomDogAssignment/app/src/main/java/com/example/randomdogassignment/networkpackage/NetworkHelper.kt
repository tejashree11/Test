package com.example.randomdogassignment.networkpackage

import android.content.Context
import android.net.ConnectivityManager

object NetworkHelper {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return try {
            val info = connectivityManager.activeNetworkInfo
            info != null && info.isConnectedOrConnecting
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}



//Corresponding java code
//package com.example.randomdogassignment.networkpackage;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//
//import androidx.core.net.ConnectivityManagerCompat;
//
//public class NetworkHelper {
//    public static boolean isNetworkAvailable(Context context){
//        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        try {
//            NetworkInfo info=connectivityManager.getActiveNetworkInfo();
//            return info!=null && info.isConnectedOrConnecting();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}
