package com.ainsigne.weatherapp.ui.details

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
class DetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    private val mutableWeatherDetailsState: MutableSharedFlow<WeatherDetailsState> = MutableSharedFlow()

    val weatherDetailsState: SharedFlow<WeatherDetailsState> = mutableWeatherDetailsState

    private val refreshContent = MutableLiveData(true)

    fun watchDailyWeatherDetails(dt: Long) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                emitState {
                    mutableWeatherDetailsState.emit(WeatherDetailsState.HideLoader)
                    mutableWeatherDetailsState.emit(WeatherDetailsState.Error(error.localizedMessage))
                }
            }
        ) {
            combine(
                refreshContent.asFlow(),
                weatherRepository.watchDailyWeatherByDT(dt).distinctUntilChanged(),
            ) { _ , data ->
                data
            }.flatMapLatest { data ->
                flowOf(data)
            }.collect { data ->
                mutableWeatherDetailsState.emit(WeatherDetailsState.HideLoader)
                data?.also {
                    mutableWeatherDetailsState.emit(WeatherDetailsState.SuccessDailyWeatherDisplay(data))
                }
            }
        }
    }

    fun watchCurrentWeatherDetails(dt: Long) {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, error ->
                emitState {
                    mutableWeatherDetailsState.emit(WeatherDetailsState.HideLoader)
                    mutableWeatherDetailsState.emit(WeatherDetailsState.Error(error.localizedMessage))
                }
            }
        ) {
            combine(
                refreshContent.asFlow(),
                weatherRepository.watchCurrentForecastByDT(dt).distinctUntilChanged(),
            ) { _ , data ->
                data
            }.flatMapLatest { data ->
                flowOf(data)
            }.collect { data ->
                mutableWeatherDetailsState.emit(WeatherDetailsState.HideLoader)
                data?.also {
                    mutableWeatherDetailsState.emit(WeatherDetailsState.SuccessCurrentWeatherDisplay(data))
                }
            }
        }
    }
}