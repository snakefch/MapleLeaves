package com.example.mapleleaves.ui.register

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mapleleaves.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding:ActivityRegisterBinding

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

        setContentView(binding.root)
    }
}