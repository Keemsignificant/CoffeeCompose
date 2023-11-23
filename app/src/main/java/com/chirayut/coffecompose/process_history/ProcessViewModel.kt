package com.chirayut.coffecompose.process_history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chirayut.coffecompose.BaseViewModel
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.model.ProcessHistory
import com.chirayut.coffecompose.service.CoffeeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProcessViewModel : BaseViewModel() {

    val processHistoryResult = MutableLiveData<List<ProcessHistory>>().apply { value = listOf() }

    private val _state = MutableStateFlow(ChatScreenUiState())
    val state = _state.asStateFlow()

    init {
        getProcessHistory()
    }

    private fun getProcessHistory() {
        viewModelScope.launch {
            val result =  CoffeeRepositoryImpl().getProcessHistory()
            processHistoryResult.value = result
        }
        /*_state.update {
            it.copy(
                messages = listOf(
                    ChatCardItemContent(
                        1,
                        R.drawable.baseline_restaurant_menu_24,
                        "Ahmed Khater",
                        "Yes, that's gonna work, hopefully",
                        "15:12",
                        2
                    ),
                    ChatCardItemContent(
                        2,
                        R.drawable.baseline_restaurant_menu_24,
                        "Krishna Barbe",
                        "Hello guys, we have discussed about...",
                        "12:00"
                    ),
                    ChatCardItemContent(
                        3,
                        R.drawable.baseline_restaurant_menu_24,
                        "Eshan Terrel",
                        "Thanks dude ☻",
                        "10:17"
                    ),
                    ChatCardItemContent(
                        4,
                        R.drawable.baseline_restaurant_menu_24,
                        "Beck Bowes",
                        "You can new method...",
                        "Yesterday",
                        3
                    ),
                    ChatCardItemContent(
                        5,
                        R.drawable.baseline_restaurant_menu_24,
                        "Cameron Williamson",
                        "Voice message",
                        "Yesterday"
                    ),
                    ChatCardItemContent(
                        6,
                        R.drawable.baseline_restaurant_menu_24,
                        "Eleanor Pena",
                        "Voice message",
                        "2 Days Ago"
                    ),
                    ChatCardItemContent(
                        7,
                        R.drawable.baseline_restaurant_menu_24,
                        "Tyra Dhillon",
                        "Good morning.",
                        "4 Days Ago"
                    ),
                    ChatCardItemContent(
                        8,
                        R.drawable.baseline_restaurant_menu_24,
                        "Sia",
                        "Voice message",
                        "4 Days Ago"
                    ),
                    ChatCardItemContent(
                        9,
                        R.drawable.baseline_restaurant_menu_24,
                        "Eshan Terrel",
                        "Thanks dude ☻",
                        "10:17"
                    ),
                    ChatCardItemContent(
                        10,
                        R.drawable.baseline_restaurant_menu_24,
                        "Beck Bowes",
                        "You can new method...",
                        "Yesterday",
                        3
                    ),
                    ChatCardItemContent(
                        11,
                        R.drawable.baseline_restaurant_menu_24,
                        "Cameron Williamson",
                        "Voice message",
                        "Yesterday"
                    ),
                    ChatCardItemContent(
                        12,
                        R.drawable.baseline_restaurant_menu_24,
                        "Eleanor Pena",
                        "Voice message",
                        "2 Days Ago"
                    ),
                )
            )
        }*/
    }

    fun onSwipeToDelete(selectedMessage: ChatCardItemContent) {
        _state.update {
            it.copy(
                messages = it.messages.filterNot { message ->
                    message.id == selectedMessage.id
                }
            )
        }
    }

    fun onSwipeToArchive(selectedMessage: ChatCardItemContent) {
        _state.update {
            it.copy(
                messages = it.messages.filterNot { message ->
                    message.id == selectedMessage.id
                }
            )
        }
    }
}
