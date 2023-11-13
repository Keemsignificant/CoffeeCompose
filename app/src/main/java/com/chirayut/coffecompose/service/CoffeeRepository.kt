package com.chirayut.coffecompose.service

import com.chirayut.coffecompose.model.AppStatus
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.model.MenuBar

interface CoffeeRepository {
    suspend fun getAllCoffee(): List<CoffeeDTO>
    suspend fun checkAppStatus(): List<AppStatus>
    suspend fun getCoffeeMenuBar(): List<MenuBar>
}