package com.example.mapleleaves.logic.model

data class CoursesAttendedResponse(val code:String,val msg:String){

    data class Data(val courseName:String)

}
