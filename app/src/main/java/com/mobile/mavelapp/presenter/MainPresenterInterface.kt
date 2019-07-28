package com.mobile.mavelapp.presenter

import android.content.Context
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.view.main.ViewInterface

interface MainPresenterInterface {

    fun setView(context: Context, view: ViewInterface)
    fun confirmFailedRequest()
    fun confirmSuccessRequest(marvelDataResponse: DataResponse)
    fun callHeroListRequest()
    fun timestamp()

}