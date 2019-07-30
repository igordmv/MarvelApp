package com.mobile.mavelapp.presenter.detail

interface DetailModelInterface {
    fun getHeroDetail(heroId: String, timestamp:String, publicKey: String, md5PrivateKey:String)
    fun setPresenter(presenter: DetailPresenter)
    fun getHeroDetailSeries(heroId: String, timestamp: String, publicKey: String, md5PrivateKey: String)
}