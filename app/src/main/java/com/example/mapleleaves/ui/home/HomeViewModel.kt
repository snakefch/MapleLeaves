package com.example.mapleleaves.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.Course
import com.example.mapleleaves.logic.model.CoursesAttendedResponse
import com.example.mapleleaves.logic.model.User

class HomeViewModel : ViewModel() {

   /* val stuCoursesList= ArrayList<CoursesAttendedResponse.Data>()

    private val studentIdLiveData= MutableLiveData<String>()

    val stuCoursesLiveData= Transformations.switchMap(studentIdLiveData){ studentId->
        Repository.getCoursesAttended(studentId)
    }

    fun setStudent(studentId:String){
       studentIdLiveData.value=studentId
    }*/

}