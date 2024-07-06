package com.example.drivererp.ui.route

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivererp.R
import com.example.drivererp.data.RouteItemModel
import com.example.drivererp.databinding.FragmentRouteBinding

class RouteFragment : Fragment() {

    private var _binding: FragmentRouteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(RouteViewModel::class.java)

        _binding = FragmentRouteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.route.layoutManager = LinearLayoutManager(requireContext())

        // ArrayList of class ItemsViewModel
        val data = ArrayList<RouteItemModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(RouteItemModel("Location","12 km","9:45"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = RouteAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.route.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}