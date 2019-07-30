package com.mobile.mavelapp.presenter.detail

import android.content.Context
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.model.DetailDataResponse
import com.mobile.mavelapp.view.detail.DetailView

interface DetailPresenterInterface {
    fun setView(context: Context, view: DetailView)
    fun confirmFailedRequest()
    fun confirmSuccessedRequest(marvelDataResponse: DataResponse)
    fun confirmSuccessedSeriesRequest(marvelDetailDataResponse: DetailDataResponse)
    fun confirmFailedSeriesRequest()
    fun callHeroDetailRequest(heroId: String)
    fun callHeroSeriesDetailRequest(heroId: String)
    fun timestamp()
}