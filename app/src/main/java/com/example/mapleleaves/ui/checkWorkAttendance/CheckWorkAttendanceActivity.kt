package com.example.mapleleaves.ui.checkWorkAttendance

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.databinding.ActivityCheckWorkAttendanceBinding
import com.example.mapleleaves.logic.model.Check
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.MyObserver
import com.example.mapleleaves.utils.showToast

class CheckWorkAttendanceActivity : AppCompatActivity() {

    private val checkViewModel by lazy { ViewModelProviders.of(this).get(CheckViewModel::class.java) }

    private lateinit var binding: ActivityCheckWorkAttendanceBinding
    private lateinit var listAdapter: CheckListAdapter

    private val TAG=this::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_check_work_attendance)
        binding= ActivityCheckWorkAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager=LinearLayoutManager(this)
        binding.rvCheck.layoutManager=layoutManager

        checkViewModel.getResultLiveData.observe(this, Observer { result ->
            val signInList=result.getOrNull()
            if (signInList!=null){
//                val count1=signInList.filter { it.result=="旷课"}.size
//                "旷课次数为：$count1".showToast()
                val attendanceTimes=signInList.filter { it.result=="出勤" }.size
                val absenteeismTimes=signInList.filter { it.result=="旷课" }.size
                val lateTimes=signInList.filter { it.result=="迟到" }.size
                val earlyTimes=signInList.filter { it.result=="早退" }.size
//              val leaveTimes=signInList.filter { it.result=="请假" }.size
                val leaveTimes=signInList.size-attendanceTimes-absenteeismTimes-lateTimes-earlyTimes
                binding.apply {
                    tvAttendanceTimes.text=attendanceTimes.toString()
                    tvAbsenteeismTimes.text=absenteeismTimes.toString()
                    tvLateTimes.text=lateTimes.toString()
                    tvEarlyTimes.text=earlyTimes.toString()
                    tvLeaveTimes.text=leaveTimes.toString()
                }

                checkViewModel.signInList.clear()
                checkViewModel.signInList.addAll(signInList)
                listAdapter= CheckListAdapter(this,checkViewModel.signInList)
                binding.rvCheck.adapter=listAdapter
                listAdapter.notifyDataSetChanged()
            }else{
                "无考勤记录".showToast()
            }
        })


        lifecycle.addObserver(MyObserver(TAG))
    }

    override fun onResume() {
        super.onResume()
        val courseId=intent.getStringExtra("courseId")!!
        LogUtil.d(TAG,courseId)
        refresh(courseId)
    }

    private fun refresh(courseId: String){
        checkViewModel.refresh(courseId)
    }

    companion object{
        fun startActivity(context: Context, courseId:String){
            val intent=Intent(context,CheckWorkAttendanceActivity::class.java).apply {
                putExtra("courseId",courseId)
            }
            context.startActivity(intent)
        }
    }

}