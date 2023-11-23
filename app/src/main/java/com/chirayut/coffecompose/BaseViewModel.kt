package com.chirayut.coffecompose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel()  {
    var isLoading = MutableLiveData<Boolean>().apply { value = false }
}