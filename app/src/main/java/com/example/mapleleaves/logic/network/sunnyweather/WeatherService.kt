package com.example.mapleleaves.logic.network.sunnyweather

import com.example.mapleleaves.MapleLeavesApplication
import com.example.mapleleaves.logic.model.response.DailyResponse
import com.example.mapleleaves.logic.model.response.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${MapleLeavesApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng:String,@Path("lat") lat:String):Call<RealtimeResponse>

    @GET("v2.5/${MapleLeavesApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng:String,@Path("lat") lat:String):Call<DailyResponse>

}