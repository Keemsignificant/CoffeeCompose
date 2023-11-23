package com.chirayut.coffecompose.home

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.compose_ui.RenderCard
import com.chirayut.coffecompose.compose_ui.renderCardLoading
import com.chirayut.coffecompose.compose_ui.renderList
import com.chirayut.coffecompose.model.CoffeeDTO
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFragment(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
) {

    val coffeeMenuList = homeViewModel.menuCoffeeResult.observeAsState().value

    // this variable use to handle list state
    val coffeeListHandle = remember { mutableStateListOf<CoffeeDTO>() }
    //https://medium.com/geekculture/add-remove-in-lazycolumn-list-aka-recyclerview-jetpack-compose-7c4a2464fc9f

    // Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()


    val isShowViewLoadMoreItemState = remember { mutableStateOf(false)}
    coffeeMenuList?.let {
        if (coffeeListHandle.isEmpty()) {
            coffeeListHandle.addAll(it)
        }
    }

    var isLoading by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = true) {
        delay(2000)
        isLoading = false
    }

    Scaffold {
        if (coffeeMenuList?.isNotEmpty() == true) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it.calculateBottomPadding()
                    )
                    .padding(bottom = 90.dp) // <<-- or simply this
            ) {
                // Your content
                HomeScreen(
                    menuCoffeeResult = coffeeListHandle,
                    onAddOrder = {

                    },
                    onClickItemDetail = {
                        val id = it.menuId
                        navController.navigate(route = "DetailFragment/$id")
                    },
                    isLoading = isLoading,
                    onLoadMoreItems = {
                        if (coffeeListHandle.isNotEmpty() && !isLoading && !isShowViewLoadMoreItemState.value) {
                            val conditionIsNotLastPage = coffeeListHandle.size < 10
                            if (conditionIsNotLastPage) {
                                addItemMenu(coffeeListHandle)
                                coroutineScope.launch {
                                    isShowViewLoadMoreItemState.value = true
                                    delay(2000)
                                    isShowViewLoadMoreItemState.value = false
                                }
                            }
                        }
                    },
                    homeViewModel = homeViewModel,
                    isShowViewLoadMoreItemState = isShowViewLoadMoreItemState.value
                )
            }

        }
    }

}

private fun addItemMenu(coffeeListHandle: SnapshotStateList<CoffeeDTO>) {
    val listAdd = listOf(
        CoffeeDTO(
            menuId = "10",
            menuName = "NameAdd ${coffeeListHandle.size + 1}",
            menuDesc = "MenuDesc",
            menuPic = null,
            menuDetailPicList = listOf()
        )
    )
    coffeeListHandle.addAll(
        listAdd
    )
}

@Composable
fun HomeScreen(
    menuCoffeeResult: List<CoffeeDTO>,
    onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit,
    isLoading: Boolean,
    onLoadMoreItems: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(),
    isShowViewLoadMoreItemState:Boolean
) {

    Column {
        renderTopBar()
        //itemCoffeeHandleLoadMore(menuCoffeeResult, onClickItemDetail, onAddOrder)
        itemLoadingCoffeeList(
            menuCoffeeResult,
            onClickItemDetail,
            onAddOrder,
            isLoading,
            onLoadMoreItems,
            homeViewModel,
            isShowViewLoadMoreItemState
        )
    }

}

@Composable
fun itemCoffeeHandleLoadMore(
    menuCoffeeResult: List<CoffeeDTO>,
    onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit,
) {
    renderList(menuCoffeeResult,
        onClickItemDetail = {
            onClickItemDetail.invoke(it)
        }, onAddOrder = {
            onAddOrder.invoke(it)
        }, loadMoreItems = {
        }, content = { index ->
        },
        listCount = menuCoffeeResult.size
    )
}

