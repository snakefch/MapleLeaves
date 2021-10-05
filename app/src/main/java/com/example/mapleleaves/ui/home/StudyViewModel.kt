package com.example.mapleleaves.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.response.CoursesAttendedResponse

class StudyViewModel:ViewModel() {

    val stuCoursesList= ArrayList<CoursesAttendedResponse.Data>()

    private val studentIdLiveData= MutableLiveData<String>()

    val stuCoursesLiveData= Transformations.switchMap(studentIdLiveData){ studentId->
        Repository.getCoursesAttended(studentId)
    }

    fun getUserId():String{
        return Repository.getUser().id
    }

    fun setStudentId(studentId:String){
        studentIdLiveData.value=studentId
    }

    fun refresh(){
        studentIdLiveData.value=Repository.getUser().id
    }
}