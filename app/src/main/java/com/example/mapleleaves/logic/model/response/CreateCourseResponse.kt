package com.example.mapleleaves.logic.model.response

data class CreateCourseResponse(val code:String, val msg:String, val data: Data){

    data class Data(val id:String,val name:String,val introduction:String,val teacherId:String,val teacher:String,
                    val ceiling:Int)
}