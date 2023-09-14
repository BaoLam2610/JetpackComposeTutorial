package com.lambao.tutorial.data.csv

import com.lambao.tutorial.data.mappers.toIntradayInfo
import com.lambao.tutorial.data.remote.dto.IntradayInfoDto
import com.lambao.tutorial.domain.model.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject

class IntradayInfoParser @Inject constructor(

) : CSVParser<IntradayInfo> {
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0)
                    val close = line.getOrNull(4)
                    IntradayInfoDto(
                        timestamp ?: return@mapNotNull null,
                        close?.toDoubleOrNull() ?: return@mapNotNull null,
                    ).toIntradayInfo()
                }.filter {
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth
                }.sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}