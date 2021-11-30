package com.ainsigne.data.module

import com.ainsigne.data.features.weather.WeatherRepository
import com.ainsigne.data.features.weather.WeatherRepositoryImpl
import com.ainsigne.local.features.weather.WeatherLocalSource
import com.ainsigne.network.features.weather.WeatherRemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi

@OptIn(DelicateCoroutinesApi::class)
@ExperimentalSerializationApi
@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @DelicateCoroutinesApi
    @Provides
    @ViewModelScoped
    fun provideWeatherRepository(
        weatherLocalSource: WeatherLocalSource,
        weatherRemoteSource: WeatherRemoteSource
    ): WeatherRepository = WeatherRepositoryImpl(
        weatherLocalSource,
        weatherRemoteSource
    )
}
