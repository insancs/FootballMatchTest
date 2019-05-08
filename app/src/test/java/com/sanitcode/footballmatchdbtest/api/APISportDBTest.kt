package com.sanitcode.footballmatchdbtest.api

import org.junit.Test
import org.junit.Assert.*

class APISportDBTest {
    @Test
    fun getPrevSchedule() {
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        assertEquals(url, APISportDB.getPrevSchedule("4328"))
    }

    @Test
    fun getNextSchedule() {
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328"
        assertEquals(url, APISportDB.getNextSchedule("4328"))
    }
}