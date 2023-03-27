package com.example.presentation.viewBindingExtensions

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import androidx.recyclerview.widget.RecyclerView

fun View.enableClick(clickable: Boolean) {
    isClickable = clickable
    if (this is RecyclerView) {
        forEachIndexed { index, view -> view.isEnabled = clickable }
    }
    else if (this is ViewGroup) children.forEach { child -> child.enableClick(clickable) }
}

fun View.enableView(isEnable: Boolean) {
    this.isEnabled = isEnable
    if (this is ViewGroup) children.forEach { child -> child.enableView(isEnable) }
}

fun View.showLoading(showLoading: Boolean) {
    this.visibility = showLoading.toVisibility()
}

fun Boolean.toVisibility() = when (this) {
    true -> View.VISIBLE
    false -> View.GONE
}