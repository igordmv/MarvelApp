package com.mobile.mavelapp.presenter.detail

import com.mobile.mavelapp.injection.marvelApiResolver
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.presenter.retrofit.MarvelApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailPresenterModel(api: MarvelApi = marvelApiResolver()) : DetailModelInterface {


    lateinit var mPresenter: DetailPresenter
    var mApi: MarvelApi = api

    override fun setPresenter(presenter: DetailPresenter) {
        mPresenter = presenter
    }

    override fun getHeroDeatil(heroId:String, timestamp: String, publicKey: String, md5PrivateKey: String) {

        val observable = mApi.getHeroDetail(heroId,timestamp,publicKey,md5PrivateKey)
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DataResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(sucessResponse: DataResponse) {

                    mPresenter.confirmSuccessdRequest(sucessResponse)

                }

                override fun onError(e: Throwable) {

                    mPresenter.confirmFailedRequest()

                }

                override fun onComplete() {

                }
            })

    }
}