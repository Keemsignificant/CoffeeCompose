package com.chirayut.coffecompose.host

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chirayut.coffecompose.compose_ui.CustomBottomNav

@Composable
fun HostFragment(
    navController: NavController,
    hostViewModel: HostViewModel = viewModel()
) {
    CustomBottomNav()
    Text(text = "Host")
}