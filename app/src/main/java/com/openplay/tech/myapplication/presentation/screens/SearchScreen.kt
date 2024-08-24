package com.openplay.tech.myapplication.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun SearchScreen(contentPaddingValues: PaddingValues) {

    val textField by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = contentPaddingValues)
    ) {

    }

}

@Preview
@Composable
fun PreviewSearchScreen() {
    SearchScreen(contentPaddingValues = PaddingValues())
}