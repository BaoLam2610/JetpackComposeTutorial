package com.lambao.tutorial.data.mappers

import com.lambao.tutorial.data.local.BeerEntity
import com.lambao.tutorial.domain.Beer
import com.lambao.tutorial.data.remote.BeerDto


fun BeerDto.toBeerEntity()  : BeerEntity {
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}
fun BeerEntity.toBeer() : Beer {
    return Beer(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}