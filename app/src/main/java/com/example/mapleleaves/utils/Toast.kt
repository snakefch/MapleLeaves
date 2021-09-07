package com.example.mapleleaves.utils

import android.widget.Toast
import com.example.mapleleaves.MapleLeavesApplication

fun String.showToast(duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(MapleLeavesApplication.context,this,duration).show()
}

fun Int.showToast(duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(MapleLeavesApplication.context,this,duration).show()
}

