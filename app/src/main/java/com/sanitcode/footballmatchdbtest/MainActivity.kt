package com.sanitcode.footballmatchdbtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.sanitcode.footballmatchdbtest.R.id.*
import com.sanitcode.footballmatchdbtest.favorite.FavoriteFragment
import com.sanitcode.footballmatchdbtest.fragment.MatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  var leagueId = "4328" //EPL
    private var fixture = 1
    private var savedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setContentView(R.layout.activity_main)

        nav_bottomview.setOnNavigationItemSelectedListener {
                item -> when(item.itemId){
            nav_previous -> {
                fixture = 1
                openFragment(MatchFragment.newInstance(fixture, leagueId))
                return@setOnNavigationItemSelectedListener true
            }
            nav_next -> {
                fixture = 2
                openFragment(MatchFragment.newInstance(fixture, leagueId))
                return@setOnNavigationItemSelectedListener true
            }
            nav_favorite -> {
                openFragment(FavoriteFragment())
                return@setOnNavigationItemSelectedListener true
            }
        }
            false
        }
        nav_bottomview.selectedItemId = nav_previous
    }

    private fun openFragment(fragment: Fragment){
        if(savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
        }
    }
}
