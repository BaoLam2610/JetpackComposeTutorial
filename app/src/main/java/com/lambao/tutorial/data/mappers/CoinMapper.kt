package com.lambao.tutorial.data.mappers

import com.lambao.tutorial.data.remote.dto.CoinDto
import com.lambao.tutorial.domain.model.Coin

fun CoinDto.toCoin() = Coin(
    id ?: "",
    isActive ?: false,
    name ?: "",
    rank ?: -1,
    symbol ?: ""
)