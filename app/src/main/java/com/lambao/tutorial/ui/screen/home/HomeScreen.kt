package com.lambao.tutorial.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lambao.tutorial.ui.custom.circleLayout
import com.lambao.tutorial.ui.theme.OrangeRed
import com.lambao.tutorial.ui.theme.iconSize
import com.lambao.tutorial.ui.theme.textStyle
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun HomeScreen() {
    Column {
        GreetingSection(
            name = "Bao Lam",
            badgeCount = "9+",
            modifier = Modifier.padding(horizontal = 20.dp)
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
        modifier = modifier.fillMaxWidth().padding(top = 20.dp),
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
                    .size(MaterialTheme.iconSize.extraLarge)
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
                    .padding(top = 10.dp)
                    .background(OrangeRed, CircleShape)
                    .circleLayout()
                    .padding(3.dp)
                    .constrainAs(badge) {
                        top.linkTo(parent.top)
                        bottom.linkTo(icon.top)
                        end.linkTo(icon.end)
                    }
            )
        }
    }
}