package com.example.presentation.viewBindingExtensions

import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(this, T::class.java)

fun Fragment.alert(dialogBuilder: AlertDialog.Builder.() -> Unit): AlertDialog {
    val builder = AlertDialog.Builder(requireContext())
    builder.dialogBuilder()
    return builder.create()
}

fun Fragment.requestPermission(permissions: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(requireActivity(), permissions, requestCode)
}