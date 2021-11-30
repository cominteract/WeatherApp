package com.ainsigne.local.features.weather

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ainsigne.local.roomdao.DateConverter
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "current_forecast_table")
@TypeConverters(DateConverter::class)
data class CurrentWeather(
    @PrimaryKey val dt: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "timezone") val timezone: String,
    @ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lon") val lon: Double = 0.0,
    @ColumnInfo(name = "clouds") val clouds: Double,
    @ColumnInfo(name = "humidity") val humidity: Double,
    @ColumnInfo(name = "pressure") val pressure: Double,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "display_date") val displayDate: String,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "sunrise") val sunrise: Date,
    @ColumnInfo(name = "sunset") val sunset: Date,
    @ColumnInfo(name = "temp") val temp: Double = 0.0
): Parcelable
