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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chirayut.coffecompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBottomNav() {
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
            if (showBottomBar) CustomBottomBar(navController = navController, currentDestination)
        }
    ) {
        Modifier.padding(it)
        MainBottomNavGraph(
            navController = navController
        )
    }
}

@Composable
fun CustomBottomBar(navController: NavHostController, currentDestination: NavDestination?) {
   // val screens = MenuItemUseCase().getMenuItem()



    /*Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            //.padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            //.background(Color.Transparent)
            .fillMaxWidth(),
        shadowElevation = 8.dp,
        color = colorResource(id = R.color.blue)
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

    }*/

}

/*
@Composable
fun CustomAddItem(
    screen: MenuItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) */
/*MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)*//*
 colorResource(id = R.color.blue) else colorResource(
            id = R.color.blue
        ) //Color.Transparent

    val contentColor =
        if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            //.height(40.dp)
            //.clip(CircleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
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
            Icon(
                //painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                painter = painterResource(id = if (selected) screen.menuIconFocus else screen.menuIcon),
                contentDescription = "icon",
                tint = contentColor
            )
            Text(
                text = screen.menuTile,
                color = contentColor
            )
        }
    }
}
*/

@Composable
fun MainBottomNavGraph(
    navController: NavHostController
) {

    /*val allMenu = MenuItemUseCase().getMenuItem()
    NavHost(
        navController = navController,
        startDestination = allMenu[0].route
    ) {
        composable(route = allMenu[0].route) {
            MainScreen(navController = navController)
        }
        composable(route = allMenu[1].route) {
            ListScreen()
        }
        composable(route = allMenu[2].route) {
            SettingScreen()
        }
    }*/
}
