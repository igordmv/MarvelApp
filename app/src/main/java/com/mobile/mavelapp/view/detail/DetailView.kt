package com.mobile.mavelapp.view.detail

import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.model.DetailDataResponse

interface DetailView {
        fun requestFailed()
        fun requestSuccess(marvelDataResponse: DataResponse)
        fun seriesRequestFailed()
        fun seriesRequestSuccess(marvelDetailDataResponse: DetailDataResponse)
        fun comicsRequestFailed()
        fun comicsRequestSuccess(marvelDetailDataResponse: DetailDataResponse)
        fun showProgressBar()
        fun hideProgressBar()
}
