package com.mobile.mavelapp.view.detail

import com.mobile.mavelapp.model.DataResponse

interface DetailView {
        fun requestFailed()
        fun requestSuccess(marvelDataResponse: DataResponse)
}
