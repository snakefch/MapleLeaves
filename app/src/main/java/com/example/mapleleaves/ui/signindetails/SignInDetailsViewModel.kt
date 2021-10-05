package com.example.mapleleaves.ui.signindetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.response.GetStudentSignInByTeacherResponse

class SignInDetailsViewModel:ViewModel() {

    val signInList=ArrayList<GetStudentSignInByTeacherResponse.StudentSignInRecord>()

    private val signInIdLiveData=MutableLiveData<String>()

    fun refresh(signInId:String){
        signInIdLiveData.value=signInId
    }

    val resultLiveData=Transformations.switchMap(signInIdLiveData){
        Repository.getStudentSignInByTeacher(it)
    }

}