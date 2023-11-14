package com.chirayut.coffecompose.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.compose_ui.renderButtonPrimary
import com.chirayut.coffecompose.home.renderTopBar
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme
import okhttp3.internal.parseCookie

@Composable
fun DetailFragment(
    idMenu: String?,
    viewModel: DetailViewModel = viewModel(),
    navController: NavController
) {
    viewModel.getCoffeeMenuDetailById(idMenu)

    val coffeeMenuList = viewModel.menuCoffeeResult.observeAsState().value
    Column {
        renderTopBar()
        coffeeMenuList?.let { lazyVerticalGrid(it.menuDetailPicList) }

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = coffeeMenuList?.menuName ?: "", style = MaterialTheme.typography.titleMedium, color = colorResource(R.color.color_secondary))
            Text(text = coffeeMenuList?.menuDesc ?: "", style = MaterialTheme.typography.titleSmall, color = colorResource(R.color.color_third))
        }

        renderButtonPrimary(onClickCallBack = {}, buttonText = "Add Order", modifier = Modifier.fillMaxWidth())
    }

}

@Composable
fun lazyVerticalGrid(coffeeMenuList: List<String?>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(coffeeMenuList) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                error = painterResource(R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GreetingPreview() {
    CoffeeComposeTheme {
        //DetailFragment(null)
    }
}
