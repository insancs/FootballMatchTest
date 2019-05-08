package com.sanitcode.footballmatchdbtest.fragment

import com.google.gson.Gson
import com.sanitcode.footballmatchdbtest.api.APISportDB
import com.sanitcode.footballmatchdbtest.api.ApiRepository
import com.sanitcode.footballmatchdbtest.model.MatchModel
import com.sanitcode.footballmatchdbtest.model.MatchResponse
import com.sanitcode.footballmatchdbtest.util.CoroutineContextProviderTest
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MatchPresenter(view, apiRepository, gson, 1, CoroutineContextProviderTest())
    }

    @Test
    fun getList() {
        val events: MutableList<MatchModel> = mutableListOf()
        val leagueId = "4329"
        val response = MatchResponse(events)

        Mockito.`when`(gson.fromJson(apiRepository.doRequest(APISportDB.getPrevSchedule(leagueId)),
                MatchResponse::class.java
        )).thenReturn(response)

        presenter.getList(leagueId)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showList(response.events)
        Mockito.verify(view).hideLoading()
    }
}