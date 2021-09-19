package com.example.mapleleaves.logic.model

import com.google.gson.annotations.SerializedName

data class User(val id:String="", @SerializedName("username") val userName:String,
                @SerializedName("password") val password:String, val name:String="", val gender:String="")
