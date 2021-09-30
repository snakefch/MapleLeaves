package com.example.mapleleaves.ui.course

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository

class CourseManagementDialogViewModel : ViewModel() {

    private val teaCourseId=MutableLiveData<String>()

    val removeResultLiveData= Transformations.switchMap(teaCourseId){ courseId->
        Repository.removeCourseCreated(courseId)
    }

    fun setTeaCourseId(courseId:String){
        teaCourseId.value=courseId
    }


    //学生，是否考虑分开
    private val stuCourseIdLiveData=MutableLiveData<String>()

    fun setStuCourseId(studentId:String){
        stuCourseIdLiveData.value=studentId
    }

    val quitResultLiveData=Transformations.switchMap(stuCourseIdLiveData){
        Repository.quitTheCourse(Repository.getUser().id,it)
    }

}