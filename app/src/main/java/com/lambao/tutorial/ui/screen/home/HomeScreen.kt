package com.lambao.tutorial.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lambao.tutorial.R
import com.lambao.tutorial.ui.custom.circleLayout
import com.lambao.tutorial.ui.theme.Background
import com.lambao.tutorial.ui.theme.LightRed
import com.lambao.tutorial.ui.theme.OrangeRed
import com.lambao.tutorial.ui.theme.OrangeYellow
import com.lambao.tutorial.ui.theme.PlaceHolder
import com.lambao.tutorial.ui.theme.iconSize
import com.lambao.tutorial.ui.theme.spacing
import com.lambao.tutorial.ui.theme.textStyle
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen() {
    val scrollState = rememberScrollState()
    var searchText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.extraMedium)
            .verticalScroll(scrollState)
    ) {


        GreetingSection(
            name = "Bao Lam",
            badgeCount = "9+"
        )


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraNormal))
        SearchSection(value = searchText) {
            searchText = it
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraNormal))
        BannerSection()



        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraNormal))
        CategoriesSection(
            categories = listOf(
                Category("PC", R.drawable.cat1),
                Category("Phone", R.drawable.cat2),
                Category("Headphone", R.drawable.cat3),
                Category("Gaming", R.drawable.cat4),
                Category("View all", R.drawable.cat5),
            )
        )


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraNormal))
        PopularProductSection(
            products = listOf(
                Product("PC 123123132", R.drawable.pic1, "123123123123", 23, 3f),
                Product("PC 123123132", R.drawable.pic2, "123123123123", 23, 3f),
                Product("PC 123123132", R.drawable.pic3, "123123123123", 23, 3f),
            )
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraNormal))
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraNormal))


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraNormal))
        PopularProductSection(
            products = listOf(
                Product("PC 123123132", R.drawable.pic1, "123123123123", 23, 3f),
                Product("PC 123123132", R.drawable.pic2, "123123123123", 23, 3f),
                Product("PC 123123132", R.drawable.pic3, "123123123123", 23, 3f),
            )
        )

    }
}

@Composable
fun GreetingSection(
    name: String,
    badgeCount: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.extraNormal),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = "Welcome", style = MaterialTheme.textStyle.regular20)
            Text(text = name, style = MaterialTheme.textStyle.bold20)
        }

        ConstraintLayout {
            val (icon, badge) = createRefs()
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .size(MaterialTheme.iconSize.large)
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )
            Text(
                text = badgeCount,
                style = MaterialTheme.textStyle.regular10,
                color = Color.White,
                modifier = Modifier
                    .background(OrangeRed, CircleShape)
                    .circleLayout()
                    .padding(MaterialTheme.spacing.extraTiny)
                    .constrainAs(badge) {
                        top.linkTo(parent.top)
                        end.linkTo(icon.end)
                    }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(MaterialTheme.spacing.extraNormal))
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Background,
                placeholderColor = PlaceHolder,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = ""
                )
            },
            textStyle = MaterialTheme.textStyle.regular18,
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search",
                    style = MaterialTheme.textStyle.regular18,
                    color = Color.Gray
                )
            }
        )
    }
}

@Composable
fun BannerSection(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (banner, buttonBuy) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(MaterialTheme.spacing.extraNormal))
                .background(LightRed)
                .constrainAs(banner) {
                    top.linkTo(parent.top)
                }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Discount 20% For Headphones!",
                    style = MaterialTheme.textStyle.bold20,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .weight(0.6f)
                        .padding(start = MaterialTheme.spacing.normal)
                )
                Image(
                    painter = painterResource(id = R.drawable.girl),
                    contentDescription = null,
                    alignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.4f)
                )
            }
        }

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = OrangeYellow
            ),
            modifier = Modifier.constrainAs(buttonBuy) {
                top.linkTo(banner.bottom)
                bottom.linkTo(banner.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            contentPadding = PaddingValues(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.extraSmall
            )
        ) {
            Text(text = "Buy now", style = MaterialTheme.textStyle.bold12, color = Color.White)
        }
    }
}

@Composable
fun CategoriesSection(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(text = "Explore popular categories", style = MaterialTheme.textStyle.bold18)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { item ->
                CategoryItem(item = item, Modifier.padding(end = MaterialTheme.spacing.extraNormal))
            }
        }
    }
}

@Composable
fun PopularProductSection(
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(text = "Explore popular categories", style = MaterialTheme.textStyle.bold18)
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.normal))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(products) { item ->
                ProductItem(product = item, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}