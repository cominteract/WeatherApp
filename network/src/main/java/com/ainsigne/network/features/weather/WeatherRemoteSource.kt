package com.ainsigne.network.features.weather

import com.ainsigne.network.features.weather.mapper.mapForecastToDommain
import com.ainsigne.domain.dispatcher.dispatcherIO
import com.ainsigne.domain.exception.apiSafeCall
import com.ainsigne.network.BuildConfig
import com.ainsigne.network.features.weather.mapper.mapForecastToDomainComplete
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class WeatherRemoteSource @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun refreshForecasts(lat: Double, lon: Double) = apiSafeCall {
        withContext(dispatcherIO) {
            val weatherForecast = weatherService.getFiveDayForecast(
                appid = BuildConfig.API_KEY,
                lat = lat,
                lon = lon
            )
            weatherForecast.mapForecastToDomainComplete(
                lat = lat,
                lon = lon
            )
        }
    }
}
