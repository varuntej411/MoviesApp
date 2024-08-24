package com.openplay.tech.myapplication.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolBar() {
   // val clickedNavDrawer = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 5.dp, shape = RectangleShape)
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black,
                scrolledContainerColor = Color.Black
            ),
            title = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Welcome ${"VarunTej"}",
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(5.dp),
                        maxLines = 1,
                        minLines = 1,
                        softWrap = true
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.Right,
                    ) {
                        IconButton(onClick = {
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Notifications,
                                contentDescription = "Notifications",
                                Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            )
                        }
                        IconButton(onClick = {
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = "theme",
                                Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            )
                        }
                    }
                }
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        coroutineScope.launch {

                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(2.dp)
                    )
                }
            },
        )
    }
}


@Preview
@Composable
fun CustomToolBarPreview() {
    CustomToolBar()
}