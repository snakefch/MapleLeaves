package com.example.mapleleaves.ui.place

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.response.Place

class PlaceViewModel:ViewModel() {

    private val searchLiveData=MutableLiveData<String>()

    val placeList=ArrayList<Place>()

    val placeLiveData=Transformations.switchMap(searchLiveData){ query->
        Log.d("查询值","$query")
        Repository.searchPlaces(query)

    }

    fun searchPlaces(query:String){
        searchLiveData.value=query
    }

    fun savePlace(place: Place)= Repository.savePlace(place)

    fun getPlace()= Repository.getPlace()

    fun isPalceSaved()= Repository.isPlaceSaved()

}