package com.chirayut.coffecompose.compose_ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.chirayut.coffecompose.R

@Composable
fun renderButtonPrimary(onClickCallBack: () -> Unit, buttonText: String, modifier: Modifier?) {
    Button(
        onClick = {
            onClickCallBack.invoke()
        }, colors = ButtonDefaults.buttonColors(
            contentColor = colorResource(R.color.white),
            containerColor = colorResource(
                id = R.color.color_secondary
            )
        ),
        modifier = modifier ?: Modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,

            )
    }
}