package com.example.mapleleaves.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mapleleaves.R
import com.example.mapleleaves.logic.model.Course
import com.example.mapleleaves.logic.model.CoursesAttendedResponse
import com.example.mapleleaves.ui.course.CourseActivity

class CourseListAdapter (private val fragment: Fragment, private val courseList: List<CoursesAttendedResponse.Data>):
    RecyclerView.Adapter<CourseListAdapter.ViewHolder>(){

        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val courseName: TextView =view.findViewById(R.id.tv_course_name)
            val courseDesc: TextView =view.findViewById(R.id.tv_course_desc)
            val studentCount:TextView=view.findViewById(R.id.tv_student_count)
            val otherButton:ImageView=view.findViewById(R.id.iv_other_button)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.course_item,parent,false)
        val holder=ViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course=courseList[position]
        holder.courseName.text=course.name
        holder.courseDesc.text=course.introduction
        holder.studentCount.text= course.number.toString()+"人"
        holder.otherButton.setOnClickListener {
            //使用safeargs
            it.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToCourseManagementDialogFragment())
        }
        holder.itemView.setOnClickListener {
            fragment.context?.let { context -> CourseActivity.startCourseActivity(context,course) }
        }
    }

    override fun getItemCount()=courseList.size

}