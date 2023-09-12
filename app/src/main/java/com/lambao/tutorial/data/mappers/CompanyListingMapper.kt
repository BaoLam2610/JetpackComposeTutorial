package com.lambao.tutorial.data.mappers

import com.lambao.tutorial.data.local.CompanyListingEntity
import com.lambao.tutorial.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing() =
    CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )

fun CompanyListing.toCompanyListingEntity() =
    CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )