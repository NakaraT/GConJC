package com.example.geneticcalc.data.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitFactory {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService = retrofit.create(TypeCodeAPI::class.java)
}
