package com.sanitcode.footballmatchdbtest.activity.detail

import com.sanitcode.footballmatchdbtest.model.MatchModel
import com.sanitcode.footballmatchdbtest.model.Team

interface DetailView {
    fun showLoading()
    fun hideloading()
    fun getDetail(matchDetail: MatchModel, hometeam: MatchModel, awayTeam: MatchModel)
}