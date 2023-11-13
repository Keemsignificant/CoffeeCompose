package com.chirayut.coffecompose.compose_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chirayut.coffecompose.model.CoffeeDTO

@Composable
fun renderList(coffee: List<CoffeeDTO>, onClick: (CoffeeDTO) -> Unit) {
    LazyColumn {
        items(coffee) {
            RenderCard(
                coffee = it,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}