package com.mobile.mavelapp.view.detail

import android.os.Bundle
import android.text.method.MovementMethod
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mobile.mavelapp.R
import com.mobile.mavelapp.injection.detailPresenterModelResolver
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.model.DetailDataResponse
import com.mobile.mavelapp.presenter.detail.DetailPresenter
import kotlinx.android.synthetic.main.hero_detail_activity.*


class HeroDetailActivity : AppCompatActivity(), DetailView{
    override fun showProgressBar() {
        pbLoadingDetailView.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        pbLoadingDetailView.visibility = View.GONE
    }

    lateinit var presenterLogic : DetailPresenter
    lateinit var id : String
    lateinit var name : String
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
    }

    override fun requestFailed() {
        Toast.makeText(this@HeroDetailActivity,"Request falhou", Toast.LENGTH_SHORT).show()
    }

    override fun requestSuccess(marvelDataResponse: DataResponse) {
        if (marvelDataResponse.data!!.results[0].name.equals(name)){
            Glide.with(this).load(marvelDataResponse.data!!.results[0].thumbnail!!.path + "/landscape_large." + marvelDataResponse.data!!.results[0].thumbnail!!.extension).into(detail_ImageView)
            hideProgressBar()
            selected_character_name.text = marvelDataResponse.data!!.results[0].name
            selected_character_description.text = marvelDataResponse.data!!.results[0].description
            selected_character_description.movementMethod = ScrollingMovementMethod() as MovementMethod?
        }

    }

    override fun seriesRequestFailed() {
        Toast.makeText(this@HeroDetailActivity,"Request falhou", Toast.LENGTH_SHORT).show()
    }

    override fun seriesRequestSuccess(marvelDetailDataResponse: DetailDataResponse) {
        marvelDetailDataResponse.data!!.results.forEach({
            Log.e("IGOR",it.title)
        })
    }

}