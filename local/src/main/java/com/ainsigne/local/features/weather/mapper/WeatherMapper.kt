package com.ainsigne.local.features.weather.mapper

import android.util.Log
import com.ainsigne.local.features.weather.CurrentWeather
import com.ainsigne.local.features.weather.DailyWeather

fun List<com.ainsigne.domain.features.DailyWeather>.mapDomainDailyWeatherToLocal(
    lat: Double,
    lon: Double
): List<DailyWeather> {
    return this.map {
        DailyWeather(
            dt = it.dt,
            name = it.name,
            icon = it.icon,
            description = it.description,
            dayTemp = it.dayTemp,
            date = it.date,
            displayDate = it.displayDate,
            nightTemp = it.nightTemp,
            lat = lat,
            lon = lon,
            timezone = it.timezone,
            humidity = it.humidity,
            pressure = it.pressure,
            clouds = it.clouds
        )
    }
}

fun com.ainsigne.domain.features.CurrentWeather.mapDomainCurrentWeatherToLocal(
    lat: Double,
    lon: Double
): CurrentWeather {

    return CurrentWeather(
        dt = this.dt,
        name = this.name,
        icon = this.icon,
        description = this.description,
        sunrise = this.sunrise,
        sunset = this.sunset,
        date = this.date,
        displayDate = this.displayDate,
        temp = this.temp,
        lat = lat,
        lon = lon,
        clouds = this.clouds,
        humidity = this.humidity,
        pressure = this.pressure,
        timezone = this.timezone
    )
}

fun List<DailyWeather>.mapLocalDailyWeatherToDomain(
    lat: Double,
    lon: Double
): List<com.ainsigne.domain.features.DailyWeather> {
    return this.map {
        com.ainsigne.domain.features.DailyWeather(
            dt = it.dt,
            name = it.name,
            icon = it.icon,
            description = it.description,
            dayTemp = it.dayTemp,
            date = it.date,
            displayDate = it.displayDate,
            nightTemp = it.nightTemp,
            lat = lat,
            lon = lon,
            clouds = it.clouds,
            humidity = it.humidity,
            pressure = it.pressure,
            timezone = it.timezone
        )
    }
}

fun DailyWeather.mapLocalDailytWeatherToDomain(): com.ainsigne.domain.features.DailyWeather {
    return com.ainsigne.domain.features.DailyWeather(
        dt = this.dt,
        name = this.name,
        icon = this.icon,
        description = this.description,
        nightTemp = this.nightTemp,
        date = this.date,
        displayDate = this.displayDate,
        dayTemp = this.dayTemp,
        lat = lat,
        lon = lon,
        clouds = this.clouds,
        humidity = this.humidity,
        pressure = this.pressure,
        timezone = this.timezone
    )
}

fun CurrentWeather.mapLocalCurrentWeatherToDomain(
    lat: Double,
    lon: Double
): com.ainsigne.domain.features.CurrentWeather {
    return com.ainsigne.domain.features.CurrentWeather(
        dt = this.dt,
        name = this.name,
        icon = this.icon,
        description = this.description,
        sunrise = this.sunrise,
        sunset = this.sunset,
        date = this.date,
        displayDate = this.displayDate,
        temp = this.temp,
        lat = lat,
        lon = lon,
        clouds = this.clouds,
        humidity = this.humidity,
        pressure = this.pressure,
        timezone = this.timezone
    )
}