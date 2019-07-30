package com.mobile.mavelapp.presenter.detail

import android.content.Context
import com.mobile.mavelapp.constants.Constants
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.presenter.PresenterModel
import com.mobile.mavelapp.presenter.encryption.Md5
import com.mobile.mavelapp.view.detail.DetailView

class DetailPresenter(val model: DetailPresenterModel) : DetailPresenterInterface {

    lateinit var mContext: Context
    lateinit var mView: DetailView
    var timestampStr: String? = null

    init {
        model.setPresenter(this)
    }

    override fun setView(context: Context, view: DetailView) {
        mContext = context
        mView = view
    }

    override fun confirmFailedRequest() {
        mView.requestFailed()
    }

    override fun confirmSuccessdRequest(marvelDataResponse: DataResponse) {
        mView.requestSuccess(marvelDataResponse)
    }

    override fun callHeroDetailRequest(heroId: String) {
        timestamp()
        model.getHeroDeatil(
            heroId,
            timestampStr!!,
            Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timestampStr+ Constants.PRIVATE_API_KEY+ Constants.PUBLIC_API_KEY))
    }

    override fun timestamp() {

        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()

        timestampStr = ts
    }
}