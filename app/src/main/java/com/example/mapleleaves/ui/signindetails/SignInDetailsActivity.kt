package com.example.mapleleaves.ui.signindetails

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.databinding.ActivitySignInDetailsBinding
import com.example.mapleleaves.ui.signinsheet.SignInSheetActivity
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.showToast

class SignInDetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignInDetailsBinding

    private val viewModel by lazy { ViewModelProviders.of(this).get(SignInDetailsViewModel::class.java) }

    private lateinit var adapter: SignInDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager=LinearLayoutManager(this)
        binding.rvSignInDetails.layoutManager=layoutManager

        viewModel.resultLiveData.observe(this, Observer {result->
            val signInList=result.getOrNull()
            if (signInList!=null){
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

                viewModel.signInList.clear()
                viewModel.signInList.addAll(signInList)
                adapter= SignInDetailsAdapter(viewModel.signInList)
                binding.rvSignInDetails.adapter=adapter
                adapter.notifyDataSetChanged()
            }else{
                "签到记录为空".showToast()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val signInId=intent.getStringExtra("signInId")!!
        LogUtil.d("接收的signInId","$signInId")
        refresh(signInId)
    }

    fun refresh(signInId: String){
        viewModel.refresh(signInId)
    }

    companion object{
        fun actionStart(context: Context, signInId:String){
            val intent= Intent(context, SignInDetailsActivity::class.java).apply {
                putExtra("signInId", signInId)
            }
            context.startActivity(intent)
        }
    }

}