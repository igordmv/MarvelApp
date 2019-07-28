package com.mobile.mavelapp.presenter.integrationTest

import com.mobile.mavelapp.constants.Constants
import com.mobile.mavelapp.injection.marvelApiResolver
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.presenter.encryption.Md5
import com.mobile.mavelapp.presenter.retrofit.MarvelApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.junit.Before
import org.junit.Test

class MarvelApiIntegrationTest {

    lateinit var marvelApi: MarvelApi

    @Before
    fun setup(){
        marvelApi = marvelApiResolver()
    }

    @Test
    fun `assert that when put limit 1 just one character will be answered`(){
        var timeStamp = getTimeStamp()
        var limit = 1
        var observable = marvelApi.getHeroesList(limit,0, timeStamp, Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timeStamp+Constants.PRIVATE_API_KEY+Constants.PUBLIC_API_KEY ))
        observable
            .subscribe(object : Observer<DataResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(sucessResponse: DataResponse) {
                    assert(sucessResponse.data!!.results.size == limit)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
    }

    @Test
    fun `assert that when put limit 10 just ten characters will be answered`(){
        var timeStamp = getTimeStamp()
        var limit = 10
        var observable = marvelApi.getHeroesList(limit,0, timeStamp, Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timeStamp+Constants.PRIVATE_API_KEY+Constants.PUBLIC_API_KEY ))
        observable
            .subscribe(object : Observer<DataResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(sucessResponse: DataResponse) {
                    assert(sucessResponse.data!!.results.size == limit)
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
    }

    @Test
    fun `assert that when search by a exactly id, the correct character is being answered`(){
        var searchedId = "1010354"
        var expectedCharacterName = "Adam Warlock"
        var timeStamp = getTimeStamp()
        var observable = marvelApi.getHeroDetail(searchedId,timeStamp, Constants.PUBLIC_API_KEY,
            Md5().generateMd5FromString(timeStamp+Constants.PRIVATE_API_KEY+Constants.PUBLIC_API_KEY ))
        observable
            .subscribe(object : Observer<DataResponse> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(sucessResponse: DataResponse) {
                    assert(sucessResponse.data!!.results.get(0).name == expectedCharacterName )
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }
            })
    }

    private fun getTimeStamp () : String{
        val tsLong = System.currentTimeMillis() / 1000
        val ts = tsLong.toString()
        return ts
    }
}