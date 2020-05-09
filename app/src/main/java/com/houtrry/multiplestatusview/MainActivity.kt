package com.houtrry.multiplestatusview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvRelativeLayout.setOnClickListener { goToAty(RelativeLayoutActivity::class.java) }
    }

    private fun goToAty(cls: Class<out Activity>) {
        startActivity(Intent(this, cls))
    }
}
