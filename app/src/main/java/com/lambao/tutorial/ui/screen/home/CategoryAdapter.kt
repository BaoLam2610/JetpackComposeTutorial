package com.lambao.tutorial.ui.screen.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lambao.tutorial.ui.theme.PlaceHolder
import com.lambao.tutorial.ui.theme.spacing
import com.lambao.tutorial.ui.theme.textStyle

@Composable
fun CategoryItem(
    item: Category,
    modifier: Modifier = Modifier
) {
    val size = 54.dp
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .border(1.dp, color = PlaceHolder, shape = CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = item.title,
            style = MaterialTheme.textStyle.regular12,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = MaterialTheme.spacing.small)
        )
    }
}

data class Category(
    val title: String,
    @DrawableRes val icon: Int
)