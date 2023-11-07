package com.chirayut.coffecompose.main

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chirayut.coffecompose.compose_ui.renderList
import com.chirayut.coffecompose.model.CoffeeDTO

@Composable
fun HomeFragment(
    homeViewModel: HomeViewModel = viewModel()
) {


    fun launch() {
        homeViewModel.getMenuCoffee()
    }

    launch()

    val list2 = homeViewModel.menuCoffeeResult.observeAsState().value

    Surface(

    ) {
        if (list2?.isNotEmpty() == true) {
            HomeScreen(
                menuCoffeeResult = list2
            )
        }
    }

}

@Composable
fun HomeScreen(
    menuCoffeeResult: List<CoffeeDTO>
) {
    renderList(menuCoffeeResult) {}
}