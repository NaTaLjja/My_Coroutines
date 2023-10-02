package com.example.herocoroutines

import retrofit2.Response
import retrofit2.Retrofit

class Repository(client:Retrofit) {
    private val apiInterface = client.create(ApiInterface::class.java)

    suspend fun getListHeroes():Response<List<Hero>> = apiInterface.getHeroes()
}