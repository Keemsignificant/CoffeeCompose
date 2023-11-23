package com.chirayut.coffecompose.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.model.AppStatus
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    var checkAppResult = MutableLiveData<List<AppStatus>>().apply { value = listOf() }
    var isShowDialogLoading = MutableLiveData<Boolean>().apply { value = true }//by remember { mutableStateOf(true) }

    init {
        checkAppStatus()
    }
    private fun checkAppStatus() {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().checkAppStatus()
            delay(1500)
            checkAppResult.value = result
        }
    }

}