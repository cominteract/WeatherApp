package com.ainsigne.data.features.weather

import com.ainsigne.domain.features.CurrentWeather
import com.ainsigne.domain.features.DailyWeather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun refreshForecasts(lat: Double, lon: Double)

    suspend fun watchForecasts(lat: Double, lon: Double): Flow<List<DailyWeather>>

    suspend fun watchCurrentForecast(lat: Double, lon: Double): Flow<CurrentWeather>

    suspend fun watchCurrentForecastByDT(dt: Long): Flow<CurrentWeather?>

    suspend fun watchDailyWeatherByDT(dt: Long): Flow<DailyWeather?>
}
