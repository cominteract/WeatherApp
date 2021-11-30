package com.ainsigne.local.roomdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.ainsigne.local.features.weather.CurrentWeather
import com.ainsigne.local.features.weather.DailyWeather
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface WeatherDao {

    @Query("SELECT * from weather_table WHERE dt == :dt")
    fun watchWeather(dt: Long): Flow<DailyWeather?>

    @Query("SELECT * from current_forecast_table WHERE dt == :dt")
    fun watchCurrentWeather(dt: Long): Flow<CurrentWeather?>

    @TypeConverters(DateConverter::class)
    @Query("SELECT * from weather_table WHERE date >= :startDate AND date <= :endDate AND lat = :lat AND lon = :lon")
    fun watchWeathers(startDate: Date, endDate: Date, lat: Double, lon: Double): Flow<List<DailyWeather>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeathers(weathers: List<DailyWeather>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentForecast(currentWeather: CurrentWeather)

    @TypeConverters(DateConverter::class)
    @Query("SELECT * from current_forecast_table WHERE lat = :lat AND lon = :lon")
    fun watchCurrentForecast(lat: Double, lon: Double): Flow<CurrentWeather>
}
