package com.mobile.mavelapp.presenter.detail

import android.content.Context
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.view.detail.DetailView

interface DetailPresenterInterface {
    fun setView(context: Context, view: DetailView)
    fun confirmFailedRequest()
    fun confirmSuccessdRequest(marvelDataResponse: DataResponse)
    fun callHeroDetailRequest(heroId: String)
    fun timestamp()
}