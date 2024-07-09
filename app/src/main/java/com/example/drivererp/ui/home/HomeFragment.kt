package com.example.drivererp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivererp.R
import com.example.drivererp.data.HomeItemModel
import com.example.drivererp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.home.layoutManager = LinearLayoutManager(requireContext())

        val data = ArrayList<HomeItemModel>()
        for (i in 1..20) {
            data.add(HomeItemModel("Location", "6 km", "9:45 AM"))
        }

        val adapter = HomeAdapter(data, this)
        binding.home.adapter = adapter

        // Set click listener for floatingIcon
        binding.floatingIcon.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapFragment)
        }

        return root
    }

    override fun onItemClick(position: Int) {
        // Handle item click and navigate to the new Fragment
        findNavController().navigate(R.id.action_homeFragment_to_newFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
