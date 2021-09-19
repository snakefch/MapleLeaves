package com.example.mapleleaves.logic.model

data class CoursesAttendedResponse(val code:String,val msg:String,val data:List<Data>){

    data class Data(val id:String,val name:String,val introduction:String,val teacherId:String,val teacher:String,
                    val addClassCode:String,val number:Int,val ceiling:Int)

}
