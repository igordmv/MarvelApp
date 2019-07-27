package com.mobile.mavelapp.view

import com.mobile.mavelapp.model.Result

interface ViewInterface {

    fun requestFailed()
    fun requestSuccess(results: ArrayList<Result>)

}