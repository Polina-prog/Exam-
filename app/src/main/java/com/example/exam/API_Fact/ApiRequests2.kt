package com.example.task2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequests2 {
    @GET("planetary/apod")
    suspend fun getImage(@Query("api_key") api_key:String): Response<Repo2>
}