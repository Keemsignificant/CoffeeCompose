package com.chirayut.coffecompose.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.chirayut.coffecompose.Greeting
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.compose_ui.RenderImageUrl
import com.chirayut.coffecompose.home.renderTopBar
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme

@Composable
fun SettingFragment(viewModel: SettingViewModel = viewModel(), navController: NavController?) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 90.dp)
    ) {

        Column {
            renderTopBar(title = "Setting", isShowOrderIcon = false)
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    RenderImageUrl(
                        path = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVDle-A4jSHngwZxLCvlvWhFXn0vb6vP7ZX3pz4Bc3_RWtdVZRV1lmpinLxor1RwQlKFA&usqp=CAU",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = "Asynchronous")
                        Text(text = "Android Dev")
                        Text(text = "Platinum Member", modifier = Modifier.padding(vertical = 6.dp))
                    }
                }


                cardItem(
                    menu = "Notification", modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )


                cardItem(
                    menu = "Contact Us", modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )


                cardItem(
                    menu = "Payment", modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )


                cardItem(
                    menu = "Create my store caffe", modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )

                cardItem(
                    menu = "LogOut", modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun cardItem(menu: String, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                //onClickItemDetail.invoke(coffee)
            },
        shadowElevation = 8.dp,
        color = colorResource(id = R.color.white),
        border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.color_primary))
    ) {
        Text(text = menu, modifier = modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeComposeTheme {
        SettingFragment(navController = null)
    }
}