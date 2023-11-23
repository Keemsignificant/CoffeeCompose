package com.chirayut.coffecompose.process_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.BaseViewModel
import com.chirayut.coffecompose.model.ProcessHistory
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.launch

class ProcessViewModel: BaseViewModel() {

    val processHistoryResult = MutableLiveData<List<ProcessHistory>>().apply { value = listOf() }

    fun getProcessHistory() {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().getProcessHistory()
            processHistoryResult.value = result
        }
    }
}
