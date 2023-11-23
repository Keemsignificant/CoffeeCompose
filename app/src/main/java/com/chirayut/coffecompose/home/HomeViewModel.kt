package com.chirayut.coffecompose.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    var menuCoffeeResult = MutableLiveData<List<CoffeeDTO>>().apply { value = listOf() }
    var isLoadingItem = MutableLiveData<Boolean>().apply { value = true }

    init {
        getMenuCoffee()
    }
    private fun getMenuCoffee() {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().getAllCoffee()
            menuCoffeeResult.value = result
        }
    }


}