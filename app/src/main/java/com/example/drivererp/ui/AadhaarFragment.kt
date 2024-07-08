package com.example.drivererp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.drivererp.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AadhaarFragment : Fragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String?, param2: String?) = AadhaarFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aadhaar, container, false)
    }

    private fun shareFile(context: Context, fileToShare: File?) {
        fileToShare?.let {
            val contentUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                it
            )
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "*/*"
                putExtra(Intent.EXTRA_STREAM, contentUri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share File"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.aadharmenu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @SuppressLint("WrongThread")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_share -> {
                val bitmap = BitmapFactory.decodeResource(
                    requireContext().resources,
                    R.drawable.driveraadhaar
                )
                val outputFile = File(requireContext().cacheDir, "fileName.png")
                try {
                    FileOutputStream(outputFile).use { outputStream ->
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                        outputFile.setReadable(true, false)
                        shareFile(requireContext(), outputFile)
                    }
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }
                true
            }
            R.id.nav_download -> {
                NavHostFragment.findNavController(this).navigate(R.id.nav_aadhaar)
                true
            }
            R.id.nav_print -> {
                NavHostFragment.findNavController(this).navigate(R.id.nav_print)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareDocument() {
        val file = File(requireContext().filesDir, "Aadhaar.pdf")
        val uri = FileProvider.getUriForFile(
            requireContext(),
            "com.example.drivererp.provider",
            file
        )
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Share document via"))
    }
}
