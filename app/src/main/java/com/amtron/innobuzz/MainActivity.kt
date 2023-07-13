package com.amtron.innobuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.amtron.innobuzz.api.PostService
import com.amtron.innobuzz.api.RetrofitHelper
import com.amtron.innobuzz.database.PostDatabase
import com.amtron.innobuzz.databinding.ActivityMainBinding
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val instance = RetrofitHelper.getInstance().create(PostService::class.java)
        val database = PostDatabase.getDataBase(applicationContext).postDao()



        instance.getPost().enqueue(object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {



                Toast.makeText(this@MainActivity, "Data Received", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Network Error", Toast.LENGTH_SHORT).show()
            }

        })


    }
}