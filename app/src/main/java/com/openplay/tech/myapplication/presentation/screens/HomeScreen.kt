package com.openplay.tech.myapplication.presentation.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesViewModel
import com.openplay.tech.myapplication.MainNavGraphs.Route
import com.openplay.tech.myapplication.R
import com.openplay.tech.myapplication.commonutils.SubImageLoader
import com.openplay.tech.myapplication.commonutils.UiText
import com.openplay.tech.myapplication.domain.model.MoviesModel
import com.openplay.tech.myapplication.presentation.components.ShimmerItem
import com.openplay.tech.myapplication.presentation.subcomponent.MoviePager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    contentPaddingValues: PaddingValues,
    wishListClicked: (MoviesModel) -> Unit,
    unWishListClicked: (MoviesModel) -> Unit,
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val moviesViewModel = hiltViewModel<MoviesViewModel>()
    val uiState = moviesViewModel.uiStates.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPaddingValues)
            .paint(
                painterResource(id = R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Welcome, Varun Tej...",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Card(
                    modifier = Modifier
                        .size(60.dp)
                        .border(
                            width = 3.dp,
                            shape = CircleShape,
                            color = Color.White
                        ),
                    shape = CircleShape,
                ) {
                    SubImageLoader(
                        imageUrl = "https://i.pravatar.cc",
                        context = context
                    )
                }
            }

            if (uiState.value.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPaddingValues),
                    contentAlignment = Alignment.Center,
                ) {
                    ShimmerItem(
                        isLoading = uiState.value.isLoading,
                        contentAfterLoading = {
                            uiState.value.data?.let {
                                MoviePager(
                                    moviesViewModel = moviesViewModel,
                                    item = uiState.value.data!!.first(),
                                    onMoreDetailsClicked = {},
                                    doWishListClicked = {},
                                    undoWishListClicked = {}
                                )
                            }
                        }
                    )
                }
            }

            if (uiState.value.error !is UiText.Idle) {
                Box(
                    modifier = Modifier
                        .padding(contentPaddingValues)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = uiState.value.error.getString())
                }
            }

            uiState.value.data?.let {

                val movieItems: List<MoviesModel> = if (uiState.value.data!!.isEmpty()) {
                    emptyList()
                } else {
                    uiState.value.data!!.subList(0, 5)
                }

                val pageState = rememberPagerState(
                    pageCount = { movieItems.size }
                )

                var selectedTabIndex by remember {
                    mutableIntStateOf(0)
                }

                // movie tabs
                val tabItems = listOf(
                    "Trending",
                    "New",
                    "Fantasy",
                    "Sci-fi",
                    "Action",
                    "Adventure",
                    "Bollywood",
                    "Romantic"
                )

                CustomTabRow(
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = {
                        selectedTabIndex = it
                    },
                    tabItems = tabItems,
                    modifier = Modifier
                        .border(
                            border = BorderStroke(2.dp, color = Color.White),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = contentPaddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    HorizontalPager(
                        pageSize = PageSize.Fixed(320.dp),
                        beyondBoundsPageCount = 2,
                        contentPadding = PaddingValues(horizontal = 0.dp),
                        pageSpacing = 1.dp,
                        state = pageState,
                        modifier = Modifier
                            .padding(top = 10.dp, end = 10.dp, start = 10.dp)
                    ) { index ->
                        LaunchedEffect(Unit) {
                            while (true) {
                                delay(3000)
                                coroutineScope.launch {
                                    pageState.animateScrollToPage(
                                        (pageState.currentPage + 1) %
                                                uiState.value.data!!.size
                                    )
                                }
                            }
                        }
                        ShimmerItem(
                            isLoading = uiState.value.isLoading,
                            contentAfterLoading = {
                                uiState.value.data?.let {
                                    MoviePager(
                                        moviesViewModel = moviesViewModel,
                                        item = it[index],
                                        onMoreDetailsClicked = {
                                           navController.navigate(Route.MoreDetailsScreen.screen)
                                        },
                                        doWishListClicked = {
                                            uiState.value.data?.let {
                                                wishListClicked.invoke(it[index])
                                            }
                                        },
                                        undoWishListClicked = {
                                            uiState.value.data?.let {
                                                unWishListClicked.invoke(it[index])
                                            }
                                        }
                                    )
                                }
                            }
                        )
                    }

                    //indicator
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(top = 400.dp, bottom = 10.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        uiState.value.data?.subList(0, 5).let {
                            Indicators(size = it!!.size, index = pageState.currentPage)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearEasing
        ),
        label = ""
    )

    Box(
        modifier = Modifier
            .height(6.dp)
            .width(width.value)
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = if (isSelected) Color.White else Color.Black
            )
    ) {
    }
}

@Composable
fun CustomTabRow(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    tabItems: List<String>,
    modifier: Modifier = Modifier,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        divider = {},
        indicator = {},
        edgePadding = 5.dp,
        modifier = modifier
    ) {
        tabItems.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                onClick = {
                    onTabSelected(index)
                }) {
                Text(
                    text = item,
                    fontFamily = FontFamily.Serif,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }
}


@Preview
@Composable
fun ProductListScreenPreview() {
    HomeScreen(navController = rememberNavController(), contentPaddingValues = PaddingValues(), wishListClicked = {}, unWishListClicked = {})
}