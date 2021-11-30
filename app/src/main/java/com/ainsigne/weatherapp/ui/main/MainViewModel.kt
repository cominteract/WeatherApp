package com.ainsigne.weatherapp.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.ainsigne.data.features.weather.WeatherRepository
import com.ainsigne.weatherapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    private val mutableDailyWeatherState: MutableSharedFlow<DailyWeatherState> = MutableSharedFlow()

    val dailyWeatherState: SharedFlow<DailyWeatherState> = mutableDailyWeatherState

    private val refreshContent = MutableLiveData(true)

    init {

        viewModelScope.launch {
            weatherRepository.refreshForecasts(14.402940, 120.989517)
        }
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                emitState {
                    mutableDailyWeatherState.emit(DailyWeatherState.HideLoader)
                    mutableDailyWeatherState.emit(DailyWeatherState.Error(error.localizedMessage))
                }
            }
        ) {
            combine(
                refreshContent.asFlow(),
                weatherRepository.watchForecasts(14.402940, 120.989517).distinctUntilChanged(),
                weatherRepository.watchCurrentForecast(14.402940, 120.989517).distinctUntilChanged()
            ) { _, data , current->
                current to data
            }.flatMapLatest { (current, data) ->
                flowOf(current to data)
            }.collect { (current, data) ->
                mutableDailyWeatherState.emit(DailyWeatherState.HideLoader)
                mutableDailyWeatherState.emit(DailyWeatherState.SuccessDailyWeatherDisplay(data))
                mutableDailyWeatherState.emit(DailyWeatherState.SuccessCurrentWeatherDisplay(current))
            }
        }
    }

    fun refreshContent() {
        refreshContent.postValue(true)
    }
}