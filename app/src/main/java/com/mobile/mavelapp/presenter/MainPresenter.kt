package com.mobile.mavelapp.presenter

import android.content.Context
import android.util.Log
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.view.ViewInterface

class MainPresenter(val model: PresenterModel) : MainPresenterInterface {

    lateinit var mContext: Context
    lateinit var mView: ViewInterface
    var timestampStr: String? = null
    val LIMIT = 30
    var offset = 0

    init {
        this.model.setPresenter(this)
    }

    override fun setView(context: Context, view: ViewInterface) {
        mContext = context
        mView = view
    }

    override fun confirmFailedRequest() {
    }

    override fun confirmSuccessdRequest(marvelDataResponse: DataResponse) {
    }

    override fun callHeroListRequest() {
        Log.e("IGOR","bateu")
    }

}