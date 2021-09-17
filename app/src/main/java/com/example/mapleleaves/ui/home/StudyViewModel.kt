package com.example.mapleleaves.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.CoursesAttendedResponse

class StudyViewModel:ViewModel() {

    val stuCoursesList= ArrayList<CoursesAttendedResponse.Data>()

    private val studentIdLiveData= MutableLiveData<String>()

    val stuCoursesLiveData= Transformations.switchMap(studentIdLiveData){ studentId->
        Repository.getCoursesAttended(studentId)
    }

    fun setStudentId(studentId:String){
        studentIdLiveData.value=studentId
    }
}