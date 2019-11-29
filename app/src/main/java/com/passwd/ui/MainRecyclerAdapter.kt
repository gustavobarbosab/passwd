package com.passwd.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.passwd.databinding.ItemRecyclerMainBinding

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    private var passwords: MutableList<MainFields> = mutableListOf()

    fun putData(passwords: List<MainFields>) {
        with(this.passwords) {
            clear()
            addAll(passwords)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecyclerMainBinding = ItemRecyclerMainBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = passwords.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fields = passwords[position]
        holder.setupFields(fields)
    }

    inner class ViewHolder(private val binding: ItemRecyclerMainBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun setupFields(fields: MainFields) {
            binding.fields = fields
        }
    }
}