@Composable
fun renderTopBar() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth(),
        color = colorResource(id = R.color.white)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Mikulo Cafe & Mikulae Bakery",
                color = colorResource(id = R.color.color_primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f)
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_restaurant_menu_24),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                alignment = Alignment.CenterEnd
            )
        }

    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun itemLoadingCoffeeList(
    menuCoffeeResult: List<CoffeeDTO>,
    onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit,
    isLoading: Boolean,
    onLoadMoreItemsCallback: () -> Unit,
    homeViewModel: HomeViewModel,
    isShowViewLoadMoreItemState: Boolean
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val isLoadingItem = homeViewModel.isLoadingItem.observeAsState().value


    coroutineScope.launch {
        delay(3000)
        homeViewModel.isLoadingItem.value = false
    }

    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState
    ) {
        items(menuCoffeeResult.size) { index ->
            val coffeeItem = menuCoffeeResult[index]
            //content(index)
            val isLastItem = index == menuCoffeeResult.size - 1
            if (isLastItem) {
                onLoadMoreItemsCallback.invoke()
            }
            renderCardCondition(
                isShowLoad = isLoading,
                modifier = modifier,
                menuCoffeeResult = menuCoffeeResult,
                onClickItemDetail = {
                    onClickItemDetail.invoke(it)
                },
                onAddOrder = {

                },
                index = index
            )
        }

        if (isShowViewLoadMoreItemState){
            items(2){
                renderCardCondition(
                    isShowLoad = true,
                    modifier = modifier,
                    menuCoffeeResult = menuCoffeeResult,
                    onClickItemDetail = {
                        onClickItemDetail.invoke(it)
                    },
                    onAddOrder = {

                    },
                    index = it
                )
            }
        }


    }
}


@Composable
fun renderCardCondition(
    isShowLoad: Boolean, modifier: Modifier,
    menuCoffeeResult: List<CoffeeDTO>,
    onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit,
    index: Int
) {

    Column {
        /* if (isShowLoad) {
             renderCardLoading(modifier = modifier.alpha(1f))
             renderCard(
                 menuCoffeeResult = menuCoffeeResult,
                 index = index,
                 onClickItemDetail = onClickItemDetail,
                 onAddOrder = onAddOrder,
                 modifier = modifier.alpha(0f)
             )
         } else {
             renderCardLoading(modifier = modifier.alpha(0f))
             renderCard(
                 menuCoffeeResult = menuCoffeeResult,
                 index = index,
                 onClickItemDetail = onClickItemDetail,
                 onAddOrder = onAddOrder,
                 modifier = modifier.alpha(1f)
             )
         }*/
        renderCardLoading(modifier = modifier, isVisible = isShowLoad)
        renderCard(
            menuCoffeeResult = menuCoffeeResult,
            index = index,
            onClickItemDetail = onClickItemDetail,
            onAddOrder = onAddOrder,
            modifier = modifier, isVisible = !isShowLoad
        )

    }
}

@Composable
fun renderCard(
    menuCoffeeResult: List<CoffeeDTO>,
    index: Int,
    onClickItemDetail: (CoffeeDTO) -> Unit,
    onAddOrder: (CoffeeDTO) -> Unit,
    idBgCardRes: Int = R.color.color_secondary,
    modifier: Modifier,
    isVisible: Boolean = true
) {
    RenderCard(
        coffee = menuCoffeeResult[index],
        modifier = modifier,
        onClickItemDetail = { coffeeItem ->
            onClickItemDetail(coffeeItem)
        },
        onAddOrder = { coffeeItem ->
            onAddOrder(coffeeItem)
        },
        idBgCardRes = idBgCardRes,
        isVisible = isVisible
    )
}

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoadingCallback: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {
        //contentLoading(modifier)
        renderCardLoading(modifier = modifier)
    } else {
        contentAfterLoadingCallback()
    }
}

@Composable
fun contentLoading(modifier: Modifier) {
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
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    //0xFFE7E7ED
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeComposeTheme {
        //HomeScreen(arrayListOf())
    }
}