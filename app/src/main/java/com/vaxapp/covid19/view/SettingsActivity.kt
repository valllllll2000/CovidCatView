package com.vaxapp.covid19.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vaxapp.covid19.R
import kotlinx.android.synthetic.main.app_bar.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
    }
}
