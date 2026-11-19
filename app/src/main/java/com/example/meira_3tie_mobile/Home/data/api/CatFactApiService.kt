package com.example.meira_3tie_mobile.Home.data.api

import com.example.meira_3tie_mobile.Home.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}