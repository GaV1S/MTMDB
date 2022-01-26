package com.gav1s.mtmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gav1s.mtmdb.framework.ui.main_fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        savedInstanceState ?: run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}