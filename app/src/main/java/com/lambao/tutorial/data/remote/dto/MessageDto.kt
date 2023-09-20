package com.lambao.tutorial.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: String,
    val text: String,
    val timestamp: Long,
    val username: String,
)
