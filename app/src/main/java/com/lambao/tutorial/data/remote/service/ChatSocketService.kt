package com.lambao.tutorial.data.remote.service

import com.lambao.tutorial.common.Constants
import com.lambao.tutorial.common.Resource
import com.lambao.tutorial.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        username: String
    ): Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    sealed class Endpoints(val url: String) {
        object ChatSocket: Endpoints("${Constants.BASE_WS_URL}/chat-socket")
    }
}