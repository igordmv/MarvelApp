package com.mobile.mavelapp.presenter.detail

interface DetailModelInterface {
    fun getHeroDeatil(heroId: String,timestamp:String,publicKey: String,md5PrivateKey:String)
    fun setPresenter(presenter: DetailPresenter)
}