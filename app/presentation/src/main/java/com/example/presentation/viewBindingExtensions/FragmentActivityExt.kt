package com.example.presentation.viewBindingExtensions

import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.alert(@StringRes title: Int, @StringRes message: Int) {
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    builder.setMessage(message).setTitle(title)
    val dialog: AlertDialog = builder.create()
    dialog.show()
}