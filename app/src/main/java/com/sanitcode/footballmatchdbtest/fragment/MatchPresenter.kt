package com.sanitcode.footballmatchdbtest.fragment

import com.google.gson.Gson
import com.sanitcode.footballmatchdbtest.api.ApiRepository
import com.sanitcode.footballmatchdbtest.api.APISportDB
import com.sanitcode.footballmatchdbtest.model.MatchResponse
import com.sanitcode.footballmatchdbtest.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val fixture: Int = 1,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getList(id: String?){
        view.showLoading()
        val api = if (fixture == 1) APISportDB.getPrevSchedule(id) else APISportDB.getNextSchedule(id)

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(api), MatchResponse::class.java)
            }

            view.showList(data.await().events)
            view.hideLoading()
        }
    }
}