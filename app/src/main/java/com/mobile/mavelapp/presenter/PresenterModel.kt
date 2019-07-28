package com.mobile.mavelapp.presenter

import android.content.Context
import android.util.Log
import com.mobile.mavelapp.injection.marvelApiResolver
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.presenter.retrofit.MarvelApi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PresenterModel(var mApi : MarvelApi = marvelApiResolver()) : PresenterModelInterface {
    lateinit var mPresenter: MainPresenterInterface

    override fun getHeroList(context: Context, limit: Int, offset: Int, timestamp: String, publicKey: String, md5PrivateKey: String) {
        val observable = mApi.getHeroesList(limit,offset,timestamp,publicKey,md5PrivateKey)
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<DataResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(sucessResponse: DataResponse) {
                    Log.i("SUCCESS",""+sucessResponse.status)

                    mPresenter.confirmSuccessRequest(sucessResponse)

                }

                override fun onError(e: Throwable) {

                    mPresenter.confirmFailedRequest()
                    Log.i("ERROR",""+e)
                }

                override fun onComplete() {

                }
            })
    }

    override fun setPresenter(presenter: MainPresenterInterface) {
        mPresenter = presenter
    }
}
