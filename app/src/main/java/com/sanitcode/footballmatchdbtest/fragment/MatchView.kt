package com.sanitcode.footballmatchdbtest.fragment

import com.sanitcode.footballmatchdbtest.model.MatchModel

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<MatchModel>)
}