package com.example.mapleleaves.ui.createCourse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.CourseForCreate

class CreateCourseViewModel:ViewModel() {

    private val CreateCourseLiveData= MutableLiveData<CourseForCreate>()

    val courseLiveData= Transformations.switchMap(CreateCourseLiveData){ courseForCreate->
        Repository.createCourse(courseForCreate)
    }

    fun setCreateCourseLiveData(courseForCreate: CourseForCreate){
        CreateCourseLiveData.value=courseForCreate
    }

}