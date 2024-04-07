package com.example.geneticcalc.data.API

import com.example.geneticcalc.data.models.PlaceholderPost
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface TypeCodeAPI {
    @GET("posts/99")
    suspend fun post(): PlaceholderPost

    @POST("posts")
    fun pushPost(@Body post: PlaceholderPost?): retrofit2.Call<PlaceholderPost?>?

    @get:GET("posts")
    val allPosts: retrofit2.Call<List<PlaceholderPost?>?>?
}