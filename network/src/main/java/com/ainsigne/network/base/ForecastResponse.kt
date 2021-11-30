package com.ainsigne.network.base

import com.ainsigne.domain.extension.EMPTY

data class ForecastResponse(
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val timezone: String = EMPTY,
    val current: CurrentForecast,
    val daily: List<DailyForecast> = emptyList()
)
