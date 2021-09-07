package com.example.mapleleaves.logic.network

import com.example.mapleleaves.MapleLeavesApplication
import com.example.mapleleaves.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${MapleLeavesApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}