package com.houtrry.multiplestatusview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_relative_layout.*

class RelativeLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relative_layout)
        tvData.setOnClickListener {
            multipleStatusView.showLoadingView(false)
            multipleStatusView.addCustomTypeView(5, R.layout.layout_custom_type, false);
            val handle = Handler()
            handle.postDelayed({
                multipleStatusView.showNotLoginView()
            }, 1500)
            handle.postDelayed({
                multipleStatusView.showEmptyView()
            }, 3000)
            handle.postDelayed({
                multipleStatusView.showErrorView()
            }, 5000)
            handle.postDelayed({
                multipleStatusView.showCustomTypeView(5)
            }, 7000)
            handle.postDelayed({
                multipleStatusView.showDataView()
            }, 9000)
        }
    }
}
