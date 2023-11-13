package com.chirayut.coffecompose.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.chirayut.coffecompose.model.Coffee
import com.chirayut.coffecompose.model.CoffeeDTO


@Composable
fun RenderCard(coffee: CoffeeDTO, modifier: Modifier, onClick: (CoffeeDTO) -> Unit) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.fillMaxWidth().clickable {
            onClick.invoke(coffee)
        },
        shadowElevation = 8.dp,
        color = colorResource(id = R.color.color_secondary),//Color.Green //cardBackgroundColor
    ) {
        Column {

            /*Image(
                painter = rememberAsyncImagePainter(user.userImageUrl),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )*/
            CategoryImageFromURLWithPlaceHolder(coffee, modifier)

            /* Image(
                 painter = painterResource(id = R.drawable.ic_launcher_foreground),
                 contentDescription = "Desc",
                 contentScale = ContentScale.Crop,
                 modifier = Modifier.fillMaxWidth()
             )*/

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = coffee.menuName ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

    }
}


@Composable
fun CategoryImageFromURLWithPlaceHolder(coffee: CoffeeDTO,  modifier: Modifier?){
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
        modifier = modifier?.height(200.dp)?.fillMaxWidth() ?:Modifier.fillMaxWidth().height(200.dp),
    )
}
