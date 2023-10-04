package com.example.herocoroutines

import retrofit2.Response
import retrofit2.Retrofit

/*
class Repository(client:Retrofit) {
    private val apiInterface = client.create(ApiInterface::class.java)

    suspend fun getListHeroes():Response<List<Hero>> = apiInterface.getHeroes()
}*/
class Repository(private val client:ApiClient) {
    private val apiInterface = client.client.create(ApiInterface::class.java)

    suspend fun getListHeroes():Response<List<Hero>>  = apiInterface.getHeroes()
}