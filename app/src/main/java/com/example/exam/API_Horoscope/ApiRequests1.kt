package com.example.exam.API_Horoscope

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests1 {
    @GET("api/horoscope/{sign}")
    suspend fun getHoroscope(@Path("sign") sign:String): Response<Repo1>
}