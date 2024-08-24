package com.openplay.tech.myapplication.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    contentPaddingValues: PaddingValues
) {
   // val productViewModel = hiltViewModel<ProductViewModel>()
   // val state by productViewModel.states.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = contentPaddingValues),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(key1 = Unit) {
            coroutineScope.launch {
                listState.firstVisibleItemIndex > 0
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            items(50) {
                ListItem(
                    modifier = Modifier
                        .fillMaxSize(),
                    headlineContent = { Text(text = "products") },
                    tonalElevation = 10.dp,
                    leadingContent = { Text(text = "$it") }
                )
            }
        }

    }
}

@Preview
@Composable
fun ProductListScreenPreview() {
    HomeScreen(contentPaddingValues = PaddingValues())
}