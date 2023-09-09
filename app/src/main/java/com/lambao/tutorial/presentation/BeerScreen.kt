package com.lambao.tutorial.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.lambao.tutorial.domain.Beer
import com.lambao.tutorial.extension.showToast

@Composable
fun BeerScreen(
    beers: LazyPagingItems<Beer> // communicate with lazy column/row
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = beers.loadState) {
        if (beers.loadState.refresh is LoadState.Error) {
            context.showToast(value = (beers.loadState.refresh as LoadState.Error).error.message)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (beers.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(beers.itemCount) { index ->
                    val item = beers[index] ?: return@items
                    BeerItem(beer = item, modifier = Modifier.fillMaxWidth())
                }
                item {
                    if(beers.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}