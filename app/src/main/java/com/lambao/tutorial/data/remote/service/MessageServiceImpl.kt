package com.lambao.tutorial.data.remote.service

import com.lambao.tutorial.data.mappers.toMessage
import com.lambao.tutorial.data.remote.dto.MessageDto
import com.lambao.tutorial.domain.model.Message
import io.ktor.client.*
import io.ktor.client.request.*

class MessageServiceImpl(
    private val client: HttpClient
) : MessageService {
    override suspend fun getAllMessages(): List<Message> {
        return try {
            client.get<List<MessageDto>>(MessageService.Endpoints.GetAllMessages.url)
                .map { it.toMessage() }
        } catch (e: Exception) {
            emptyList<Message>()
        }
    }
}