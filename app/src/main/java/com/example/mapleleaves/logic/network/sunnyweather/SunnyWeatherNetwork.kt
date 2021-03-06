package com.example.mapleleaves.logic.network.sunnyweather

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val weatherService= SunnyWeatherServiceCreator.create(WeatherService::class.java)

    suspend fun getDailyWeather(lng:String,lat:String)= weatherService.getDailyWeather(lng, lat).await()

    suspend fun getRealtimeWeather(lng: String,lat: String)= weatherService.getRealtimeWeather(lng, lat).await()

    private val placeService= SunnyWeatherServiceCreator.create(PlaceService::class.java)

    suspend fun searchPlaces(query:String)= placeService.searchPlaces(query).await()

    private suspend fun <T> Call<T>.await():T{

        return suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body=response.body()
                Log.d("body", body.toString())
                if (body!=null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("response body is null"))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
         })

        }

    }


}