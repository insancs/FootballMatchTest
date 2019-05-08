package com.sanitcode.footballmatchdbtest.api

import android.net.Uri
import com.sanitcode.footballmatchdbtest.BuildConfig

class ApiSport(val id: String) {

    private fun getJson(path: String?) = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.API_KEY)
            .appendPath(path)
            .appendQueryParameter("id", id)
            .build().toString()

    fun getMatchDetail() = getJson("lookupevent.php")
    fun getTeamDetail() = getJson("lookupteam.php")
}