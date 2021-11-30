package com.ainsigne.domain.features

import android.os.Parcelable
import androidx.room.Entity
import com.ainsigne.domain.extension.EMPTY
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class CurrentWeather(
    val dt: Long,
    val name: String,
    val timezone: String = EMPTY,
    val description: String,
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val icon: String,
    val displayDate: String,
    val date: Date,
    val sunrise: Date,
    val sunset: Date,
    val clouds: Double,
    val humidity: Double,
    val pressure: Double,
    val temp: Double = 0.0
): Parcelable
