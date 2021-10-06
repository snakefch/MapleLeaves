package com.example.mapleleaves.ui.createcourse

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.body.CreateCourseBody

class CreateCourseViewModel:ViewModel() {

    private val CreateCourseLiveData= MutableLiveData<CreateCourseBody>()

    val courseLiveData= Transformations.switchMap(CreateCourseLiveData){ createCourseBody->
        Repository.createCourse(createCourseBody)
    }

    fun setCreateCourseLiveData(createCourseBody: CreateCourseBody){
        CreateCourseLiveData.value=createCourseBody
    }

}