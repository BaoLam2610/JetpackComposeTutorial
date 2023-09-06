package com.lambao.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lambao.tutorial.ui.theme.Purple40

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                ProfileAvatarSection()
                Spacer(modifier = Modifier.height(10.dp))
                ProfileNameSection(
                    name = "Lam La Tui",
                    email = "baolam.nguyen2610@gmail.com",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                MenuSection(
                    items = listOf(
                        MenuItem(painterResource(id = R.drawable.ic_1), "Settings"),
                        MenuItem(painterResource(id = R.drawable.ic_2), "Information"),
                        MenuItem(painterResource(id = R.drawable.ic_3), "Personal"),
                        MenuItem(painterResource(id = R.drawable.ic_4), "Notification"),
                        MenuItem(painterResource(id = R.drawable.ic_5), "Fingerprint settings"),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                LogoutSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
        }
    }
}

@Composable
fun RoundedImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(3.dp, color = Color.White, shape = CircleShape)
            .padding(3.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProfileAvatarSection(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier) {
        val (topBgr, avatar) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.top_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(topBgr) {
                    top.linkTo(parent.top)
                },
        )

        RoundedImage(
            image = painterResource(id = R.drawable.shinobu_kochou),
            modifier = Modifier
                .size(150.dp)
                .constrainAs(avatar) {
                    top.linkTo(topBgr.top)
                    bottom.linkTo(topBgr.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    verticalBias = 0.6f
                }
        )
    }
}

@Composable
fun ProfileNameSection(
    name: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 30.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = email, fontSize = 18.sp, color = Color.Gray)
    }
}

@Composable
fun MenuSection(
    items: List<MenuItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        itemsIndexed(items) { _, item ->
            ButtonSection {
                Image(
                    painter = item.icon,
                    contentDescription = item.title,
                )
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 10.dp),
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun LogoutSection(
    modifier: Modifier = Modifier,
) {
    ButtonSection(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple40,
        ),
        shape = ButtonDefaults.shape
    ) {
        Text(
            text = "Logout",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color.White),
    shape: Shape = RoundedCornerShape(10.dp),
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
    contentPadding: PaddingValues = PaddingValues(20.dp),
    scope: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = colors,
        shape = shape,
        elevation = elevation,
        contentPadding = contentPadding
    ) {
        scope()
    }
}

data class MenuItem(
    val icon: Painter,
    val title: String
)