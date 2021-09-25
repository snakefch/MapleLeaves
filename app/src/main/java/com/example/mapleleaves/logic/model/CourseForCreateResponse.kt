package com.example.mapleleaves.logic.model

data class CourseForCreateResponse(val code:String,val data:Data){

    data class Data(val id:String,val name:String,val introduction:String,val teacherId:String,val teacher:String,
                    val ceiling:Int)
}