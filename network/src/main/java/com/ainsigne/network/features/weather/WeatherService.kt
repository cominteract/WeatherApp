package com.ainsigne.network.features.weather

import com.ainsigne.domain.extension.EMPTY
import com.ainsigne.network.base.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("onecall")
    suspend fun getFiveDayForecast(
        @Query("appid") appid: String? = EMPTY,
        @Query("exclude") exclude: String = "hourly,minutely",
        @Query("lat") lat: Double = 0.0,
        @Query("lon") lon: Double = 0.0
    ): ForecastResponse
}
