package com.mobile.mavelapp.presenter.retrofit

import android.content.Context
import com.mobile.mavelapp.model.Data
import com.mobile.mavelapp.model.DataResponse
import com.mobile.mavelapp.model.Result
import com.mobile.mavelapp.presenter.MainPresenter
import com.mobile.mavelapp.presenter.PresenterModel
import com.mobile.mavelapp.view.ViewInterface
import io.mockk.*
import org.junit.Before
import org.junit.Test

class MainPresenterTest{

    var marvelDataResponse : DataResponse = mockk<DataResponse>()
    var presenterModel : PresenterModel = mockk<PresenterModel>()
    var context : Context = mockk<Context>()
    var viewInterface : ViewInterface = mockk<ViewInterface>()
    var data : Data = mockk<Data>()
    lateinit var subject: MainPresenter

    @Before
    fun setup(){
        every { presenterModel.setPresenter(any()) } just Runs
        subject = MainPresenter(presenterModel)
        subject.setView(context,viewInterface)
    }

    @Test
    fun `when call hero list request should pass request to presenter model getHeroList`(){
        every { presenterModel.getHeroList(any(),any(),any(),any(),any(),any()) } just Runs
        subject.callHeroListRequest()
        verify(exactly = 1){ presenterModel.getHeroList(any(),any(),any(),any(),any(),any())}
    }

    @Test
    fun `when call confirm success request should pass information to view to get things updated`(){
        every { viewInterface.requestSuccess(any()) } just Runs
        every { marvelDataResponse.data} returns data
        every { data.results} returns ArrayList<Result>()
        subject.confirmSuccessRequest(marvelDataResponse)
        verify (exactly = 1) { viewInterface.requestSuccess(any())}
    }

    @Test
    fun `when call confirm failed request should pass information to view to get things updated`(){
        every { viewInterface.requestFailed() } just Runs
        subject.confirmFailedRequest()
        verify (exactly = 1) { viewInterface.requestFailed()}
    }

}