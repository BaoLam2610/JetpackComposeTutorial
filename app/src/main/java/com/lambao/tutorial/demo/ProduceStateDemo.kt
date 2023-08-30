package com.lambao.tutorial.demo

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import com.lambao.tutorial.TAG
import kotlinx.coroutines.delay

/* DEMO 1 */
@Composable
fun ProduceStateDemo() {
    val number = produceNumber(countUpTo = 20)
    Text(text = number.value.toString())
}

@Composable
fun produceNumber(countUpTo: Int): State<Int> {
    return produceState(initialValue = 0){
        while (value < countUpTo) {
            delay(1000)
            value++
        }
    }
}

/*
@Composable
fun loadNetworkImage(
    url: String,
    imageRepository: ImageRepository = ImageRepository()
): State<Result<Image>> {

    // Creates a State<T> with Result.Loading as initial value
    // If either `url` or `imageRepository` changes, the running producer
    // will cancel and will be re-launched with the new inputs.
    return produceState<Result<Image>>(initialValue = Result.Loading, url, imageRepository) {

        // In a coroutine, can make suspend calls
        val image = imageRepository.load(url)

        // Update State with either an Error or Success result.
        // This will trigger a recomposition where this State is read
        value = if (image == null) {
            Result.Error
        } else {
            Result.Success(image)
        }
    }
}*/
