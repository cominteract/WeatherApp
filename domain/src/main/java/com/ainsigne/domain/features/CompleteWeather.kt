package com.ainsigne.domain.features

data class CompleteWeather(
    val currentWeather: CurrentWeather,
    val dailyWeathers: List<DailyWeather>
)
