package com.example.mapleleaves.ui.course

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityTeacherCourseBinding
import com.example.mapleleaves.logic.model.CoursesAttendedResponse
import com.example.mapleleaves.ui.home.TeachViewModel
import com.example.mapleleaves.ui.signinsheet.SignInSheetActivity
import com.example.mapleleaves.ui.startsignin.StartSignInActivity
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.MyObserver
import com.google.gson.Gson

class TeacherCourseActivity : AppCompatActivity() {

    private lateinit var binding:ActivityTeacherCourseBinding

    private val TAG=this::class.java.simpleName

    private val teachViewModel by lazy {ViewModelProviders.of(this).get(CourseViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_teacher_course)
        binding= ActivityTeacherCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Activity的布局会显示在状态栏上
        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // window.setDecorFitsSystemWindows(false)
        //将状态栏设置成透明色
        window.statusBarColor= Color.TRANSPARENT

        val jsonData=intent.getStringExtra("courseData")
        val courseData=Gson().fromJson(jsonData,CoursesAttendedResponse.Data::class.java)

        teachViewModel.saveCourseData(courseData)
        teachViewModel.courseLiveData.observe(this, Observer {
            binding.apply {
                tvCourseName.text=it.name
                tvCourseCode.text=it.id
                tvAddCourseCode.text=it.addClassCode
                tvCourseMemberCount.text= it.number.toString()+"人"

            }
        })

        binding.ivCheckWork.setOnClickListener {
            StartSignInActivity.actionStart(this,courseData)
        }

        binding.ivSignInList.setOnClickListener {
            LogUtil.d("传送的id","${courseData.id}")
            SignInSheetActivity.actionStart(this,courseData.id)
        }

        lifecycle.addObserver(MyObserver(TAG))
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