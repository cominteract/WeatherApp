package com.ainsigne.utilities

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DateExtensionUnitTest {
    val testDate : Long = 1638169601

    @Test
    fun testShouldCorrectlyFormatDate() {
        assertEquals(testDate.toDate(), Date(testDate * 1000))
    }

    @Test
    fun testShouldCorrectlyFormatHoursString() {
        assertEquals(testDate.toDate().hourFormatToDisplay(),"3:06:41 PM")
    }

    @Test
    fun testShouldCorrectlyFormatDateString() {
        assertEquals(testDate.toDate().formatToDisplay(),"Nov 29 2021")
    }

    @Test
    fun testShouldCorrectlyFormatDateTimeString() {
        assertEquals(testDate.toDate().fullFormatToDisplay(),"Nov 29 2021 03:06:41 PM")

    }
}