package com.amtron.innobuzz.api

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPost(): Call<JsonArray>
}