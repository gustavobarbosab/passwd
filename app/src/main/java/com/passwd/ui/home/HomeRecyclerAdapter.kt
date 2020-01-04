package com.passwd.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.passwd.databinding.ItemRecyclerMainBinding
import com.passwd.ui.home.model.HomeItemPassword

class HomeRecyclerAdapter : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    private var passwords: MutableList<HomeItemPassword> = mutableListOf()

    fun setPasswords(passwords: List<HomeItemPassword>) {
        with(this.passwords) {
            clear()
            addAll(passwords)
        }
    }

    fun getPassword(position: Int) = passwords[position]

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
        fun setupFields(password: HomeItemPassword) {
            binding.fields = password
        }
    }
}