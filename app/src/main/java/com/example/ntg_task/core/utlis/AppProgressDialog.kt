package com.example.ntg_task.core.utlis

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import com.example.ntg_task.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppProgressDialog @Inject constructor() {
    private lateinit var dialog: Dialog

    private fun createDialog(context: Context) {
        dialog = Dialog(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.layout_loading, null)
        dialog.setContentView(inflate)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
    }

    fun show(context: Context) {
        if (!::dialog.isInitialized) {
            createDialog(context)
        }
        dialog?.show()

    }

    fun dismiss() {
        if (::dialog.isInitialized) {
            dialog.dismiss()
        }

    }
}