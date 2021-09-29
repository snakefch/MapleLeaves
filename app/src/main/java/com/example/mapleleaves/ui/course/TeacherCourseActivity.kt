package com.example.mapleleaves.ui.course

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapleleaves.databinding.ActivityTeacherCourseBinding
import com.example.mapleleaves.logic.model.CoursesAttendedResponse
import com.google.gson.Gson

class TeacherCourseActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTeacherCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_teacher_course)
        binding= ActivityTeacherCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun startTeacherCourseActivity(context: Context, courseData: CoursesAttendedResponse.Data){
            val intent= Intent(context, TeacherCourseActivity::class.java).apply {
                putExtra("courseData", Gson().toJson(courseData))
            }
            context.startActivity(intent)
        }
    }
}