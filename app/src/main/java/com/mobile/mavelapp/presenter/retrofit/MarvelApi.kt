package com.mobile.mavelapp.presenter.retrofit

import com.mobile.mavelapp.model.DataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getHeroesList(@Query("limit") limit: Int, @Query("offset") offset: Int, @Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<DataResponse>

    @GET("characters/{characterId}")
    fun getHeroDetail(@Path("characterId") id: String, @Query("ts") ts: String, @Query("apikey") apikey: String, @Query("hash") hash: String): Observable<DataResponse>

}