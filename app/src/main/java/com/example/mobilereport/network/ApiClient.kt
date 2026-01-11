package com.example.mobilereport.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:7050/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}