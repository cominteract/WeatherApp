package com.ainsigne.weatherapp.ui.details

import com.ainsigne.domain.extension.EMPTY
import com.ainsigne.domain.features.CurrentWeather
import com.ainsigne.domain.features.DailyWeather
import com.ainsigne.weatherapp.ui.main.DailyWeatherState

sealed class WeatherDetailsState {
    object ShowLoader : WeatherDetailsState()
    object HideLoader : WeatherDetailsState()
    data class Error(val error: String = EMPTY) : WeatherDetailsState()
    data class SuccessDailyWeatherDisplay(val dailyWeather: DailyWeather) : WeatherDetailsState()
    data class SuccessCurrentWeatherDisplay(val currentWeather: CurrentWeather) : WeatherDetailsState()
}