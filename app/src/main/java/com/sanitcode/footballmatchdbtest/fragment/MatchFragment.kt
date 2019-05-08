package com.sanitcode.footballmatchdbtest.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.sanitcode.footballmatchdbtest.R

import com.sanitcode.footballmatchdbtest.activity.detail.DetailActivity
import com.sanitcode.footballmatchdbtest.adapter.MatchAdapter
import com.sanitcode.footballmatchdbtest.model.MatchModel
import com.sanitcode.footballmatchdbtest.api.ApiRepository
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchFragment : Fragment(),  AnkoComponent<Context>, MatchView{
    private var match: MutableList<MatchModel> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var matchAdapter: MatchAdapter
    private var fixture= 1
    private var leagueId = "4328"

    companion object {
        fun newInstance(fixture: Int, leagueId: String): MatchFragment {
            val fragment = MatchFragment()
            fragment.fixture = fixture
            fragment.leagueId = leagueId
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, apiRepository, gson, fixture)

        matchAdapter = MatchAdapter(match){
            ctx.startActivity<DetailActivity>("EVENT" to it)
        }

        recyclerView.adapter = matchAdapter

        swipeRefresh.onRefresh {
            presenter.getList(leagueId)
        }

        presenter.getList(leagueId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showList(data: List<MatchModel>) {
        hideLoading()
        match.clear()
        match.addAll(data)
        matchAdapter.notifyDataSetChanged()
    }

    override fun createView(ui: AnkoContext<Context>) = with(ui){
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(8)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_red_light ,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light
                )

                recyclerView = recyclerView {
                    id = R.id.rv_match
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

        }
    }
}// Required empty public constructor
