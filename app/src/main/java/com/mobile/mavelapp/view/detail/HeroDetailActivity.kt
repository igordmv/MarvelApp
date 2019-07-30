package com.mobile.mavelapp.view.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.mavelapp.R
import com.mobile.mavelapp.injection.detailPresenterModelResolver
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.model.DetailDataResponse
import com.mobile.mavelapp.presenter.adapters.comics.HeroDetailedComicsAdapter
import com.mobile.mavelapp.presenter.adapters.events.HeroDetailedEventsAdapter
import com.mobile.mavelapp.presenter.adapters.series.HeroDetailedSeriesAdapter
import com.mobile.mavelapp.presenter.adapters.stories.HeroDetailedStoriesAdapter
import com.mobile.mavelapp.presenter.detail.DetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_detail_activity.*




class HeroDetailActivity : AppCompatActivity(), DetailView{

    lateinit var presenterLogic : DetailPresenter
    lateinit var id : String
    lateinit var name : String
    var heroDetailedSeriesAdapter : HeroDetailedSeriesAdapter? = null
    var heroDetailedComicsAdapter : HeroDetailedComicsAdapter? = null
    var heroDetailedEventsAdapter : HeroDetailedEventsAdapter? = null
    var heroDetailedStoriesAdapter : HeroDetailedStoriesAdapter? = null



    override fun showProgressBar() {
        pbLoadingDetailView.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pbLoadingDetailView.visibility = View.GONE
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.hero_detail_activity)
        val intent = this.intent
        id = intent.extras!!.get("heroId") as String
        name = intent.extras!!.get("name") as String

        showProgressBar()
        presenterLogic = DetailPresenter(detailPresenterModelResolver())
        presenterLogic.setView(this,this)
        presenterLogic.callHeroDetailRequest(id)
        presenterLogic.callHeroSeriesDetailRequest(id)
        presenterLogic.callHeroComicsDetailRequest(id)
        presenterLogic.callHeroEventsDetailRequest(id)
        presenterLogic.callHeroStoriesDetailRequest(id)

    }

    override fun requestFailed() {
        presenterLogic.callHeroDetailRequest(id)
    }

    override fun requestSuccess(marvelDataResponse: DataResponse) {
        Picasso.with(this)
            .load(marvelDataResponse.data!!.results[0].thumbnail!!.path + "/landscape_large." + marvelDataResponse.data!!.results[0].thumbnail!!.extension)
            .into(detail_ImageView)
        selected_character_name.text = marvelDataResponse.data!!.results[0].name
        selected_character_description.text = marvelDataResponse.data!!.results[0].description
    }

    override fun seriesRequestFailed() {
        presenterLogic.callHeroSeriesDetailRequest(id)
    }

    override fun seriesRequestSuccess(marvelDetailDataResponse: DetailDataResponse) {
        val mLayoutManager = LinearLayoutManager(this@HeroDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        heroDetailedSeriesAdapter = HeroDetailedSeriesAdapter(
            this@HeroDetailActivity,
            marvelDetailDataResponse.data!!.results
        )
        recyclerDetailedSeries.apply {

            layoutManager = mLayoutManager
            adapter = heroDetailedSeriesAdapter

        }
        hideProgressBar()
    }

    /**
     *
     *  Comics handlers
     *
     */

    override fun comicsRequestFailed() {
        presenterLogic.callHeroComicsDetailRequest(id)
    }

    override fun comicsRequestSuccess(marvelDetailDataResponse: DetailDataResponse) {
        val mLayoutManager = LinearLayoutManager(this@HeroDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        heroDetailedComicsAdapter = HeroDetailedComicsAdapter(
            this@HeroDetailActivity,
            marvelDetailDataResponse.data!!.results
        )
        recyclerDetailedComics.apply {

            layoutManager = mLayoutManager
            adapter = heroDetailedComicsAdapter

        }
        hideProgressBar()
    }

    /**
     *
     *  Events handlers
     *
     */

    override fun eventsRequestFailed() {
        presenterLogic.callHeroEventsDetailRequest(id)
    }

    override fun eventsRequestSuccess(marvelDetailDataResponse: DetailDataResponse) {
        val mLayoutManager = LinearLayoutManager(this@HeroDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        heroDetailedEventsAdapter = HeroDetailedEventsAdapter(
            this@HeroDetailActivity,
            marvelDetailDataResponse.data!!.results
        )
        recyclerDetailedEvents.apply {

            layoutManager = mLayoutManager
            adapter = heroDetailedEventsAdapter

        }
        hideProgressBar()
    }
    /**
     *
     *  Stories handlers
     *
     */

    override fun storiesRequestFailed() {
        presenterLogic.callHeroStoriesDetailRequest(id)
    }

    override fun storiesRequestSuccess(marvelDetailDataResponse: DetailDataResponse) {     val mLayoutManager = LinearLayoutManager(this@HeroDetailActivity, LinearLayoutManager.HORIZONTAL, false)
        heroDetailedStoriesAdapter = HeroDetailedStoriesAdapter(
            this@HeroDetailActivity,
            marvelDetailDataResponse.data!!.results
        )
        recyclerDetailedStories.apply {

            layoutManager = mLayoutManager
            adapter = heroDetailedStoriesAdapter

        }
        hideProgressBar()
    }


}