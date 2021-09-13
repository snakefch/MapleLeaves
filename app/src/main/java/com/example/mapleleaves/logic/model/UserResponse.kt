package com.example.mapleleaves.logic.model

import com.google.gson.annotations.SerializedName

data class UserResponse (val code:String,val msg:String,val data:Data){
    //定义在里面可以防止同名类冲突
    data class Data(val id:String, @SerializedName("username") val userName:String,
                    @SerializedName("password") val passWord:String,val name:String,val gender:String)
}

