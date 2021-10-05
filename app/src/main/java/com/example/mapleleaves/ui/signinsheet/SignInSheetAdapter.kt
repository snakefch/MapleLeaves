package com.example.mapleleaves.ui.signinsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapleleaves.R
import com.example.mapleleaves.logic.model.response.GetSignInByTeacherResponse

class SignInSheetAdapter (private val signInSheetActivity: SignInSheetActivity,private val signInListSheet:List<GetSignInByTeacherResponse.SignInRecord>):
    RecyclerView.Adapter<SignInSheetAdapter.ViewHolder>(){

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val datetime:TextView=view.findViewById(R.id.tv_datetime)
        val name:TextView=view.findViewById(R.id.tv_name)
        val state:TextView=view.findViewById(R.id.tv_state)
        val signInCode:TextView=view.findViewById(R.id.tv_sign_in_code)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sign_in_sheet_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val signInsheet=signInListSheet[position]
        holder.datetime.text=signInsheet.datetime
        holder.name.text=signInsheet.name
        holder.state.text=signInsheet.state
        holder.signInCode.text=signInsheet.signInCode
        if(holder.state.text=="on"){
            holder.state.setOnClickListener {
                signInSheetActivity.setSignInId(signInsheet.id)
            }
        }
    }

    override fun getItemCount()=signInListSheet.size

}