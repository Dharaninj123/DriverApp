package com.example.drivererp.ui.studentlocation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.drivererp.R
import com.example.drivererp.data.Student


class StudentsAdapter(private val students: List<Student>) :
    RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serialNumberTextView: TextView = itemView.findViewById(R.id.text_view_serial_number)
        val studentNameTextView: TextView = itemView.findViewById(R.id.text_view_student_name)
        val classTextView: TextView = itemView.findViewById(R.id.text_view_class)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.studentitem, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.serialNumberTextView.text = (position + 1).toString()
        holder.studentNameTextView.text = student.name
        holder.classTextView.text = student.className
    }

    override fun getItemCount(): Int {
        return students.size
    }
}