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
@Entity(tableName = "weather_table")
@TypeConverters(DateConverter::class)
data class DailyWeather(
    @PrimaryKey val dt: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "timezone") val timezone: String,
    @ColumnInfo(name = "lat") val lat: Double = 0.0,
    @ColumnInfo(name = "lon") val lon: Double = 0.0,
    @ColumnInfo(name = "clouds") val clouds: Double = 0.0,
    @ColumnInfo(name = "humidity") val humidity: Double = 0.0,
    @ColumnInfo(name = "pressure") val pressure: Double = 0.0,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "display_date") val displayDate: String,
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "day_temp") val dayTemp: Double = 0.0,
    @ColumnInfo(name = "night_temp") val nightTemp: Double = 0.0
): Parcelable
