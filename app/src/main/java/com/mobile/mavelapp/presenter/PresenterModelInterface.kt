package com.mobile.mavelapp.presenter

import android.content.Context

interface PresenterModelInterface {
        fun getHeroList(context: Context, limit: Int, publicKey: String, md5PrivateKey:String)
        fun setPresenter(presenter: MainPresenterInterface)
}