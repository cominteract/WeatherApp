package com.ainsigne.local.features.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ainsigne.local.WEATHER_DB
import com.ainsigne.local.features.weather.CurrentWeather
import com.ainsigne.local.features.weather.DailyWeather
import com.ainsigne.local.roomdao.WeatherDao

@Database(
    entities = [
        DailyWeather::class,
        CurrentWeather::class
    ],
    version = 1,
    exportSchema = false
)

abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        fun getInstance(context: Context): WeatherDatabase = buildDatabase(context)

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                WeatherDatabase::class.java,
                WEATHER_DB
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
