package com.flagos.directory.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.flagos.directory.R
import com.flagos.directory.databinding.EmployeeItemBinding
import com.flagos.directory.domain.model.EmployeeItem
import com.squareup.picasso.Picasso

class EmployeeViewHolder(private val binding: EmployeeItemBinding) : RecyclerView.ViewHolder(binding.root) {

    private var picassoInstance: Picasso = Picasso.get()

    init {
        picassoInstance.setIndicatorsEnabled(true)
    }

    fun bind(employee: EmployeeItem) {
        with(binding) {
            textEmployeeName.text = employee.fullName
            textEmployeeEmail.text = employee.emailAddress
            textEmployeeBiography.text = employee.biography
            textEmployeeType.text = employee.employeeType
            textEmployeeTeam.text = employee.team
            textEmployeePhoneNumber.text = employee.phoneNumber

            picassoInstance
                .load(employee.photoUrlSmall)
                .error(R.drawable.ic_placeholder)
                .placeholder(R.drawable.ic_placeholder)
                .fit()
                .centerCrop()
                .into(imageEmployee)
        }
    }
}