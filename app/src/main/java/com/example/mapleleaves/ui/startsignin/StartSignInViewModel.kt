package com.example.mapleleaves.ui.startsignin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.body.StartSignInBody

class StartSignInViewModel:ViewModel() {

    private val startSignInLiveData= MutableLiveData<StartSignInBody>()

    fun setstartSignInLiveData(startSignInBody: StartSignInBody){
        startSignInLiveData.value=startSignInBody
    }

    val resultLiveData= Transformations.switchMap(startSignInLiveData){
        Repository.startSignIn(it)
    }

}