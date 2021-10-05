package com.example.mapleleaves.ui.signindetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapleleaves.R
import com.example.mapleleaves.logic.model.response.GetStudentSignInByTeacherResponse

class SignInDetailsAdapter(private val signInList:List<GetStudentSignInByTeacherResponse.StudentSignInRecord>):RecyclerView.Adapter<SignInDetailsAdapter.ViewHolder>(){

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val studentId:TextView=view.findViewById(R.id.tv_student_id)
        val studentName:TextView=view.findViewById(R.id.tv_student_name)
        val signInName:TextView=view.findViewById(R.id.tv_sign_in_name)
        val datetime:TextView=view.findViewById(R.id.tv_datetime)
        val location:TextView=view.findViewById(R.id.tv_location)
        val result:TextView=view.findViewById(R.id.tv_result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.sign_in_details_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val oneSignInRecord=signInList[position]
        holder.studentId.text=oneSignInRecord.studentId
        holder.studentName.text=oneSignInRecord.studentName
        holder.signInName.text=oneSignInRecord.signInName
        holder.datetime.text=oneSignInRecord.datetime
        holder.result.text=oneSignInRecord.result
    }

    override fun getItemCount()=signInList.size
}