package com.example.mapleleaves.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.mapleleaves.MapleLeavesApplication
import com.google.gson.Gson
import com.example.mapleleaves.logic.model.response.Place

object PlaceDao {

    fun savePlace(place: Place){
        sharedPreferences().edit(){
            putString("place",Gson().toJson(place))
        }
    }

    fun getPlace(): Place? {
        val placeJson= sharedPreferences().getString("place","")
        return if(placeJson!="") Gson().fromJson(placeJson, Place::class.java) else null
    }

    fun isPlaceSaved()= sharedPreferences().contains("place")

    private fun sharedPreferences()=MapleLeavesApplication.context.
    getSharedPreferences("sunny_weather",Context.MODE_PRIVATE)
}
