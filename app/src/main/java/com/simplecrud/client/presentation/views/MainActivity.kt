package com.simplecrud.client.presentation.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplecrud.base.presentation.BaseActivity
import com.simplecrud.client.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}