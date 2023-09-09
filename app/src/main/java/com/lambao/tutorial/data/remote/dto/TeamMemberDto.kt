package com.lambao.tutorial.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TeamMemberDto(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("position") val position: String?
)