package com.amtron.innobuzz

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.amtron.innobuzz.api.PostService
import com.amtron.innobuzz.api.RetrofitHelper
import com.amtron.innobuzz.database.PostDatabase
import com.amtron.innobuzz.databinding.ActivityMainBinding
import com.amtron.innobuzz.model.Post
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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


        instance.getPost().enqueue(object: Callback<JsonArray>{
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {


                if(response.isSuccessful){

                    val data : List<Post> = Gson().fromJson(response.body(), object : TypeToken<List<Post>>()  {}.type)

                    GlobalScope.launch {
                        database.addPosts(data)
                    }
                }

            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {

                Toast.makeText(this@MainActivity, "Network Error", Toast.LENGTH_SHORT).show()

            }

        })






    }
}