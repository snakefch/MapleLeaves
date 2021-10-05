package com.example.mapleleaves.logic.model

import com.google.gson.annotations.SerializedName

data class User(val id:String="", @SerializedName("username") val userName:String,
                @SerializedName("password") val password:String, val name:String="", val gender:String="男",val college:String="桂林电子科技大学",val academy:String="2018")
