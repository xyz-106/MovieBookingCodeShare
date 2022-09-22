package com.jshelf.themoviebookingapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.jshelf.themoviebookingapp.adapters.InternalAdapter
import com.jshelf.themoviebookingapp.delegates.TimeDelegate
import com.jshelf.themoviebookingapp.R
import kotlinx.android.synthetic.main.external_rc_viewholder.*

class ExternalActivity(private val mDelegate:TimeDelegate): AppCompatActivity() {
    lateinit var internalAdapter: InternalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.external_rc_viewholder)
        setUpInternalRcView()

    }

    private fun setUpInternalRcView() {
        internalAdapter = InternalAdapter(mDelegate)
        internalRc.adapter = internalAdapter
        internalRc.layoutManager =
            GridLayoutManager(applicationContext, 3, GridLayoutManager.VERTICAL, false)

    }


}