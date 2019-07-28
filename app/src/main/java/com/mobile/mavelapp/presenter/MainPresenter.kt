package com.mobile.mavelapp.presenter

import android.content.Context
import android.util.Log
import com.mobile.mavelapp.constants.Constants
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.presenter.encryption.Md5
import com.mobile.mavelapp.view.main.ViewInterface

class MainPresenter(val model: PresenterModel) : MainPresenterInterface {

    var timestampStr: String? = null
    val LIMIT = 30
    var offset = 0
    lateinit var mContext: Context
    lateinit var mView: ViewInterface

    init {
        this.model.setPresenter(this)
    }

    override fun setView(context: Context, view: ViewInterface) {
        mContext = context
        mView = view
    }

    override fun confirmFailedRequest() {
        mView.requestFailed()
    }

    override fun confirmSuccessRequest(marvelDataResponse: DataResponse) {
        mView.requestSuccess(marvelDataResponse.data!!.results)
    }

    override fun callHeroListRequest() {
        timestamp()
        model.getHeroList(mContext,
            LIMIT,
            offset,
            timestampStr!!,
            Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timestampStr+Constants.PRIVATE_API_KEY+Constants.PUBLIC_API_KEY)
        )

    }

    override fun timestamp() {

        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()
        timestampStr = ts

    }

}