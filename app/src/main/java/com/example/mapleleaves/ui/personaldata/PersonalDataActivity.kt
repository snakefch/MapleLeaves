package com.example.mapleleaves.ui.personaldata

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.mapleleaves.databinding.ActivityPersonalDataBinding
import com.example.mapleleaves.ui.signindetails.SignInDetailsActivity

class PersonalDataActivity : AppCompatActivity(){

    private lateinit var binding: ActivityPersonalDataBinding

    private val viewModel by lazy { ViewModelProviders.of(this).get(PersonalDataViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user=viewModel.getUser()
        binding.apply {
            userName.text=user.userName
            name.text=user.name
            gender.text=user.gender
            college.text=user.college
            academy.text=user.academy
        }


    }

    companion object{
        fun actionStart(context: Context){
            val intent= Intent(context, PersonalDataActivity::class.java).apply {
            }
            context.startActivity(intent)
        }
    }
}