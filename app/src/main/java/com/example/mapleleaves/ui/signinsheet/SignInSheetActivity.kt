package com.example.mapleleaves.ui.signinsheet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapleleaves.databinding.ActivitySignInSheetBinding
import com.example.mapleleaves.logic.model.CoursesAttendedResponse
import com.example.mapleleaves.ui.startsignin.StartSignInActivity
import com.example.mapleleaves.utils.LogUtil
import com.example.mapleleaves.utils.showToast
import com.google.gson.Gson

class SignInSheetActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySignInSheetBinding

    private val signInSheetViewModel by lazy { ViewModelProviders.of(this).get(SignInSheetViewModel::class.java) }

    private lateinit var adapter:SignInSheetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignInSheetBinding.inflate(layoutInflater)

        val layoutManager= LinearLayoutManager(this)
        binding.rvSignInSheet.layoutManager=layoutManager

        val courseId=intent.getStringExtra("courseId")
        if (courseId != null) {
            LogUtil.d("接收的id","$courseId")
            signInSheetViewModel.setCourseIdLiveData(courseId)
        }

        signInSheetViewModel.resultLiveData.observe(this, Observer {
            val signInSheetList=it.getOrNull()
            if (signInSheetList!=null&&(signInSheetList.toString()!="[]")){
                signInSheetViewModel.signInRecodeList.clear()
                signInSheetViewModel.signInRecodeList.addAll(signInSheetList)
                adapter= SignInSheetAdapter(signInSheetViewModel.signInRecodeList)
                binding.rvSignInSheet.adapter=adapter
                adapter.notifyDataSetChanged()
            }else{
                "签到记录为空".showToast()
            }
        })

        setContentView(binding.root)
    }

    companion object{
        fun actionStart(context: Context,courseId:String){
            val intent= Intent(context, SignInSheetActivity::class.java).apply {
                putExtra("courseId", courseId)
            }
            context.startActivity(intent)
        }
    }
}