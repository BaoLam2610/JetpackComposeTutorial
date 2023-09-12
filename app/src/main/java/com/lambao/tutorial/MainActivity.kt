package com.lambao.tutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }

        val jobRegistry = JobRegistry()
        val id1 = jobRegistry.launch(lifecycleScope) {
            delay(1000)
            Log.d("lamnb", "Job 1 done")
//            jobRegistry.cancelAll()
        }
        val id2 = jobRegistry.launch(lifecycleScope) {
            delay(2000)
            Log.d("lamnb", "Job 2 done")
        }
        val id3 = jobRegistry.launch(lifecycleScope) {
            delay(3000)
            Log.d("lamnb", "Job 3 done")
        }
        Log.d("lamnb", "Job 1 id: $id1")
        Log.d("lamnb", "Job 2 id: $id2")
        Log.d("lamnb", "Job 3 id: $id3")
    }
}

internal class IdProvider {
    private var previous = AtomicLong(-1L)

    fun next() = previous.addAndGet(1)
}

class JobRegistry {
    private val idProvider = IdProvider()
    private val map = ConcurrentHashMap<Long, Job>()

    fun launch(
        scope: CoroutineScope,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Long {
        val id = idProvider.next()
        map[id] = scope.launch(context, start, block)
            .also { it.invokeOnCompletion { map.remove(id) } }
        return id
    }

    fun cancel(jobId: Long) {
        map[jobId]?.cancel()
    }

    fun cancelAll() {
        map.forEach { (_, job) ->
            job.cancel()
        }
        map.clear()
    }
}