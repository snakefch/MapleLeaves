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
import com.example.mapleleaves.logic.model.response.CoursesAttendedResponse
import com.example.mapleleaves.ui.course.StudentCourseActivity
import com.example.mapleleaves.ui.course.TeacherCourseActivity

class CourseListAdapter (private val fragment: Fragment, private val courseList: List<CoursesAttendedResponse.Data>, private val userId:String=""):
    RecyclerView.Adapter<CourseListAdapter.ViewHolder>(){

        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
            val courseName: TextView =view.findViewById(R.id.tv_course_name)
            val courseCode: TextView =view.findViewById(R.id.tv_course_code)
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
        holder.courseCode.text=course.id
        holder.studentCount.text= course.number.toString()+"人"
        holder.otherButton.setOnClickListener {
            //使用safeargs
            //在使用safeargs的2.3.5和2.4.0-alpha01传递参数时，不知从何而来，构建正在崩溃与导航组件相关的一个奇怪的错误，
            // 即使它以前工作过，错误是在生成的类HomeFragmentDirections,更新safeArg插件到2.4.0-alpha04，摆脱错误消息。
           // it.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToCourseManagementDialogFragment(course.id.toLong(),2))

            if (fragment is StudyFragment) it.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToCourseManagementDialogFragment(course.id.toLong(),1,userId.toLong()))
            else it.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToCourseManagementDialogFragment(course.id.toLong(),2))

        }
        holder.itemView.setOnClickListener {
            fragment.context?.let { context ->
                //将判断放到里面可以减少重复判断fragment.context?
                if (fragment is StudyFragment) StudentCourseActivity.startCourseActivity(context,course)
                else TeacherCourseActivity.startTeacherCourseActivity(context,course)
            }
        }
    }

    override fun getItemCount()=courseList.size

}