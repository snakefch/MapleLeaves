package com.example.mapleleaves.ui.signinsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mapleleaves.R
import com.example.mapleleaves.logic.model.response.GetSignInByTeacherResponse
import com.example.mapleleaves.ui.signindetails.SignInDetailsActivity

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
        val oneSignInRecord=signInListSheet[position]
        holder.datetime.text=oneSignInRecord.datetime
        holder.name.text=oneSignInRecord.name
        //holder.state.text=oneSignInRecord.state
        holder.signInCode.text=oneSignInRecord.signInCode
        if(oneSignInRecord.state=="on"){
            holder.state.text="停止"
            holder.state.setOnClickListener {
                signInSheetActivity.setSignInId(oneSignInRecord.id)
            }
        }else{
            holder.state.text="已停止"
        }
        holder.itemView.setOnClickListener {
            SignInDetailsActivity.actionStart(signInSheetActivity,oneSignInRecord.id)
        }
    }

    override fun getItemCount()=signInListSheet.size

}