package com.example.drivererp.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.drivererp.R
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.io.FileOutputStream

class DocumentFragment : Fragment() {

    private var imageFileIcon: ImageView? = null
    private var editTextFileName: TextInputEditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_document, container, false)
        imageFileIcon = view.findViewById(R.id.image_file_icon)
        editTextFileName = view.findViewById(R.id.editTextFileName)
        imageFileIcon?.setOnClickListener { openFileChooser() }
        return view
    }

    private fun openFileChooser() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_FILE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val fileUri = data.data
            if (fileUri != null) {
                val fileName = getFileName(fileUri)
                editTextFileName?.setText(fileName)
                saveFileToInternalStorage(fileUri, fileName)
                Toast.makeText(context, "File Selected: $fileName", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getFileName(uri: Uri): String {
        var fileName = ""
        val contentResolver = requireContext().contentResolver
        if (uri.scheme == "content") {
            try {
                contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val index = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (index != -1) {
                            fileName = cursor.getString(index)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("DocumentFragment", "Error retrieving file name", e)
            }
        } else {
            fileName = File(uri.path ?: "").name
        }
        return fileName
    }

    private fun saveFileToInternalStorage(fileUri: Uri, fileName: String) {
        val targetFile = File(requireContext().filesDir, fileName)
        try {
            requireContext().contentResolver.openInputStream(fileUri)?.use { inputStream ->
                FileOutputStream(targetFile).use { outputStream ->
                    val buffer = ByteArray(1024)
                    var bytesRead: Int
                    while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                        outputStream.write(buffer, 0, bytesRead)
                    }
                    outputStream.flush()
                    Toast.makeText(
                        context,
                        "File saved to internal storage",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (e: Exception) {
            Log.e("DocumentFragment", "Error saving file", e)
            Toast.makeText(context, "Failed to save file", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val PICK_FILE_REQUEST_CODE = 1
    }
}
