package com.openplay.tech.myapplication.MainNavGraphs

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openplay.tech.myapplication.R
import com.openplay.tech.myapplication.ui.theme.Purple40
import com.openplay.tech.myapplication.ui.theme.PurpleGrey40


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(
    navItems: List<BottomNavigationBarItems>,
    selectedItem: Int,
    onClickItem: (Int) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .clip(RoundedCornerShape(20)),
        tonalElevation = 30.dp,
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = {
                    onClickItem.invoke(index)
                },
                icon = {
                    Column(
                        modifier = Modifier.padding(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                        Text(
                            text = item.title,
                            fontSize = 10.sp,
                            style = MaterialTheme.typography.labelLarge,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center
                        )
                    }
//                    BadgedBox(
//                        badge = {
//                            if (item.notificationCount > 0) {
//                                Badge(containerColor = Pink40) {
//                                    Text(
//                                        text = item.notificationCount.toString(),
//                                        fontSize = 10.sp,
//                                        color = Color.White,
//                                        style = MaterialTheme.typography.labelSmall,
//                                        fontFamily = FontFamily.SansSerif,
//                                        textAlign = TextAlign.Center
//                                    )
//                                }
//                            } else if (item.hasBadgeDot) {
//                                Badge(containerColor = Pink40)
//                            }
//                        }
//                    ) {
//
//                    }
                },
//                colors = NavigationDrawerItemDefaults.colors(
//                    unselectedBadgeColor = Color.White,
//                    unselectedTextColor = Color.Green,
//                    selectedBadgeColor = Color.Black,
//                    selectedTextColor = Color.Red,
//                    unselectedIconColor = Color.DarkGray,
//                    selectedIconColor = Color.Black,
//                    selectedContainerColor = Color.Magenta,
//                    unselectedContainerColor = Color.Blue
//                ),
                colors = NavigationBarItemColors(
                    unselectedIconColor = PurpleGrey40,
                    unselectedTextColor = PurpleGrey40,
                    selectedIconColor = Purple40,
                    selectedTextColor = Purple40,
                    selectedIndicatorColor = MaterialTheme.colorScheme.onPrimary,
                    disabledIconColor = PurpleGrey40,
                    disabledTextColor = PurpleGrey40
                )
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}

data class BottomNavigationBarItems(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String,
    val hasBadgeDot: Boolean = false,
)

val bottomBarItems = listOf(
    BottomNavigationBarItems(
        title = "Home",
        icon = R.drawable.unselectedhome,
        route = "home",
        hasBadgeDot = false,
    ),
    BottomNavigationBarItems(
        title = "Search",
        icon = R.drawable.unselectedsearch,
        route = "search",
        hasBadgeDot = false,
    ),
    BottomNavigationBarItems(
        title = "Watchlist",
        icon = R.drawable.watch_list_24,
        route = "watchlist",
        hasBadgeDot = false,
    ),
    BottomNavigationBarItems(
        title = "Profile",
        icon = R.drawable.profile,
        route = "profile",
        hasBadgeDot = false
    )
)

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavigationBar(
        navItems = bottomBarItems,
        selectedItem = 0,
        onClickItem = {}
    )
}