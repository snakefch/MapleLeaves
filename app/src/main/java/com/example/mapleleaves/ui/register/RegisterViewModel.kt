package com.example.mapleleaves.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.body.RegisterBody

class RegisterViewModel:ViewModel() {

    private val userNameLiveData=MutableLiveData<RegisterBody>()

    fun refresh(registerBody: RegisterBody){
        userNameLiveData.value=registerBody
    }

    val resultLiveData=Transformations.switchMap(userNameLiveData){
        Repository.postRegister(it)
    }

}