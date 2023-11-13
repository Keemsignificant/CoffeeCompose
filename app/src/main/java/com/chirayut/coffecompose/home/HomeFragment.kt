package com.chirayut.coffecompose.home

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chirayut.coffecompose.compose_ui.renderList
import com.chirayut.coffecompose.model.CoffeeDTO

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFragment(
    homeViewModel: HomeViewModel = viewModel()
) {


    fun launch() {
        homeViewModel.getMenuCoffee()
    }

    launch()

    val coffeeMenuList = homeViewModel.menuCoffeeResult.observeAsState().value

    Scaffold {
        if (coffeeMenuList?.isNotEmpty() == true) {
            HomeScreen(
                menuCoffeeResult = coffeeMenuList
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