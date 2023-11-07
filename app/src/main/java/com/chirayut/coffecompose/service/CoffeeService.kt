package com.chirayut.coffecompose.service

import com.chirayut.coffecompose.model.CoffeeDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CoffeeService {

    @GET("getCoffeeMenu")
    fun getCoffeeMenu(): Call<List<CoffeeDTO>>


    @GET("getCoffeeMenu")
    suspend fun getCoffeeMenuTest(
    ) : List<CoffeeResponse>


    companion object {
        private var retrofitService: CoffeeService? = null
        fun getInstance() : CoffeeService {
            if (retrofitService == null) {
                val client = OkHttpClient
                    .Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
                //http://localhost:4000/getCoffeeMenu
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://localhost:4000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(CoffeeService::class.java)
            }
            return retrofitService!!
        }
    }
}