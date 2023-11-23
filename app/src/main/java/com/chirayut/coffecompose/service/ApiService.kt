package com.chirayut.coffecompose.service

import com.chirayut.coffecompose.model.AppStatus
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.model.MenuBar
import com.chirayut.coffecompose.model.ProcessHistory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("getCoffeeMenu")
    fun getCoffeeMenu(): Call<List<CoffeeDTO>>

    @GET("checkAppStatus")
    fun checkAppStatus(): Call<List<AppStatus>>

    @GET("getCoffeeMenuBar")
    fun getCoffeeMenuBar(): Call<List<MenuBar>>

    @GET("getCoffeeMenuByID/{id}")
    fun getCoffeeMenuById(@Path("id") id: String?): Call<CoffeeDTO>

    @GET("getProcess")
    fun getProcess(): Call<List<ProcessHistory>>
}