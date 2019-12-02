package com.passwd.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.passwd.R
import kotlinx.android.synthetic.main.activity_main.recyclerView
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()
    val adapter: MainRecyclerAdapter by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler()
        observeList()
    }

    private fun setupRecycler() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeList() {
        viewModel
            .passwordList
            .observe(this, Observer {
                adapter.setPasswords(it)
                adapter.notifyDataSetChanged()
            })
    }
}
