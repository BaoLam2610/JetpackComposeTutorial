package com.lambao.tutorial.data.mappers

import com.lambao.tutorial.data.remote.dto.MessageDto
import com.lambao.tutorial.domain.model.Message
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

fun MessageDto.toMessage() : Message {
    val date = Date(timestamp)
    val formattedDate = DateFormat
        .getDateInstance(DateFormat.DEFAULT)
        .format(date)
    return Message(
        text = text,
        formattedTime = formattedDate,
        username = username
    )
}