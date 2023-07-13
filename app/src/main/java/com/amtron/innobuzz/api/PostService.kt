package com.amtron.innobuzz.api

import com.amtron.innobuzz.model.Post
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPost(): Call<JsonArray>
}