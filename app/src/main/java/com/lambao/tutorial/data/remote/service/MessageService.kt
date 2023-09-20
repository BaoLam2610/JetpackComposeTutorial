package com.lambao.tutorial.data.remote.service

import com.lambao.tutorial.common.Constants.BASE_HTTP_URL
import com.lambao.tutorial.domain.model.Message

interface MessageService {

    suspend fun getAllMessages() : List<Message>

    sealed class Endpoints(val url: String) {
        object GetAllMessages: Endpoints("${BASE_HTTP_URL}/messages")
    }
}