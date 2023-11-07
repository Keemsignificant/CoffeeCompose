package com.chirayut.coffecompose.service

import com.chirayut.coffecompose.model.CoffeeDTO
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("getCoffeeMenu")
    fun getCoffeeMenu(): Call<List<CoffeeDTO>>
}