package com.lambao.tutorial.ui.screen.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lambao.tutorial.R
import com.lambao.tutorial.ui.theme.OrangeRed
import com.lambao.tutorial.ui.theme.textStyle

data class Product(
    val name: String,
    @DrawableRes val image: Int,
    val price: String,
    val commentCount: Int,
    val rate: Float
)

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(end = 10.dp, start = 3.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        val imageSize = 180.dp
        Box(
            modifier = Modifier
                .size(imageSize)
                .background(Color.LightGray),

            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center),
                contentScale = ContentScale.Inside
            )
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.TopStart)
                    .background(Color.White, RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                    .padding(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.rate.toString(),
                    style = MaterialTheme.textStyle.regular12,
                    color = OrangeRed,
                    modifier = Modifier.padding(end = 2.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = product.name,
            style = MaterialTheme.textStyle.bold14,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .width(imageSize)
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = product.price,
                style = MaterialTheme.textStyle.regular14,
                modifier = Modifier.padding(end = 10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = product.commentCount.toString(),
                style = MaterialTheme.textStyle.regular14,
                modifier = Modifier.padding(end = 4.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.review),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}