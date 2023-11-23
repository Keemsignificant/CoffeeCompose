package com.chirayut.coffecompose.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.BaseViewModel
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.launch

class DetailViewModel: BaseViewModel() {


    var menuCoffeeResult = MutableLiveData<CoffeeDTO>()
    fun getCoffeeMenuDetailById(id: String?) {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().getCoffeeMenuDetailById(id = id)
            menuCoffeeResult.value = result
        }
    }
}