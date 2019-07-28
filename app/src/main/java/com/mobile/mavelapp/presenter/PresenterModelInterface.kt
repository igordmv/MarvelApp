package com.mobile.mavelapp.presenter

import android.content.Context

interface PresenterModelInterface {
        fun getHeroList(context: Context, limit: Int, offset: Int, timestamp: String, publicKey: String, md5PrivateKey:String)
        fun setPresenter(presenter: MainPresenterInterface)
}