package com.example.shoppinglist.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.shoppinglist.databinding.DeleteDialogBinding

object DeleteDialog {
    fun showDialog(context: Context, listener: Listener){
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = DeleteDialogBinding.inflate(LayoutInflater.from(context))//7.35 здесь остановка
        builder.setView(binding.root)

        binding.apply {
            bDelete.setOnClickListener{
                listener.onClick()
                dialog?.dismiss()
            }
            bCancel.setOnClickListener{
                dialog?.dismiss()
            }
        }

        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }

    interface Listener{
        fun onClick()
    }
}