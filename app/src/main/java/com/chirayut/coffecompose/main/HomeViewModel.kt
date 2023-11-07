package com.chirayut.coffecompose.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var menuCoffeeResult = MutableLiveData<List<CoffeeDTO>>().apply { value = listOf() }

    fun getMenuCoffee() {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().getAllCoffee()
            menuCoffeeResult.value = result
        }
    }


}