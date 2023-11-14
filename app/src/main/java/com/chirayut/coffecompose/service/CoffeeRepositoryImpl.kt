package com.chirayut.coffecompose.service

import com.chirayut.coffecompose.model.AppStatus
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.model.MenuBar
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class CoffeeRepositoryImpl : CoffeeRepository {

    //private val BASE_URL = "http://localhost:4000/"
    //private val BASE_URL = "http://127.0.0.1:4000/"
    private companion object {
        const val BASE_URL = "http://10.0.2.2:4000/"
    }

    private val apiService: ApiService

    init {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    override suspend fun getAllCoffee(): List<CoffeeDTO> {
        return try {
            val response = apiService.getCoffeeMenu().awaitResponse()
            if (response.isSuccessful) {
                //val countries = response.body() ?: emptyList()
                //countries.map { t.toModel() }
                (response.body() ?: emptyList())
            } else {
                emptyList()
            }
        
        } catch (exception: Exception) {
            val e = exception
            emptyList()
        }

    }

    override suspend fun checkAppStatus(): List<AppStatus> {
        return try {
            val response = apiService.checkAppStatus().awaitResponse()
            if (response.isSuccessful) {
                //val countries = response.body() ?: emptyList()
                //countries.map { t.toModel() }
                delay(1500)
                (response.body() ?: emptyList())
            } else {
                emptyList()
            }

        } catch (exception: Exception) {
            val e = exception
            emptyList()
        }
    }

    override suspend fun getCoffeeMenuBar(): List<MenuBar> {
        return try {
            val response = apiService.getCoffeeMenuBar().awaitResponse()
            if (response.isSuccessful) {
                //val countries = response.body() ?: emptyList()
                //countries.map { t.toModel() }
                (response.body() ?: emptyList())
            } else {
                emptyList()
            }

        } catch (exception: Exception) {
            val e = exception
            emptyList()
        }
    }

    override suspend fun getCoffeeMenuDetailById(id: String?): CoffeeDTO {
        return try {
            val response = apiService.getCoffeeMenuById(id).awaitResponse()
            if (response.isSuccessful) {
                //val countries = response.body() ?: emptyList()
                //countries.map { t.toModel() }
                (response.body() ?:  CoffeeDTO())
            } else {
                CoffeeDTO()
            }

        } catch (exception: Exception) {
            val e = exception
            CoffeeDTO()
        }
    }
}
