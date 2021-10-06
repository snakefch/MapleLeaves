package com.example.mapleleaves.ui.studentsignin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityStudentSignInBinding
import com.example.mapleleaves.utils.showToast

class StudentSignInActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStudentSignInBinding

    private val viewModel by lazy { ViewModelProviders.of(this).get(StudentSignInViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentSignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId=intent.getStringExtra("studentId")!!
        val courseId=intent.getStringExtra("courseId")!!
        val signInId=intent.getStringExtra("signInId")!!
        val location="NA"

        binding.btDetermine.setOnClickListener {
            viewModel.refresh(studentId,courseId,signInId,location,binding.tvSignInCode.text.toString())
        }

        viewModel.resultLiveData.observe(this, Observer {
            val result=it.getOrNull()
            if (result!=null){
                "签到成功".showToast()
            }else{
                "签到失败".showToast()
            }
            finish()
        })
    }

    companion object{
        fun startActivity(context: Context, studentId: String,courseId: String,signInId: String){
            val intent= Intent(context, StudentSignInActivity::class.java).apply {
                putExtra("studentId",studentId)
                putExtra("courseId",courseId)
                putExtra("signInId",signInId)
            }
            context.startActivity(intent)
        }
    }
}