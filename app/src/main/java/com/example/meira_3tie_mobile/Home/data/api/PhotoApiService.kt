package com.example.meira_3tie_mobile.Home.data.api

import com.example.meira_3tie_mobile.Home.data.model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}