package com.passwd.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.passwd.R
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    // TODO injetar depois
    private val adapter = MainRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupListMock()
    }

    private fun setupListMock() {
        val list = mutableListOf<MainFields>()

        repeat(30) {
            list.add(MainFields(1, "teste", "123123"))
        }

        adapter.putData(list)
        adapter.notifyDataSetChanged()
    }
}
