package com.nguyenhoangthanhan.stockmarketapp.data.mapper

import com.nguyenhoangthanhan.stockmarketapp.data.remote.dto.IntradayInfoDto
import com.nguyenhoangthanhan.stockmarketapp.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
    val pattern = "yyyy-MM-dd HH:mm:ss"
    var localDateTime: LocalDateTime? = null
    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    localDateTime = LocalDateTime.parse(timeStamp, formatter)

    return IntradayInfo(
        date = localDateTime,
        close = close
    )
}