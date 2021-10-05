package com.example.mapleleaves.logic.model.response

import com.example.mapleleaves.logic.model.User

data class UserResponse (val code:String,val msg:String,val data: User){
    //定义在里面可以防止同名类冲突
   /* data class User(val id:String, @SerializedName("username") val userName:String,
                    @SerializedName("password") val password:String, val name:String, val gender:String)*/
}

