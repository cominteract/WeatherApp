package com.ainsigne.network.base

data class DailyForecast(
    val feelsLike: FeelsLike,
    val temp: Temp
) : DefaultForecast()
