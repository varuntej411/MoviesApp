package com.openplay.tech.myapplication.presentation.subcomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesViewModel
import com.openplay.tech.myapplication.R
import com.openplay.tech.myapplication.domain.model.MoviesModel
import com.openplay.tech.myapplication.ui.theme.Gold_Bright

@Composable
fun MoviePager(
    moviesViewModel: MoviesViewModel,
    item: MoviesModel,
    onMoreDetailsClicked: (String) -> Unit,
    doWishListClicked: (MoviesModel) -> Unit,
    undoWishListClicked: (MoviesModel) -> Unit,
) {

    var wishlistVisibility by rememberSaveable { mutableStateOf(false) }

    val uiState = moviesViewModel.uiStates.collectAsStateWithLifecycle()

    val icon =
        if (wishlistVisibility) painterResource(id = R.drawable.outline_favorite_24) else painterResource(
            id = R.drawable.outline_favorite_white_24
        )

    Box(modifier = Modifier.padding(top = 20.dp)) {
        Box(
            modifier = Modifier.padding(bottom = 40.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(bottom = 50.dp)
                    .align(Alignment.TopCenter),
                colors = CardDefaults.cardColors(
                    contentColor = Color.Red,
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(32.dp)
            ) {
                IconButton(
                    onClick = {
                        wishlistVisibility = !wishlistVisibility
                        // Handle wishlist icon click
                        if (wishlistVisibility) {
                            uiState.value.data?.let {
                                doWishListClicked.invoke(item)
                            }
                        }else {
                            uiState.value.data?.let {
                                undoWishListClicked.invoke(item)
                            }
                        }
                    },
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp)
                        .padding(top = 5.dp, end = 20.dp)
                        .align(Alignment.End),
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        tint = if (wishlistVisibility) Color.Red else Color.White
                    )
                }
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${item.poster_path}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "image"
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(180.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Transparent
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .align(Alignment.BottomCenter),
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .height(10.dp)
                        .background(Color.Transparent)
                        .padding(vertical = 15.dp, horizontal = 15.dp)
                ) {
                    Text(
                        text = item.title!!,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 24.sp,
                        color = Color.White,
                        lineHeight = 32.sp,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = item.genre_ids!!.joinToString(", "),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 0.dp)
                    )
                    Text(
                        text = item.release_date!!,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = Color.White,
                    )

                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 15.dp)
                        .align(Alignment.BottomCenter),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(0.5f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.mr_star),
                            contentDescription = null,
                            tint = Gold_Bright
                        )
                        val rating = formatNumber(item.vote_average!!)
                        Text(
                            text = "${rating}/10",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Button(
                        onClick = {
                            onMoreDetailsClicked.invoke(item.title!!)
                        },
                        colors = ButtonDefaults.buttonColors(Gold_Bright),
                        modifier = Modifier.weight(0.7f)
                    ) {
                        Text(
                            text = "More Details",
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }
                }
            }

        }

    }
}


@Preview
@Composable
fun MoviePagerPreview() {
    MoviePager(
        moviesViewModel = hiltViewModel<MoviesViewModel>(),
        item = MoviesModel(
            adult = false,
            backdrop_path = "/lgkPzcOSnTvjeMnuFzozRO5HHw1.jpg",
            genre_ids = arrayListOf(
                16,
                10751,
                35,
                28
            ),
            id = 519182,
            original_language = "en",
            original_title = "Despicable Me 4",
            overview = "\"Gru and Lucy and their girls—Margo, " +
                    "Edith and Agnes—welcome a new member to the Gru family, " +
                    "Gru Jr., who is intent on tormenting his dad. Gru also faces " +
                    "a new nemesis in Maxime Le Mal and his femme fatale girlfriend " +
                    "Valentina," +
                    " forcing the family to go on the run.",
            popularity = 2384.852,
            poster_path = "/wWba3TaojhK7NdycRhoQpsG0FaH.jpg",
            release_date = "2024-06-20",
            title = "Despicable Me 4",
            video = false,
            vote_average = 7.296,
            vote_count = 1206
        ),
        onMoreDetailsClicked = {},
        doWishListClicked = {},
        undoWishListClicked = {}
    )
}

@Preview
@Composable
fun MoviePagerPreview2() {

}

fun formatNumber(value: Double): String {
    val formattedValue = "%.1f".format(value)  // Round to one decimal place
    val withoutTrailingZeros = formattedValue.replace(Regex("\\.?0*$"), "")
    return withoutTrailingZeros
}