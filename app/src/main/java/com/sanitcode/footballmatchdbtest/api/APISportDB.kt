package com.sanitcode.footballmatchdbtest.api

import com.sanitcode.footballmatchdbtest.BuildConfig

object APISportDB {
    private fun getJson(path: String, id: String?) = BuildConfig.BASE_URL+"api/v1/json/"+BuildConfig.API_KEY+"/"+path+"?id="+id

    fun getPrevSchedule(id: String?) = getJson("eventspastleague.php", id)
    fun getNextSchedule(id: String?) = getJson("eventsnextleague.php", id)
}

