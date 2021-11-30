package com.ainsigne.network.module

import com.ainsigne.network.features.weather.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun provideWeatherService(retrofit: Retrofit) = retrofit.create(WeatherService::class.java)
}
