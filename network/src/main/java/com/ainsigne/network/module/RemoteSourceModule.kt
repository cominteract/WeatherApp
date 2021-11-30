package com.ainsigne.network.module


import com.ainsigne.network.features.weather.WeatherRemoteSource
import com.ainsigne.network.features.weather.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteSourceModule {


    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideWeatherRemoteSource(weatherService: WeatherService) =
        WeatherRemoteSource(weatherService)

}
