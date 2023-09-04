package com.lambao.tutorial

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.TaskStackBuilder
import com.lambao.tutorial.destinations.PostsScreenDestination
import com.lambao.tutorial.destinations.ProfileScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.parcelize.Parcelize


@Composable
fun NavigationDestination() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navigator.navigate(
                    ProfileScreenDestination(
                        User("Bao Lam", "Hai Duong")
                    )
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Go to Detail")
        }
    }
}

@Destination()
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator,
    user: User
) {
    Text(
        text = user.toString(),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .clickable {
                navigator.navigate(PostsScreenDestination("ok"))
            },
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
}

@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "https://baolam2610.com/{title}",
            action = Intent.ACTION_VIEW
        ),
        DeepLink(
            uriPattern = "https://baolam2610.com/things/{things}",
            action = Intent.ACTION_VIEW
        ),
        DeepLink(
            uriPattern = "https://baolam2610.com/waifu/{waifu}",
            action = Intent.ACTION_VIEW
        )
    ]
)
@Composable
fun PostsScreen(
    title: String = "",
    things: Things = Things(),
    waifu: ArrayList<Waifu> = arrayListOf() // url/array/[shinobu %252C makima]
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 30.sp,
        )

        Text(
            text = "Things: $things",
            fontSize = 30.sp,
            modifier = Modifier.padding(30.dp)
        )

        Text(
            text = "Waifu size: ${waifu.size}",
            fontSize = 30.sp,
            modifier = Modifier.padding(30.dp)
        )
        val context = LocalContext.current
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://baolam2610.com/HelloWorld"))
            val pendingIntent = TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(intent)
                getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }
            pendingIntent?.send()
        }) {
            Text(text = "Trigger Deeplink")
        }
    }
}

@Parcelize
data class User(
    val name: String,
    val address: String
) : Parcelable