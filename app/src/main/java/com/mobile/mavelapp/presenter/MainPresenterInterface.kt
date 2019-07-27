package com.mobile.mavelapp.presenter

import android.content.Context
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.view.ViewInterface

interface MainPresenterInterface {

    fun setView(context: Context, view: ViewInterface)
    fun confirmFailedRequest()
    fun confirmSuccessdRequest(marvelDataResponse: DataResponse)
    fun callHeroListRequest()

}