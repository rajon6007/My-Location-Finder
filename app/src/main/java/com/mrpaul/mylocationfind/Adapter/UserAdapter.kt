package com.mrpaul.mylocationfind.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrpaul.mylocationfind.Model.User02
import com.mrpaul.mylocationfind.databinding.ItemUser02Binding

class UserAdapter (private var userList: List<User02>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(private val binding: ItemUser02Binding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: User02){
            binding.apply {
                nameTxt.text = user.displayname
                emailTxt.text = user.email
                locationTxt.text = user.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUser02Binding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.bind(user)

    }
    fun updateData(newList: List<User02>) {
        userList = newList
        notifyDataSetChanged()
    }
}