package com.akriti.meeshoapp.view

import android.os.Bundle
import com.akriti.meeshoapp.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchFragment.newInstance())
                    .commitNow()
        }
    }
}