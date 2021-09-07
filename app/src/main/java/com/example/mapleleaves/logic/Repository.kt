package com.example.mapleleaves.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.mapleleaves.logic.dao.PlaceDao
import com.example.mapleleaves.logic.model.Place
import com.example.mapleleaves.logic.model.Weather
import com.example.mapleleaves.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.Exception
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query:String) = fire(Dispatchers.IO){
               val placeResponse= SunnyWeatherNetwork.searchPlaces(query)
               if (placeResponse.status=="ok"){
                   val places=placeResponse.places
                   Result.success(places)
               }else{
                   Result.failure(RuntimeException("response status is ${placeResponse.status}"))
               }

    }

    fun refreshWeather(lng:String,lat:String)= fire(Dispatchers.IO){
        Log.d("refreshWeather","lng is ${lng},lat is ${lat}")
            coroutineScope {
                val deferredRealtime=async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily=async {
                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
                }
                val realtimeResponse=deferredRealtime.await()
                val dailyResponse=deferredDaily.await()
                if (realtimeResponse.status=="ok"&&dailyResponse.status=="ok"){
                    val weather= Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                            "realtime response status is ${realtimeResponse.status}"+
                                    "daily response status is ${dailyResponse.status}"
                        ))
                }

            }
    }

    private fun<T>fire(context: CoroutineContext, block:suspend ()->Result<T>)=
        liveData<Result<T>>(context){
            val result=try {
                block()
            }catch (e:Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: Place)= PlaceDao.savePlace(place)

    fun getPlace()=PlaceDao.getPlace()

    fun isPlaceSaved()=PlaceDao.isPlaceSaved()

}