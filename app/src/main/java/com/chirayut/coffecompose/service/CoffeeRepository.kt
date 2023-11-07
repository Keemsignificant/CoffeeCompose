package com.chirayut.coffecompose.service

import com.chirayut.coffecompose.model.CoffeeDTO

interface CoffeeRepository2 {
    suspend fun getAllCoffee(): List<CoffeeDTO>
}