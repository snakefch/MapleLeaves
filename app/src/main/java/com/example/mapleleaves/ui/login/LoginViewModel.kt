package com.example.mapleleaves.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mapleleaves.logic.Repository
import com.example.mapleleaves.logic.model.User

class LoginViewModel:ViewModel() {

    private val loginLiveData= MutableLiveData<User>()

    val userLiveData= Transformations.switchMap(loginLiveData){ user->
        Repository.postLogin(user.userName,user.password)
    }

    fun postLogin(userName:String,password:String){
        loginLiveData.value=User(userName=userName,password=password)
    }

    fun getUser()=Repository.getUser()

    fun getRememberPassword()=Repository.getRememberPassword()

    fun saveRememberPassword(boolean: Boolean)=Repository.saveRememberPassword(boolean)

    fun saveUser(user: User)=Repository.saveUser(user)

}