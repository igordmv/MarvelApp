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
import com.mobile.mavelapp.presenter.adapters.series.HeroDetailedSeriesAdapter
import com.mobile.mavelapp.presenter.detail.DetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hero_detail_activity.*




class HeroDetailActivity : AppCompatActivity(), DetailView{
    lateinit var presenterLogic : DetailPresenter
    lateinit var id : String
    lateinit var name : String
    var heroDetailedSeriesAdapter : HeroDetailedSeriesAdapter? = null
    var heroDetailedComicsAdapter : HeroDetailedComicsAdapter? = null


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

    }

    override fun requestFailed() {
        Toast.makeText(this@HeroDetailActivity,"Request falhou", Toast.LENGTH_SHORT).show()
    }

    override fun requestSuccess(marvelDataResponse: DataResponse) {
        Picasso.with(this)
            .load(marvelDataResponse.data!!.results[0].thumbnail!!.path + "/landscape_large." + marvelDataResponse.data!!.results[0].thumbnail!!.extension)
            .into(detail_ImageView)
        selected_character_name.text = marvelDataResponse.data!!.results[0].name
        selected_character_description.text = marvelDataResponse.data!!.results[0].description
    }

    override fun seriesRequestFailed() {
        hideProgressBar()
        Toast.makeText(this@HeroDetailActivity,"Request falhou", Toast.LENGTH_SHORT).show()
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
    override fun comicsRequestFailed() {
        hideProgressBar()
        Toast.makeText(this@HeroDetailActivity,"Request falhou", Toast.LENGTH_SHORT).show()
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


}