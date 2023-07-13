package com.amtron.innobuzz.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPost(): Call<JsonObject>
}