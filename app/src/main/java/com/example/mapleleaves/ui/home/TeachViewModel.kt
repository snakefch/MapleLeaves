package com.example.mapleleaves.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.CoursesAttendedResponse

class TeachViewModel:ViewModel() {

    val teachCoursesList=ArrayList<CoursesAttendedResponse.Data>()

    private val teacherIdLiveData= MutableLiveData<String>()

    val teaCoursesLiveData= Transformations.switchMap(teacherIdLiveData){ teacherId->
        Repository.getCoursesAttended(teacherId)
    }

    fun setTeacherId(teacherId:String){
        teacherIdLiveData.value=teacherId
    }


}