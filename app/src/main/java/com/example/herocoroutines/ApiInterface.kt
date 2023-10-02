package com.example.herocoroutines

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    suspend fun getHeroes():Response<List<Hero>>
}