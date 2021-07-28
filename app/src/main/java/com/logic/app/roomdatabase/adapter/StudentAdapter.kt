package com.logic.app.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.logic.app.roomdatabase.database.Student
import com.logic.app.roomdatabase.databinding.ItemStudentBinding

class StudentAdapter(
    private val listStudent: List<Student>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>() {

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = listStudent.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            binding.id.text = listStudent[position].uid.toString()
            binding.name.text = listStudent[position].name
            binding.phone.text = listStudent[position].phone.toString()
            binding.root.setOnClickListener(this)
        }
    }


    inner class MyViewHolder(val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                listener.onItemClickStudent(listStudent[position],position)


        }
    }


    interface OnItemClickListener {
        fun onItemClickStudent(student: Student,position: Int)
    }
}