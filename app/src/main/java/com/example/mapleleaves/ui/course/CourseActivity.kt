package com.example.mapleleaves.ui.course

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapleleaves.MainActivity
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.ActivityCheckWorkAttendanceBinding
import com.example.mapleleaves.databinding.ActivityCourseBinding
import com.example.mapleleaves.ui.checkWorkAttendance.CheckWorkAttendanceActivity

class CourseActivity : AppCompatActivity() {

    lateinit var binding: ActivityCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_course)
        val binding=ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivCheckWork.setOnClickListener {
            CheckWorkAttendanceActivity.startActivity(this)
        }
    }

    companion object {
        fun startCourseActivity(context: Context){
            val intent= Intent(context, CourseActivity::class.java)
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}