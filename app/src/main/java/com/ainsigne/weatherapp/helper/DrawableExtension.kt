package com.ainsigne.weatherapp.helper

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.ainsigne.weatherapp.R

@DrawableRes
fun String.iconToDrawable() : Int{
    return when (this) {
        "01d" -> R.drawable.clearsky_day
        "01n" -> R.drawable.clearsky_night
        "02d" -> R.drawable.fewclouds_day
        "02n" -> R.drawable.fewclouds_night
        "03d" -> R.drawable.scatteredclouds_day
        "03n" -> R.drawable.scatteredclouds_night
        "04d" -> R.drawable.brokenclouds_day
        "04n" -> R.drawable.brokenclouds_night
        "09d" -> R.drawable.showerrain_day
        "09n" -> R.drawable.showerrain_night
        "10d" -> R.drawable.rain_day
        "10n" -> R.drawable.rain_night
        "11d" -> R.drawable.thunderstorm_day
        "11n" -> R.drawable.thunderstorm_night
        "13d" -> R.drawable.snow_day
        "13n" -> R.drawable.snow_night
        "50d" -> R.drawable.mist_day
        "50n" -> R.drawable.mist_night
        else -> R.drawable.clearsky_day
    }
}

@ColorRes
fun String.iconToBackground() : Int{
    return when (this) {
        "01d","02d","03d","50d","13d","11d","10d","09d","04d" -> R.color.secondary
        else -> R.color.primary
    }
}