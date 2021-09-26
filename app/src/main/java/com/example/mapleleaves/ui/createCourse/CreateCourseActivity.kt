package com.example.mapleleaves.ui.createCourse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityCreateCourseBinding
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.CourseForCreate
import com.example.mapleleaves.utils.MyObserver
import com.example.mapleleaves.utils.showToast

class CreateCourseActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCreateCourseBinding

    private val TAG=this::class.java.simpleName

    private val createCourseViewModel by lazy { ViewModelProviders.of(this).get(CreateCourseViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.creatCourse.setOnClickListener{
            Repository.getUser().apply {
                createCourseViewModel.setCreateCourseLiveData(CourseForCreate( binding.courseName.text.toString(),binding.introduction.text.toString() ,name ,id,binding.number.text.toString().toInt()))
            }
        }

        createCourseViewModel.courseLiveData.observe(this, Observer {result->
            val data=result.getOrNull()
            if (data!=null){
                Log.d("Code",data.toString())
                "创建成功".showToast()
                finish()
            }else{
                Toast.makeText(this,"创建失败",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        lifecycle.addObserver(MyObserver(TAG))

    }

    companion object{
        fun startCreateCourseActivity(context: Context){
            val intent= Intent(context, CreateCourseActivity::class.java)
            context.startActivity(intent)
        }
    }
}