package com.example.mapleleaves.ui.studentsignin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository

class StudentSignInViewModel:ViewModel() {

    private val signInCodeLiveData=MutableLiveData<String>()

    private var studentId=""
    private var courseId=""
    private var signInId=""
    private var location=""

    fun refresh(studentId: String,courseId: String,signInId: String,location:String,signInCode:String){
        this.studentId=studentId
        this.courseId=courseId
        this.signInId=signInId
        this.location=location
        signInCodeLiveData.value=signInCode
    }

    val resultLiveData=Transformations.switchMap(signInCodeLiveData){
        Repository.signInByStudent(studentId,courseId,signInId,location,it)
    }

}