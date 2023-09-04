package com.lambao.tutorial

import com.ramcosta.composedestinations.navargs.DestinationsNavTypeSerializer
import com.ramcosta.composedestinations.navargs.NavTypeSerializer

data class Waifu(
    val name: String
)

@NavTypeSerializer
class WaifuNavTypeSerializer : DestinationsNavTypeSerializer<Waifu> {
    override fun fromRouteString(routeStr: String): Waifu {
        return Waifu(routeStr)
    }

    override fun toRouteString(value: Waifu): String {
        return value.name
    }
}