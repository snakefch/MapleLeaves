package com.example.mapleleaves.ui.checkworkattendance

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.response.GetSignInByStudentResponse

class CheckViewModel:ViewModel() {

    val signInList=ArrayList<GetSignInByStudentResponse.StudentSignInRecord>()

    private val courseIdLiveData=MutableLiveData<String>()

    fun refresh(courseId:String){
        courseIdLiveData.value=courseId
    }

    val getResultLiveData=Transformations.switchMap(courseIdLiveData){
        Repository.getSignInByStudent(Repository.getUser().id,it)
    }

}