package com.example.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.presentation.R


class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar= findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun showToolbar(title: String, navigationIcon: Boolean){
        if (navigationIcon){
            toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener(View.OnClickListener {
                onBackPressedDispatcher.onBackPressed()
            })
        }else{
            toolbar.navigationIcon = null
        }
        toolbar.title = title
    }
}