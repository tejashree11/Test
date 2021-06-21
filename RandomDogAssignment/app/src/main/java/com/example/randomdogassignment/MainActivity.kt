package com.example.randomdogassignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomdogassignment.Models.DogModel
import com.example.randomdogassignment.Models.RecyclerAdapter
import com.example.randomdogassignment.networkpackage.NetworkHelper
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var context:Context=this


        recyclerview.setHasFixedSize(true)
        recyclerview.adapter=null

       var isNetworkOk= NetworkHelper.isNetworkAvailable(context)
    // if(isNetworkOk) {
         val request = Request.Builder()
             .url("https://dog.ceo/api/breeds/image/random/2")
             .build()
         Log.d("Request Builder", request.toString())

         val okHttp = OkHttpClient()

         okHttp.newCall(request).enqueue(object : Callback {

             override fun onFailure(call: Call, e: IOException) {
                 Log.d("Fail", e.message ?: "Something went wrong")
             }

             override fun onResponse(call: Call, response: Response) {

                 Log.i("vikas", response.toString())

                 val dogArrayList = fetchDogArrayList()
                 Log.i("Array Resoponse-->>", dogArrayList.toString());
                 runOnUiThread {
                     val recyclerAdapter = RecyclerAdapter(this@MainActivity, dogArrayList)
                     recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
                     recyclerview.adapter = recyclerAdapter
                     recyclerAdapter.notifyDataSetChanged()
                     Log.i("vishal", response.toString())
                 }
             }
         })
     //}
//         else{
//             Toast.makeText(context, "Network Unavailable", Toast.LENGTH_SHORT).show()
//         }
        recyclerview.itemAnimator = DefaultItemAnimator()

    }

    private fun fetchDogArrayList(): ArrayList<DogModel> {
        val apiResponse = URL("https://dog.ceo/api/breeds/image/random/20").readText()
        Log.i("API RESPONSE -- >",apiResponse);
        val json = JSONObject(apiResponse)
        Log.i("API RESPONSE2 -- >",json.toString());
        val arr: JSONArray = json.getJSONArray("message")
        Log.i("API RESPONSE3 -- >",arr.toString());
        val dogs = ArrayList<DogModel>()
        for (i in 0..arr.length()-1)
        {
            dogs.add(DogModel(arr[i].toString()))
            Log.i("Current array  -- >",dogs.toString());

        }
        return dogs;
    }

}