package com.chirayut.coffecompose.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.model.AppStatus
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    var checkAppResult = MutableLiveData<List<AppStatus>>().apply { value = listOf() }
    fun checkAppStatus() {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().checkAppStatus()
            checkAppResult.value = result
        }
    }
}