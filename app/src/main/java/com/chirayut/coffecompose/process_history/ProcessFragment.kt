package com.chirayut.coffecompose.process_history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.chirayut.coffecompose.ui.theme.CoffeeComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProcessFragment(
    viewModel: ProcessViewModel = viewModel(),
    navController: NavController?
) {
    viewModel.getProcessHistory()

    val processList = viewModel.processHistoryResult.observeAsState().value

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

@Preview(showBackground = true)
@Composable
fun PrecessFragmentPreview() {
    CoffeeComposeTheme {
        ProcessFragment(navController = null)
    }
}