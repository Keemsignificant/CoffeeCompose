package com.chirayut.coffecompose.host

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chirayut.coffecompose.compose_ui.CustomBottomNav

@Composable
fun HostFragment(
    navController: NavController,
    hostViewModel: HostViewModel = viewModel()
) {
    //HomeFragment()
    hostViewModel.getMenuBar()

    val menuBar = hostViewModel.menuBarResult.observeAsState().value
    if (menuBar.isNullOrEmpty()){

    }else{
        CustomBottomNav(menuBar ?: listOf(), navController)
    }
}