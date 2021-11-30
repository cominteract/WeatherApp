package com.ainsigne.data.features.weather

import android.util.Log
import com.ainsigne.domain.features.CurrentWeather
import com.ainsigne.domain.features.DailyWeather
import com.ainsigne.local.features.weather.WeatherLocalSource
import com.ainsigne.local.features.weather.mapper.mapDomainCurrentWeatherToLocal
import com.ainsigne.local.features.weather.mapper.mapDomainDailyWeatherToLocal
import com.ainsigne.local.features.weather.mapper.mapLocalCurrentWeatherToDomain
import com.ainsigne.local.features.weather.mapper.mapLocalDailyWeatherToDomain
import com.ainsigne.local.features.weather.mapper.mapLocalDailytWeatherToDomain
import com.ainsigne.network.features.weather.WeatherRemoteSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
@DelicateCoroutinesApi
class WeatherRepositoryImpl
@Inject constructor(

    private val weatherLocalSource: WeatherLocalSource,
    private val weatherRemoteSource: WeatherRemoteSource

) : WeatherRepository {
    override suspend fun refreshForecasts(lat: Double, lon: Double) {
        val remoteForecasts = weatherRemoteSource.refreshForecasts(lat, lon)
        weatherLocalSource.insertDailyWeathers(remoteForecasts.dailyWeathers.mapDomainDailyWeatherToLocal(
            lat = lat,
            lon = lon
        ))
        val it = remoteForecasts.currentWeather.mapDomainCurrentWeatherToLocal(
            lat = lat,
            lon = lon
        )
        Log.d(" Current Weather "," Current Weather Here ${it.clouds} ${it.humidity} ${it.pressure}")
        weatherLocalSource.insertCurrentWeather(it)
    }

    override suspend fun watchForecasts(lat: Double, lon: Double): Flow<List<DailyWeather>> {
        return weatherLocalSource.watchWeatherWithinDates(lat, lon).flatMapLatest {
            flowOf(it.mapLocalDailyWeatherToDomain(lat, lon))
        }
    }

    override suspend fun watchCurrentForecast(lat: Double, lon: Double): Flow<CurrentWeather> {
        return weatherLocalSource.watchCurrentWeather(lat, lon).flatMapLatest {
            Log.d(" Current Weather "," Current Weather Here x ${it.clouds} ${it.humidity} ${it.pressure}")
            flowOf(it.mapLocalCurrentWeatherToDomain(lat, lon))
        }
    }

    override suspend fun watchCurrentForecastByDT(dt: Long): Flow<CurrentWeather?> {
        return weatherLocalSource.watchCurrentWeather(dt).flatMapLatest {
            Log.d(" Current Weather "," Current Weather Here y ${it?.clouds} ${it?.humidity} ${it?.pressure}")
            flowOf(it?.mapLocalCurrentWeatherToDomain(it.lat ?: 0.0, it.lon ?: 0.0))
        }
    }

    override suspend fun watchDailyWeatherByDT(dt: Long): Flow<DailyWeather?> {
        return weatherLocalSource.watchWeather(dt).flatMapLatest {
            flowOf(it?.mapLocalDailytWeatherToDomain())
        }
    }

}
