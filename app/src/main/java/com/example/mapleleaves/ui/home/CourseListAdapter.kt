package com.example.mapleleaves.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mapleleaves.R
import com.example.mapleleaves.logic.model.Course

class CourseListAdapter (private val fragment: HomeFragment, private val courseList: List<Course>):
    RecyclerView.Adapter<CourseListAdapter.ViewHolder>(){

        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val courseName: TextView =view.findViewById(R.id.courseName)
            val courseDesc: TextView =view.findViewById(R.id.courseDesc)
            val studentCount:TextView=view.findViewById(R.id.studentCount)
            val otherButton:ImageView=view.findViewById(R.id.otherButton)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.course_item,parent,false)
        val holder=ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course=courseList[position]
        holder.courseName.text=course.courseName
        holder.courseDesc.text=course.courseDesc
        holder.studentCount.text= course.studentcount.toString()+"人"
        holder.otherButton.setOnClickListener {
            //使用safeargs
            it.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToCourseManagementDialogFragment())
        }
    }

    override fun getItemCount()=courseList.size

}