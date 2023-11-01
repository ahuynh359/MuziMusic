package com.team15.muzimusic.base.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.team15.muzimusic.R
import com.team15.muzimusic.databinding.DialogErrorBinding

class ErrorDialog(
    context: Context,
    private val errorContent: String,
    private val textButton: String? = null
) : Dialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootView = layoutInflater.inflate(R.layout.dialog_error, null, false)

        val tvContentError = rootView.findViewById<TextView>(R.id.tv_content_error)
        tvContentError.text = errorContent

        val btnOK = rootView.findViewById<Button>(R.id.btn_ok)
        textButton?.let {
            btnOK.text = textButton
        }

        btnOK.setOnClickListener {
            dismiss()
        }
        setContentView(rootView)

    }
}