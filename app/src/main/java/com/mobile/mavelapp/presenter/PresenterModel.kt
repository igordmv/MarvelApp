package com.mobile.mavelapp.presenter

import android.content.Context

class PresenterModel : PresenterModelInterface {
    lateinit var mPresenter: MainPresenterInterface


    override fun getHeroList(context: Context, limit: Int, publicKey: String, md5PrivateKey: String) {

    }

    override fun setPresenter(presenter: MainPresenterInterface) {
        mPresenter = presenter
    }
}
