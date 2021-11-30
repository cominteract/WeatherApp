package com.ainsigne.weatherapp

import com.ainsigne.weatherapp.helper.iconToBackground
import com.ainsigne.weatherapp.helper.iconToDrawable
import org.junit.Test

import org.junit.Assert.*

class DrawableUnitTest {
    @Test
    fun testShouldIndicateDrawableIsCorrect() {
        assertEquals(R.drawable.scatteredclouds_night, "03n".iconToDrawable())
        assertEquals(R.drawable.brokenclouds_day, "04d".iconToDrawable())
    }

    @Test
    fun testShouldIndicateBackgroundIsCorrect() {
        assertEquals(R.color.primary, "03n".iconToBackground())
        assertEquals(R.color.secondary, "04d".iconToBackground())
    }
}