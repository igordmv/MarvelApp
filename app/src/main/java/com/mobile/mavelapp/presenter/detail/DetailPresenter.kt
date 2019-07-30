package com.mobile.mavelapp.presenter.detail

import android.content.Context
import com.mobile.mavelapp.constants.Constants
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.model.DetailDataResponse
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

    override fun confirmSuccessedRequest(marvelDataResponse: DataResponse) {
        mView.requestSuccess(marvelDataResponse)
    }
    override fun confirmSuccessedSeriesRequest(marvelDetailDataResponse: DetailDataResponse) {
        mView.seriesRequestSuccess(marvelDetailDataResponse)
    }

    override fun confirmFailedSeriesRequest() {
        mView.seriesRequestFailed()
    }
    override fun confirmSuccessedComicsRequest(marvelDetailDataResponse: DetailDataResponse) {
        mView.comicsRequestSuccess(marvelDetailDataResponse)
    }

    override fun confirmFailedComicsRequest() {
        mView.comicsRequestFailed()
    }

    override fun confirmFailedEventsRequest() {
        mView.eventsRequestFailed()
    }

    override fun confirmSuccessedEventsRequest(marvelDetailDataResponse: DetailDataResponse) {
        mView.eventsRequestSuccess(marvelDetailDataResponse)
    }
    override fun confirmSuccessedStoriesRequest(marvelDetailDataResponse: DetailDataResponse) {
        mView.storiesRequestSuccess(marvelDetailDataResponse)
    }

    override fun confirmFailedStoriesRequest() {
        mView.storiesRequestFailed()
    }

    override fun callHeroStoriesDetailRequest(heroId: String) {
        timestamp()
        model.getHeroDetailStories(
            heroId,
            timestampStr!!,
            Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timestampStr+ Constants.PRIVATE_API_KEY+ Constants.PUBLIC_API_KEY))
    }


    override fun callHeroEventsDetailRequest(heroId: String) {
        timestamp()
        model.getHeroDetailEvents(
            heroId,
            timestampStr!!,
            Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timestampStr+ Constants.PRIVATE_API_KEY+ Constants.PUBLIC_API_KEY))
    }

    override fun callHeroComicsDetailRequest(heroId: String) {
        timestamp()
        model.getHeroDetailComics(
            heroId,
            timestampStr!!,
            Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timestampStr+ Constants.PRIVATE_API_KEY+ Constants.PUBLIC_API_KEY))
    }
    override fun callHeroDetailRequest(heroId: String) {
        timestamp()
        model.getHeroDetail(
            heroId,
            timestampStr!!,
            Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timestampStr+ Constants.PRIVATE_API_KEY+ Constants.PUBLIC_API_KEY))
    }
    override fun callHeroSeriesDetailRequest(heroId: String) {
        timestamp()
        model.getHeroDetailSeries(
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