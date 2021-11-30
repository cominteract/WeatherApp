package com.ainsigne.domain.features

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ainsigne.domain.extension.EMPTY
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class DailyWeather(
    val dt: Long,
    val timezone: String = EMPTY,
    val name: String,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val clouds: Double = 0.0,
    val humidity: Double = 0.0,
    val pressure: Double = 0.0,
    val description: String,
    val icon: String,
    val displayDate: String,
    val date: Date,
    val dayTemp: Double = 0.0,
    val nightTemp: Double = 0.0,
): Parcelable
