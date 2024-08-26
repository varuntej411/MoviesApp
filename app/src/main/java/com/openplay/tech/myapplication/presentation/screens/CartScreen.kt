package com.openplay.tech.myapplication.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.openplay.tech.myapplication.commonutils.UiText
import com.openplay.tech.myapplication.presentation.viewmodels.WishListViewModel

@Composable
fun CartScreen(
    navController: NavHostController,
    contentPaddingValues: PaddingValues,
) {


    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        contentColor = Color.White,
        color = Color.White
    ) {
        val wishListViewModel: WishListViewModel = hiltViewModel<WishListViewModel>()
        val uiState = wishListViewModel.uiState.collectAsStateWithLifecycle()


        if (uiState.value.isLoading) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        }

        if (uiState.value.error !is UiText.Idle) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = uiState.value.error.getString(),
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.Serif,
                    fontSize = 24.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        uiState.value.data?.let { list ->
            if (list.isEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Not Data Found",
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily.Serif,
                        fontSize = 24.sp,
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    items(list.size) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(bottom = 50.dp),
                            colors = CardDefaults.cardColors(
                                contentColor = Color.Black,
                                containerColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(32.dp)
                        ) {

                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500${list.get(it).poster_path}",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = "image"
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 15.dp, horizontal = 15.dp)
                        ) {



                            Text(
                                text = list.get(it).title!!,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 24.sp,
                                color = Color.Black,
                                lineHeight = 32.sp,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = list.get(it).release_date!!,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = Color.Black,
                                style = MaterialTheme.typography.bodySmall
                            )

                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCartScreen() {
    CartScreen(
        navController = rememberNavController(),
        contentPaddingValues = PaddingValues()
    )
}