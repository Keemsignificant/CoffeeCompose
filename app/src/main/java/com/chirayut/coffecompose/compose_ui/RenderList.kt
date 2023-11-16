package com.chirayut.coffecompose.compose_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.model.CoffeeDTO
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun renderList(
    coffee: List<CoffeeDTO>, onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit,
    loadMoreItems: () -> Unit,
    content: @Composable (Int) -> Unit,
    listCount: Int
) {

    val listState = rememberLazyListState()
    // Remember a CoroutineScope to be able to launch

    LazyColumn(state = listState) {
        items(listCount) { index ->

            val coffeeItem = coffee[index]
            content(index)
            if (index == coffee.size - 1) {
                loadMoreItems()
            }

            RenderCard(
                coffee = coffeeItem,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                onClickItemDetail = { coffeeItem ->
                    onClickItemDetail(coffeeItem)
                },
                onAddOrder = { coffeeItem ->
                    onAddOrder(coffeeItem)
                },
                idBgCardRes = R.color.color_secondary
            )
        }
    }
}


@Composable
fun InfiniteScrollList(
    itemCount: Int,
    loadMoreItems: () -> Unit,
    content: @Composable (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(itemCount) { index ->
            content(index)
            if (index == itemCount - 1) {
                loadMoreItems()
            }
        }
    }
}


@Composable
fun renderListString(
    list: List<String>, onClickItemDetail: (String) -> Unit,
    onAddOrder: (String) -> Unit,
    loadMoreItems: () -> Unit,
    content: @Composable (Int) -> Unit,
    listCount: Int
) {

    val listState = rememberLazyListState()
    LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
        items(listCount) { index ->

            val coffeeItem = list[index]
            content(index)
            if (index == list.size - 1) {
                loadMoreItems()
            }
            Text(text = coffeeItem)
        }
    }
}