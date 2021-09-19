package com.example.mapleleaves.ui.joinCourse

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityJoinCourseBinding
import com.example.mapleleaves.utils.showToast

class JoinCourseActivity : AppCompatActivity() {

    private lateinit var binding:ActivityJoinCourseBinding

    private val joinClassViewModel by lazy { ViewModelProviders.of(this).get(JoinClassViewModel::class.java) }

    companion object{
        fun startActivity(context: Context){
            val intent=Intent(context,JoinCourseActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_join_course)
        binding= ActivityJoinCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            determine.setOnClickListener {
                joinClassViewModel.setAddClassCode(tvAddCourseCode.text.toString())
            }
        }

        joinClassViewModel.resultLiveData.observe(this, Observer { it->
            val result=it.getOrNull()
            if (result!=null){
                "加入成功".showToast()
                finish()
            } else{
                Toast.makeText(this,"加入失败，返回码 $result", Toast.LENGTH_SHORT).show()
                it.exceptionOrNull()?.printStackTrace()
            }
        })

    }
}