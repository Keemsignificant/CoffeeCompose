package com.chirayut.coffecompose.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chirayut.coffecompose.MainActivity
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.compose_ui.CustomDialog
import com.chirayut.coffecompose.host.HostFragment
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreenFragment(
    viewModel: SplashScreenViewModel = viewModel(),
) {

    fun launch() {
        viewModel.checkAppStatus()
    }

    launch()

    val checkAppResult = viewModel.checkAppResult.observeAsState().value
    //var isShowDialogLoading by remember { mutableStateOf(true) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.color_secondary)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .padding(32.dp)
                .size(100.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }

    if (checkAppResult.isNullOrEmpty()) {
        viewModel.isShowDialogLoading.value = true
        CustomDialog(
            isShowDialogLoading = viewModel.isShowDialogLoading.value ?: true,
            onDismissRequest = {
                viewModel.isShowDialogLoading.value = false
            })
    } else {
        viewModel.isShowDialogLoading.value = false
        navigateToHostScreen()
    }

}


@Composable
fun navigateToHostScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "host") {
        composable("host") { HostFragment(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeComposeTheme {
        //SplashScreenFragment()
    }
}