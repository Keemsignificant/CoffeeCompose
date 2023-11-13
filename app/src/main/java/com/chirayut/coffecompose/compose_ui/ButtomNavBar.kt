package com.chirayut.coffecompose.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.home.HomeFragment
import com.chirayut.coffecompose.model.MenuBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNav(menuBar: List<MenuBar>, navController: NavController) {
    val navController = rememberNavController()

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    var showBottomBar by rememberSaveable { mutableStateOf(true) }

    /* showBottomBar = when (navStackBackEntry?.destination?.route) {
         "RouteOfScreenA" -> false // on this screen bottom bar should be hidden
         "RouteOfScreenB" -> false // here too
         else -> true // in all other cases show bottom bar
     }*/

    Scaffold(
        bottomBar = {
            //CustomBottomBar(navController = navController, currentDestination)
            if (showBottomBar) CustomBottomBar(
                navController = navController,
                currentDestination,
                menuBar
            )
        }
    ) {
        Modifier.padding(it)
        MainBottomNavGraph(
            navController = navController,
            menuBar
        )
    }
}

@Composable
fun CustomBottomBar(
    navController: NavController,
    currentDestination: NavDestination?,
    menuBar: List<MenuBar>
) {
    val screens = menuBar


    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            //.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            //.background(Color.Transparent)
            .fillMaxWidth(),
        shadowElevation = 8.dp,
        color = colorResource(id = R.color.color_primary)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .background(Color.Transparent)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                CustomAddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }

    }

}

@Composable
fun CustomAddItem(
    screen: MenuBar,
    currentDestination: NavDestination?,
    navController: NavController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.menuRoute } == true

    val background =
        if (selected) colorResource(id = R.color.color_primary) else colorResource(id = R.color.color_primary) //Color.Transparent

    val contentColor =
        if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            //.height(40.dp)
            //.clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.menuRoute ?: "") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Column(
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            /*AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(if (selected) screen.menuIconActive else screen.menuIcon)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_launcher_foreground),
                modifier = Modifier.width(50.dp).height(50.dp),
            )*/
            Icon(painter = painterResource(id = R.drawable.baseline_restaurant_menu_24), contentDescription = null,
                /*modifier = Modifier.width(40.dp).height(40.dp),*/)

            Text(
                text = screen.menuBar ?: "Empty",
                color = contentColor
            )
        }
    }
}

@Composable
fun MainBottomNavGraph(
    navController: NavController,
    menuBar: List<MenuBar>
) {

    NavHost(
        navController = navController as NavHostController,
        startDestination = menuBar[0].menuRoute ?: ""
    ) {
        composable(route = menuBar[0].menuRoute ?: "") {
            //MainScreen(navController = navController)
            HomeFragment()
        }
        composable(route = menuBar[1].menuRoute ?: "") {
            //ListScreen()
        }
        composable(route = menuBar[2].menuRoute ?: "") {
            //SettingScreen()
        }
    }
}
