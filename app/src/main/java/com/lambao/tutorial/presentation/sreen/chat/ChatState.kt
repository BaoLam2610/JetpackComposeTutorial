package com.lambao.tutorial.presentation.sreen.chat

import com.lambao.tutorial.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
