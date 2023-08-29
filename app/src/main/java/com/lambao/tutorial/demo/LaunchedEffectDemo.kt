package com.lambao.tutorial.demo

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lambao.tutorial.TAG


@Composable
fun LaunchedEffectDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var count by remember {
            mutableStateOf(0)
        }

        /*
        * Chỗ nào gọi count thì sẽ call lại toàn bộ hàm Composable
        * log này sẽ đc gọi khi count đc gọi ở đâu đó
        * đéo gọi launched effect là ăn đầu buồi ăn cứt
        * */
//        Log.d(TAG, "Init demo: Call Api")

        /*
        * Cách để k bị gọi
        * CHUẨN
        * */
        LaunchedEffect(key1 = "call api") {
            Log.d(TAG, "Init demo: Call Api")
        }

        Text(
            text = count.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )

        Button(
            onClick = {
                count++
                Log.d(TAG, "Current count: $count")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Add", modifier = Modifier.padding(8.dp))
        }

        Button(
            onClick = {
                count -= 1
                Log.d(TAG, "Post count to server: $count")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Call Api", modifier = Modifier.padding(8.dp))
        }
    }
}
