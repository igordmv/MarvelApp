package com.mobile.mavelapp.view.main

import com.mobile.mavelapp.model.Result

interface ViewInterface {

    fun requestFailed()
    fun requestSuccess(results: ArrayList<Result>)
    fun showProgressBar()
    fun dismissProgressBar()
    fun recyclerListener()
    fun showBottomProgressBar()
    fun dismissBottomProgressBar()
}