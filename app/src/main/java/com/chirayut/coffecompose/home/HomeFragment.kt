package com.chirayut.coffecompose.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.navArgument
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.compose_ui.renderList
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFragment(
    navController: NavController,
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
                menuCoffeeResult = coffeeMenuList,
                onAddOrder = {

                },
                onClickItemDetail = {
                    val id = it.menuId
                    navController.navigate(route = "DetailFragment/$id")
                }
            )
        }
    }

}

@Composable
fun HomeScreen(
    menuCoffeeResult: List<CoffeeDTO>,
    onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit
) {

    Column {
        renderTopBar()
        renderList(menuCoffeeResult,
            onClickItemDetail = {
                onClickItemDetail.invoke(it)
            }, onAddOrder = {
                onAddOrder.invoke(it)
            })
    }

}

@Composable
fun renderTopBar() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
        color = colorResource(id = R.color.white)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Mikulo Cafe & Mikulae Bakery",
                color = colorResource(id = R.color.color_primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f)
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_restaurant_menu_24),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                alignment = Alignment.CenterEnd
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeComposeTheme {
        //HomeScreen(arrayListOf())
    }
}