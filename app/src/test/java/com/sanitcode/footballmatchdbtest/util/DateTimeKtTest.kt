package com.sanitcode.footballmatchdbtest.util

import org.junit.Test
import org.junit.Assert.*
import java.text.SimpleDateFormat

class DateTimeKtTest {
    @Test
    fun stringToDate() {
        val dateFormat = SimpleDateFormat("dd/MM/yyy")
        val date = dateFormat.parse("28/09/2018")
        assertEquals(date, stringToDate("2018-09-28"))
    }

    @Test
    fun changeFormatDate() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Rab, 28 Feb 2018", changeFormatDate(date))
    }
}