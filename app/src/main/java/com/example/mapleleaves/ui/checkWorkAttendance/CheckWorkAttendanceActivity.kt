package com.example.mapleleaves.ui.checkWorkAttendance

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.R
import com.example.mapleleaves.databinding.ActivityCheckWorkAttendanceBinding
import com.example.mapleleaves.logic.model.Check

class CheckWorkAttendanceActivity : AppCompatActivity() {

    private val checkViewModel by lazy { ViewModelProviders.of(this).get(CheckViewModel::class.java) }

    lateinit var binding: ActivityCheckWorkAttendanceBinding
    lateinit var listAdapter: CheckListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_check_work_attendance)
        binding= ActivityCheckWorkAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager=LinearLayoutManager(this)
        binding.rvCheck.layoutManager=layoutManager
        listAdapter= CheckListAdapter(checkViewModel.checkList)
        binding.rvCheck.adapter=listAdapter
        checkViewModel.checkList.add(Check("标题1"))
        checkViewModel.checkList.add(Check("标题2"))
        checkViewModel.checkList.add(Check("标题3"))
        listAdapter.notifyDataSetChanged()

    }

    companion object{
        fun startActivity(context: Context){
            val intent=Intent(context,CheckWorkAttendanceActivity::class.java)
            context.startActivity(intent)
        }
    }

}