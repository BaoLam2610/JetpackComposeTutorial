package com.lambao.tutorial.data.mappers

import com.lambao.tutorial.data.remote.dto.CoinDetailDto
import com.lambao.tutorial.data.remote.dto.TeamMemberDto
import com.lambao.tutorial.domain.model.CoinDetail
import com.lambao.tutorial.domain.model.TeamMember

fun CoinDetailDto.toCoinDetail() = CoinDetail(
    id ?: "",
    name ?: "",
    description ?: "",
    symbol ?: "",
    rank ?: -1,
    isActive ?: false,
    tags?.map { it.toString() } ?: listOf(),
    team?.map { it.toTeamMember() } ?: listOf()
)

fun TeamMemberDto.toTeamMember() = TeamMember(
    id ?: "",
    name ?: "",
    position ?: ""
)