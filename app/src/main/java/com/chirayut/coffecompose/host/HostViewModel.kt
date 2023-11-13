package com.chirayut.coffecompose.host

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.model.AppStatus
import com.chirayut.coffecompose.model.MenuBar
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.launch

class HostViewModel: ViewModel() {

    var menuBarResult = MutableLiveData<List<MenuBar>>().apply { value = listOf() }
    fun getMenuBar() {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().getCoffeeMenuBar()
            menuBarResult.value = result
        }
    }
}