package com.shubham.womensafety.guardiandetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.womensafety.R
import com.shubham.womensafety.database.Guardian
import kotlinx.android.synthetic.main.list_view.view.*


class GuardianAdapter(val guardians: List<Guardian>)
    : RecyclerView.Adapter<GuardianAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : GuardianAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: GuardianAdapter.ViewHolder, position: Int) {
        holder.name.text = guardians[position].guardianName
        holder.relation.text = guardians[position].guardianEmail
        holder.phone.text = guardians[position].guardianPhoneNo
        holder.email.text = guardians[position].guardianRelation

    }

    override fun getItemCount(): Int {
        return guardians.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val name = itemView.textName
        val relation = itemView.textRelation
        val phone = itemView.textPhone
        val email = itemView.textEmail

    }

}