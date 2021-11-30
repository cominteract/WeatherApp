package com.ainsigne.weatherapp.ui.main

import com.ainsigne.domain.extension.EMPTY
import com.ainsigne.domain.features.CurrentWeather
import com.ainsigne.domain.features.DailyWeather

sealed class DailyWeatherState {
    object ShowLoader : DailyWeatherState()
    object HideLoader : DailyWeatherState()
    data class Error(val error: String = EMPTY) : DailyWeatherState()
    data class SuccessDailyWeatherDisplay(val dailyWeathers: List<DailyWeather>) : DailyWeatherState()
    data class SuccessCurrentWeatherDisplay(val currentWeather: CurrentWeather) : DailyWeatherState()
}