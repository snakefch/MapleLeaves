package com.example.mapleleaves.logic.model.body

//开始签到请求Body

data class StartCheckInBody (val name:String,val courseId:String,val courseName:String,val teacherId:String,
                        val teacherName:String,val totalNumber:Int)