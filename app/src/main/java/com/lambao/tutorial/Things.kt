package com.lambao.tutorial

import android.os.Parcelable
import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer
import kotlinx.parcelize.Parcelize

@Parcelize
data class Things(
    val thingOne: String = "",
    val thingTwo: String = ""
) : Parcelable

@NavTypeSerializer
class ThingsNavTypeSerializer : DestinationsNavTypeSerializer<Things> {

    override fun toRouteString(value: Things): String {
        return "${value.thingOne};${value.thingTwo}"
    }

    override fun fromRouteString(routeStr: String): Things {
        val things = routeStr.split(";")
        return Things(things[0], things[1])
    }
}