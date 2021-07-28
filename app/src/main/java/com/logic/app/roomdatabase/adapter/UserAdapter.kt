package com.logic.app.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logic.app.roomdatabase.database.User
import com.logic.app.roomdatabase.databinding.ItemUserBinding

class UserAdapter(
    private val listUser: List<User>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            binding.address.text = listUser[position].address
            binding.country.text = listUser[position].country
            binding.id.text = listUser[position].id.toString()
            binding.root.setOnClickListener(this)
        }
    }

    override fun getItemCount(): Int = listUser.size

    inner class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClickUser(listUser[position],position)

        }
    }

}

interface OnItemClickListener {
    fun onItemClickUser(user: User,position: Int)
}
