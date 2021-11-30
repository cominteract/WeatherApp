package com.ainsigne.local.module

import com.ainsigne.local.features.weather.WeatherLocalSource
import com.ainsigne.local.roomdao.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModule {


    @Provides
    fun provideWeatherLocalSource(weatherDao: WeatherDao) =
        WeatherLocalSource(weatherDao = weatherDao)

}
