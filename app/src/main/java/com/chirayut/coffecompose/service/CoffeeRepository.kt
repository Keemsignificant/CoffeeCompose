package com.chirayut.coffecompose.service

import com.chirayut.coffecompose.model.AppStatus
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.model.MenuBar
import com.chirayut.coffecompose.model.ProcessHistory

interface CoffeeRepository {
    suspend fun getAllCoffee(): List<CoffeeDTO>
    suspend fun checkAppStatus(): List<AppStatus>
    suspend fun getCoffeeMenuBar(): List<MenuBar>
    suspend fun getCoffeeMenuDetailById(id: String?): CoffeeDTO
    suspend fun getProcessHistory(): List<ProcessHistory>
}