package com.chirayut.coffecompose.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.home.shimmerEffect
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme


@Composable
fun RenderCard(
    coffee: CoffeeDTO,
    modifier: Modifier,
    onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit,
    idBgCardRes: Int,
    isVisible:Boolean = true
) {
    if (isVisible){
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onClickItemDetail.invoke(coffee)
                },
            shadowElevation = 8.dp,
            color = colorResource(id = idBgCardRes),//Color.Green //cardBackgroundColor
        ) {
            Column {

                CategoryImageFromURLWithPlaceHolder(coffee, modifier)

                Row(modifier = Modifier.padding(16.dp)) {

                    Column(modifier = Modifier.weight(8f)) {
                        Text(
                            text = coffee.menuName ?: "",
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = coffee.menuDesc ?: "",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.baseline_restaurant_menu_24),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(2f)
                            .clickable {
                                onAddOrder.invoke(coffee)
                            }
                    )

                }

                /*LazyColumn{
                    items(10) { index ->
                        Text(text = "Item$index")
                    }
                }*/

                /*for(i in 1..10){
                    Text(text = "Item$i")
                }

                Row {
                    for(i in 1..15){
                        Text(text = "ItemRow$i")
                    }
                }*/
            }

        }
    }

}

@Composable
fun renderCardLoading(modifier: Modifier, isVisible:Boolean = true) {
    if (isVisible){
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = modifier
                .fillMaxWidth(),
            shadowElevation = 8.dp,
            color = colorResource(id = R.color.color_secondary),//Color.Green //cardBackgroundColor
        ) {
            Row(modifier = modifier) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(20.dp)
                            .shimmerEffect()
                    )
                }
            }


            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("")
                        .crossfade(true)
                        .build(),
                    //placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = stringResource(R.string.app_name),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    //error = painterResource(R.drawable.ic_launcher_foreground),
                    modifier = modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .shimmerEffect()
                )

                Row(modifier = Modifier
                    .padding(16.dp)
                    .shimmerEffect()) {

                    Column(modifier = Modifier
                        .weight(8f)
                        .shimmerEffect()) {
                        Text(
                            text = "",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.shimmerEffect()
                        )

                        Text(
                            text = "",
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier.shimmerEffect()
                        )
                    }

                }
            }

        }
    }

}


@Composable
fun CategoryImageFromURLWithPlaceHolder(coffee: CoffeeDTO, modifier: Modifier?) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(coffee.menuPic)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        error = painterResource(R.drawable.ic_launcher_foreground),
        modifier = modifier?.height(200.dp)?.fillMaxWidth() ?: Modifier
            .fillMaxWidth()
            .height(200.dp),
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeComposeTheme {
        /*RenderCard(
            CoffeeDTO(menuId = null, menuName = "null", menuDesc = "Desc", menuPic = null),
            modifier = Modifier.padding(16.dp),
            onClickItemDetail = {},
            onAddOrder = {})*/
    }
}
