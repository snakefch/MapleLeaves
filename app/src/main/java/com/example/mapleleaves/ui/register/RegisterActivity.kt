package com.example.mapleleaves.ui.register

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityRegisterBinding
import com.example.mapleleaves.logic.model.body.RegisterBody
import com.example.mapleleaves.utils.showToast

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding

    private val viewModel by lazy { ViewModelProviders.of(this).get(RegisterViewModel::class.java) }

    companion object{
        fun startActivity(context: Context){
            val intent=Intent(context,RegisterActivity::class.java)
            context.startActivity(intent)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding= ActivityRegisterBinding.inflate(layoutInflater)

        val decorView=window.decorView
        decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // window.setDecorFitsSystemWindows(false)
        window.statusBarColor= Color.TRANSPARENT

        binding.btRegister.setOnClickListener {
            val userName=binding.etUserName.text.toString()
            val name=binding.etName.text.toString()
            val passwordFirst=binding.etPasswordFirst.text.toString()
            val passwordSecond=binding.etPasswordSecond.text.toString()
            if ((userName!=null&&userName.trim().isNotEmpty())&&(passwordFirst!=null&&passwordFirst.trim().isNotEmpty())
                &&(passwordSecond!=null&&passwordSecond.trim().isNotEmpty())&&passwordFirst==passwordSecond) {
                viewModel.refresh(
                    RegisterBody(
                        userName, passwordFirst,
                        name, "男", "NA", "NA"
                    )
                )
            }else{
                return@setOnClickListener
            }
        }

        viewModel.resultLiveData.observe(this, Observer {
            val code=it.getOrNull()
            if (code!=null){
                "注册成功".showToast()
            }else{
                "注册失败".showToast()
            }
            finish()
        })

        setContentView(binding.root)
    }
}