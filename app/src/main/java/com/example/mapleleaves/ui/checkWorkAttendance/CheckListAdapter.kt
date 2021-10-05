package com.example.mapleleaves.ui.checkWorkAttendance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapleleaves.R
import com.example.mapleleaves.logic.model.response.GetSignInByStudentResponse
import com.example.mapleleaves.ui.studentsignin.StudentSignInActivity

class CheckListAdapter(private val checkWorkAttendanceActivity: CheckWorkAttendanceActivity,private val studentSignInList:List<GetSignInByStudentResponse.StudentSignInRecord>) :
    RecyclerView.Adapter<CheckListAdapter.ViewHolder>() {

        inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
            val signInName:TextView=view.findViewById(R.id.tv_sign_in_name)
            val datetime:TextView=view.findViewById(R.id.tv_datetime)
            val result:TextView=view.findViewById(R.id.tv_result)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.check_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val signInRecord=studentSignInList[position]
        holder.signInName.text=signInRecord.signInName
        holder.datetime.text=signInRecord.datetime
        holder.result.text=signInRecord.result
        if (signInRecord.state=="on"&&signInRecord.result!="出勤"){
            holder.result.text="签到"
            holder.result.setOnClickListener {
                StudentSignInActivity.startActivity(checkWorkAttendanceActivity,signInRecord.studentId,
                    signInRecord.courseId,signInRecord.signInId)
            }
        }
    }

    override fun getItemCount()=studentSignInList.size
}