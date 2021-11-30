package com.ainsigne.network.base

open class DefaultForecast(
    val dt: Long = 0,
    val sunrise: Long = 0,
    val sunset: Long = 0,
    val moonrise: Long = 0,
    val moonset: Long = 0,
    val moon_phase: Double = 0.0,
    val pressure: Int = 0,
    val humidity: Int = 0,
    val dew_point: Double = 0.0,
    val uvi: Double = 0.0,
    val clouds: Int = 0,
    val pop: Double = 0.0,
    val snow: Double = 0.0,
    val visibility: Int = 0,
    val wind_speed: Double = 0.0,
    val wind_deg: Int = 0,
    val wind_gust: Double = 0.0,
    val weather: List<Weather> = emptyList(),
)
