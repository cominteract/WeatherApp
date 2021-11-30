package com.ainsigne.network.features.weather.mapper

import com.ainsigne.domain.extension.EMPTY
import com.ainsigne.domain.features.CompleteWeather
import com.ainsigne.domain.features.CurrentWeather
import com.ainsigne.domain.features.DailyWeather
import com.ainsigne.network.base.ForecastResponse
import com.ainsigne.utilities.formatToDisplay
import com.ainsigne.utilities.fullFormatToDisplay
import com.ainsigne.utilities.toDate
import java.util.*

fun ForecastResponse.mapForecastToDommain(
    lat: Double,
    lon: Double
): List<DailyWeather> {

    return this.daily.map {
        val weather = it.weather.firstOrNull()
        DailyWeather(
            dt = it.dt,
            timezone = this.timezone,
            name = weather?.main ?: EMPTY,
            icon = weather?.icon ?: EMPTY,
            description = weather?.description ?: EMPTY,
            lat = lat,
            lon = lon,
            clouds = it.clouds.toDouble(),
            humidity = it.humidity.toDouble(),
            pressure = it.pressure.toDouble(),
            date = it.dt.toDate(),
            displayDate = it.dt.toDate().formatToDisplay(),
            dayTemp = it.temp.day,
            nightTemp = it.temp.night
        )
    }
}

fun ForecastResponse.mapForecastToDomainComplete(
    lat: Double,
    lon: Double
): CompleteWeather {
    val dailyWeathers = this.daily.map {
        val weather = it.weather.firstOrNull()
        DailyWeather(
            dt = it.dt,
            timezone = this.timezone,
            name = weather?.main ?: EMPTY,
            icon = weather?.icon ?: EMPTY,
            description = weather?.description ?: EMPTY,
            lat = lat,
            lon = lon,
            clouds = it.clouds.toDouble(),
            humidity = it.humidity.toDouble(),
            pressure = it.pressure.toDouble(),
            date = it.dt.toDate(),
            displayDate = it.dt.toDate().formatToDisplay(),
            dayTemp = it.temp.day,
            nightTemp = it.temp.night
        )
    }
    val weather = this.current.weather.firstOrNull()
    return CompleteWeather(
        CurrentWeather(
            dt = this.current.dt,
            timezone = this.timezone,
            name = weather?.main ?: EMPTY,
            description = weather?.description ?: EMPTY,
            lat = lat,
            lon = lon,
            clouds = this.current.clouds.toDouble(),
            humidity = this.current.humidity.toDouble(),
            pressure = this.current.pressure.toDouble(),
            icon = weather?.icon ?: EMPTY,
            temp = this.current.temp,
            displayDate = this.current.dt.toDate().fullFormatToDisplay(),
            date = this.current.dt.toDate(),
            sunrise = this.current.sunrise.toDate(),
            sunset = this.current.sunset.toDate()
        ),
        dailyWeathers
    )
}