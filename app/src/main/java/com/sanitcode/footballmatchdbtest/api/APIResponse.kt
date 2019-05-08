package com.sanitcode.footballmatchdbtest.api

import com.sanitcode.footballmatchdbtest.model.MatchModel

data class APIResponse(
        val events: List<MatchModel>,
        val teams: List<MatchModel>
)