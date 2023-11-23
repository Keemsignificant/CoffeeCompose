package com.chirayut.coffecompose.process_history

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chirayut.coffecompose.R
import com.chirayut.coffecompose.compose_ui.CategoryImageFromURLWithPlaceHolder
import com.chirayut.coffecompose.compose_ui.renderButtonPrimary
import com.chirayut.coffecompose.detail.lazyVerticalGrid
import com.chirayut.coffecompose.home.renderCard
import com.chirayut.coffecompose.home.renderTopBar
import com.chirayut.coffecompose.model.ProcessHistory
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessFragment(
    viewModel: ProcessViewModel = viewModel(),
    navController: NavController?
) {

    //viewModel.getProcessHistory()

    val processList = viewModel.processHistoryResult.observeAsState().value

    //val state by viewModel.state.collectAsState()
    //HomeContent(state, viewModel::onSwipeToDelete)

    if (!processList.isNullOrEmpty()) {

        Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        bottom = it.calculateBottomPadding()
                    )
                    .padding(bottom = 90.dp) // <<-- or simply this
            ) {
                LazyColumn {

                    items(processList) { item ->

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                                .clickable {
                                    // onClickItemDetail.invoke(item)
                                },
                            shadowElevation = 8.dp,
                            color = Color.White //cardBackgroundColor
                        ) {
                            Row {

                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .weight(1f)
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(item.processPic)
                                            .crossfade(true)
                                            .build(),
                                        placeholder = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = stringResource(R.string.app_name),
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center,
                                        error = painterResource(R.drawable.ic_launcher_foreground),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                            .weight(1f),
                                    )

                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(item.menuPic)
                                            .crossfade(true)
                                            .build(),
                                        placeholder = painterResource(R.drawable.ic_launcher_background),
                                        contentDescription = stringResource(R.string.app_name),
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center,
                                        error = painterResource(R.drawable.ic_launcher_foreground),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                            .weight(1f),
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .weight(1f)
                                ) {

                                    Column(modifier = Modifier.weight(8f)) {
                                        Text(
                                            text = item.processTitle ?: "",
                                            style = MaterialTheme.typography.titleMedium
                                        )

                                        Text(
                                            text = item.processDescription ?: "",
                                            style = MaterialTheme.typography.titleSmall
                                        )

                                        Text(
                                            text = item.processTime ?: "",
                                            style = MaterialTheme.typography.titleSmall
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
private fun HomeContent(
    state: ChatScreenUiState,
    onSwipeToDelete: (ChatCardItemContent) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {

        ChatCardSection(
            state, onSwipeToDelete
        )
    }

}

@Composable
fun ChatCardItem(
    chatCardItem: ChatCardItemContent,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 16.dp, end = 16.dp)
            .clickable { /* navigate to chat*/ }
    ) {
        Box(
            Modifier
                .size(50.dp)
                .clip(CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_restaurant_menu_24),
                contentDescription = "Messenger image",
                contentScale = ContentScale.Crop
            )
        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = chatCardItem.messengerName,
                fontSize = 15.sp,
                //fontFamily = Ubuntu,
                fontWeight = FontWeight.Bold,

                )
            Text(
                text = chatCardItem.message,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,

                )
        }

        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = chatCardItem.message,
                //fontFamily = Ubuntu,
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin
            )
            /*if (chatCardItem.messageNumberBadge > 0) {
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEF5350)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = chatCardItem.messageNumberBadge.toString(),
                        fontSize = 12.sp,
                        //fontFamily = Ubuntu,
                        color = Color.White,
                        fontWeight = FontWeight.Thin
                    )
                }
            }*/
        }

    }
}

@Composable
fun ChatCardSection(
    chatCardItems: ChatScreenUiState,
    onSwipeToDelete: (ChatCardItemContent) -> Unit
) {


    LazyColumn(Modifier.fillMaxSize()) {

        items(chatCardItems.messages) {
            val delete = SwipeAction(
                onSwipe = {
                    //onSwipeToDelete(it)
                    Log.d("OnSwipeToDelete", chatCardItems.messages.size.toString())
                },
                icon = {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete chat",
                        modifier = Modifier.padding(16.dp),
                        tint = Color.White
                    )
                }, background = Color.Red.copy(alpha = 0.5f),
                isUndo = true
            )
            val archive = SwipeAction(
                onSwipe = {},
                icon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_restaurant_menu_24),
                        contentDescription = "archive chat",
                        modifier = Modifier.padding(16.dp),

                        tint = Color.White

                    )
                }, background = Color(0xFF50B384).copy(alpha = 0.7f)
            )
            SwipeableActionsBox(
                modifier = Modifier,
                //swipeThreshold = 10.dp,
                startActions = listOf(archive),
                endActions = listOf(delete)
            ) {
                ChatCardItem(it)
            }

        }
    }


}



@Preview(showBackground = true)
@Composable
fun PrecessFragmentPreview() {
    CoffeeComposeTheme {
        ProcessFragment(navController = null)
    }
}