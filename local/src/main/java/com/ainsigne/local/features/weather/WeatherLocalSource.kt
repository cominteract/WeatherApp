package com.ainsigne.local.features.weather

import com.ainsigne.local.roomdao.WeatherDao
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class WeatherLocalSource @Inject constructor(
    private val weatherDao: WeatherDao
) {
    fun watchWeather(dt: Long): Flow<DailyWeather?> {
        return weatherDao.watchWeather(dt = dt)
    }

    fun watchCurrentWeather(dt: Long): Flow<CurrentWeather?> {
        return weatherDao.watchCurrentWeather(dt = dt)
    }

    fun watchWeatherWithinDates(lat: Double, lon: Double): Flow<List<DailyWeather>> {
        val c = Calendar.getInstance()
        c.time = Date()
        c.add(Calendar.DATE, 1)
        val startDate = c.time
        c.add(Calendar.DATE, 10)
        val endDate = c.time
        return weatherDao.watchWeathers(
            startDate = startDate,
            endDate = endDate,
            lat = lat,
            lon = lon
        )
    }

    fun watchCurrentWeather(lat: Double, lon: Double): Flow<CurrentWeather> {
        return weatherDao.watchCurrentForecast(
            lat = lat,
            lon = lon
        )
    }

    suspend fun insertDailyWeathers(dailyWeathers: List<DailyWeather>) {
        weatherDao.insertWeathers(dailyWeathers)
    }

    suspend fun insertCurrentWeather(currentWeather: CurrentWeather) {
        weatherDao.insertCurrentForecast(currentWeather)
    }

}
