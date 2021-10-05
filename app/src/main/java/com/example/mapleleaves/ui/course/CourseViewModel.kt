package com.example.mapleleaves.ui.course

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.model.response.CoursesAttendedResponse

class CourseViewModel:ViewModel() {

    private val _courseLiveData=MutableLiveData<CoursesAttendedResponse.Data>()

    val courseLiveData:LiveData<CoursesAttendedResponse.Data> get() = _courseLiveData

    fun saveCourseData(courseData: CoursesAttendedResponse.Data){
        _courseLiveData.value=courseData
    }

}