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
    fun confirmSuccessedComicsRequest(marvelDetailDataResponse: DetailDataResponse)
    fun confirmFailedComicsRequest()
    fun confirmSuccessedEventsRequest(marvelDetailDataResponse: DetailDataResponse)
    fun confirmFailedEventsRequest()
    fun confirmSuccessedStoriesRequest(marvelDetailDataResponse: DetailDataResponse)
    fun confirmFailedStoriesRequest()
    fun callHeroDetailRequest(heroId: String)
    fun callHeroSeriesDetailRequest(heroId: String)
    fun callHeroComicsDetailRequest(heroId: String)
    fun callHeroEventsDetailRequest(heroId: String)
    fun callHeroStoriesDetailRequest(heroId: String)
    fun timestamp()
}