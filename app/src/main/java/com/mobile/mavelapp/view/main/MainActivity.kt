package com.mobile.mavelapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.mavelapp.injection.presenterModelResolver
import com.mobile.mavelapp.model.Result
import com.mobile.mavelapp.presenter.CharactersListAdapter
import com.mobile.mavelapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Gravity
import android.widget.SearchView
import android.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.mobile.mavelapp.R


class MainActivity : AppCompatActivity(), ViewInterface {

    lateinit var presenterLogic :  MainPresenter
    private var canLoadMore : Boolean = false
    var characterHeroAdapter : CharactersListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenterLogic = MainPresenter(presenterModelResolver())
        presenterLogic.setView(this,this)
        presenterLogic.callHeroListRequest()
        val search = findViewById(R.id.searchView) as SearchView
        search.setLayoutParams(Toolbar.LayoutParams(Gravity.RIGHT))
    }

    override fun requestFailed() {

    }

    override fun requestSuccess(results: ArrayList<Result>) {
        dismissProgressBar()
        canLoadMore = true
        presenterLogic.increaseOffset()
        if( characterHeroAdapter != null){

            for(i in results){

                characterHeroAdapter!!.addItem(i)
            }
            dismissBottomProgressBar()
            characterHeroAdapter!!.notifyDataSetChanged()
        }
        else{
            characterHeroAdapter = CharactersListAdapter(this@MainActivity, results)
            recycler.apply {

                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = characterHeroAdapter

            }
            recyclerListener()
        }
    }

    override fun recyclerListener() {

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val llm = recyclerView!!.layoutManager as LinearLayoutManager

                if (characterHeroAdapter != null && characterHeroAdapter!!.itemCount == llm.findLastVisibleItemPosition() + 5 && canLoadMore) {

                    if (dy > 0) {

                        presenterLogic.callHeroListRequest()
                        canLoadMore = false
                        showBottomProgressBar()
                    }

                }
            }
        })

    }

    override fun showProgressBar() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        pbLoading.visibility = View.GONE
    }

    override fun showBottomProgressBar() {

        pbLoadingBottom.visibility = View.VISIBLE
    }

    override fun dismissBottomProgressBar() {

        pbLoadingBottom.visibility = View.GONE
    }

}
