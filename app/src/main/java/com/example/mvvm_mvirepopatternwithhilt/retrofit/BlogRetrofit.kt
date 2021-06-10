package com.example.mvvm_mvirepopatternwithhilt.retrofit

import retrofit2.http.GET

interface BlogRetrofit {

    companion object{
        const val BASE_URL = "https://open-api.xyz/placeholder/"
    }

    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>

}