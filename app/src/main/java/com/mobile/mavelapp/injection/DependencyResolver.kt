package com.mobile.mavelapp.injection

import com.mobile.mavelapp.presenter.PresenterModel
import com.mobile.mavelapp.presenter.detail.DetailPresenter
import com.mobile.mavelapp.presenter.detail.DetailPresenterModel
import com.mobile.mavelapp.presenter.retrofit.MarvelApi
import com.mobile.mavelapp.presenter.retrofit.RetrofitInitializer

fun marvelApiResolver() : MarvelApi =  RetrofitInitializer().marvelApiService()

fun presenterModelResolver() : PresenterModel = PresenterModel()

fun detailPresenterModelResolver() : DetailPresenterModel = DetailPresenterModel()
