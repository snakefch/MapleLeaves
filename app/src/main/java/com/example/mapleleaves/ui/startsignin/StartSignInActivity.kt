package com.example.mapleleaves.ui.startsignin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityStartSignInBinding
import com.example.mapleleaves.logic.model.response.CoursesAttendedResponse
import com.example.mapleleaves.logic.model.body.StartSignInBody
import com.example.mapleleaves.ui.signinsheet.SignInSheetActivity
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.MyObserver
import com.example.mapleleaves.utils.showToast
import com.google.gson.Gson

class StartSignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStartSignInBinding

    private val TAG=this.javaClass.simpleName

    private val startSignInViewModel by lazy { ViewModelProviders.of(this).get(StartSignInViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStartSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonData=intent.getStringExtra("courseData")
        val courseData=Gson().fromJson(jsonData, CoursesAttendedResponse.Data::class.java)

        LogUtil.d(TAG,"$courseData")

        binding.btDetermine.setOnClickListener {
            startSignInViewModel.setstartSignInLiveData(StartSignInBody(binding.tvName.text.toString(),courseData.id,courseData.name,
            courseData.teacherId,courseData.teacher,courseData.number))
        }

        startSignInViewModel.resultLiveData.observe(this, Observer {
            val result=it.getOrNull()
            if (result!=null){
                "已发布新考勤".showToast()
                SignInSheetActivity.actionStart(this,courseData.id)
            }else{
                "发布考勤失败".showToast()
            }
            finish()
        })

        lifecycle.addObserver(MyObserver(TAG))

    }

    companion object{
        fun actionStart(context: Context,courseData: CoursesAttendedResponse.Data){
            val intent= Intent(context, StartSignInActivity::class.java).apply {
                putExtra("courseData", Gson().toJson(courseData))
            }
            context.startActivity(intent)
        }
    }
}