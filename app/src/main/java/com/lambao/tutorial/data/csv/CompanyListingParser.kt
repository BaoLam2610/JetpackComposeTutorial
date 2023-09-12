package com.lambao.tutorial.data.csv

import com.lambao.tutorial.domain.model.CompanyListing
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class CompanyListingParser @Inject constructor(

) : CSVParser<CompanyListing> {
    override suspend fun parse(stream: InputStream): List<CompanyListing> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(0)
                    val exchange = line.getOrNull(0)
                    CompanyListing(
                        name ?: return@mapNotNull null,
                        symbol ?: return@mapNotNull null,
                        exchange ?: return@mapNotNull null,
                    )
                }.also {
                    csvReader.close()
                }
        }
    }
}