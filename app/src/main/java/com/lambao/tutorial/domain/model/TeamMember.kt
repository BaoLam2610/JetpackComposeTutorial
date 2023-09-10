package com.lambao.tutorial.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamMember(
    val id: String,
    val name: String,
    val position: String,
) : Parcelable
