package com.lambao.tutorial.common

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


internal class IdProvider {
    private var previous = AtomicLong(-1L)

    fun next() = previous.addAndGet(1)
}

class JobRegistry {
    private val idProvider = IdProvider()
    private val map = ConcurrentHashMap<Long, Job>()

    fun addJob(job: Job) : Long {
        val id = idProvider.next()
        map[id] = job
        return id
    }

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
        Log.d("lamnb", "$map")
        map.clear()
        Log.d("lamnb", "$map")
    }
}