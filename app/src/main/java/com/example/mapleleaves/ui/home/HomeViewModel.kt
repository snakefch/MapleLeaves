package com.example.mapleleaves.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.model.Course

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    //val courseList=ArrayList<Course>()

    private val _studyCourseList=ArrayList<Course>().apply {
        add(Course("Android应用开发","2023280",80))
        add(Course("Android应用开发","2023281",81))
        add(Course("Android应用开发","2023282",82))
    }
    private val _teachCourseList=ArrayList<Course>().apply {
        add(Course("Android应用开发","2023280",80))
        add(Course("Android应用开发","2023281",81))
        add(Course("Android应用开发","2023282",82))
    }

    val studyCourseList=_studyCourseList
    val teachCourseList=_teachCourseList





}