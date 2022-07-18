package com.flagos.directory.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.flagos.directory.databinding.EmployeeItemBinding
import com.flagos.directory.domain.model.EmployeeItem
import com.flagos.directory.presentation.adapter.viewholder.EmployeeViewHolder

class EmployeeAdapter : ListAdapter<EmployeeItem, EmployeeViewHolder>(EmployeeDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EmployeeViewHolder(EmployeeItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    private class EmployeeDiff : DiffUtil.ItemCallback<EmployeeItem>() {
        override fun areItemsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: EmployeeItem, newItem: EmployeeItem): Boolean = oldItem.uuid == newItem.uuid
    }
}
