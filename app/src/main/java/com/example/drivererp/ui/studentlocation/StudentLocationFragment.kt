package com.example.drivererp.ui.studentlocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.drivererp.R
import com.example.drivererp.data.Student


class StudentsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_location, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = StudentsAdapter(getStudentsList())

        return view
    }

    private fun getStudentsList(): List<Student> {
        // Replace this with your data fetching logic
        val students = listOf(
            Student("Mahesh", "8th Std"),
            Student("Shruthi", "6th Std"),
            Student("Devi Prasad", "5th Std")
        )
        return students
    }
}