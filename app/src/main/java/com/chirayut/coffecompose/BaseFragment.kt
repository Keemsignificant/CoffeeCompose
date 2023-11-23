package com.chirayut.coffecompose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chirayut.coffecompose.compose_ui.CustomDialog


@Composable
fun BaseFragment(viewModel: BaseViewModel) {

    val isLoading = viewModel.isLoading.observeAsState().value ?: false

    CustomDialog(isShowDialogLoading = isLoading, onDismissRequest = {
        viewModel.isLoading.value = false
    })
}