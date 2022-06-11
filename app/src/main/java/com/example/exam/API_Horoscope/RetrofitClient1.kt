package com.example.exam.API_Horoscope

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient1 {
    var gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofitClient = Retrofit.Builder()
        .baseUrl("https://ohmanda.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val service = retrofitClient.create(ApiRequests1::class.java)
}