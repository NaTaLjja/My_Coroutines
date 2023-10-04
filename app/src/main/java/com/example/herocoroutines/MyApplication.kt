package com.example.herocoroutines

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@HiltAndroidApp
class MyApplication:Application()

/*
class MyApplication:Application() {
    lateinit var repo: Repository

    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = Repository(getApiClient())
    }

    private fun getApiClient():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://akabab.github.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object{
        private lateinit var instance:MyApplication
        fun getApp() = instance
    }
}*/
