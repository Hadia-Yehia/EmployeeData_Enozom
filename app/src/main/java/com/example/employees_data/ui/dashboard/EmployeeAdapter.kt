package com.example.employees_data.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.employees_data.databinding.EmployeeCardBinding
import com.example.employees_data.model.EmployeeModel

class EmployeeAdapter():
    ListAdapter<EmployeeModel, EmployeeAdapter.EmployeeViewHolder>(EmployeeDiffUtil()) {
    lateinit var context: Context
    lateinit var binding: EmployeeCardBinding

    inner class EmployeeViewHolder(var binding: EmployeeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        context = parent.context
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = EmployeeCardBinding.inflate(inflater, parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentObj = getItem(position)
        holder.binding.empName.text = currentObj.name
        holder.binding.empEmail.text = currentObj.Email

//        holder.binding.addToFav.setOnClickListener {onFavClick(currentObj)
//
//        }
    }
}
class EmployeeDiffUtil: DiffUtil.ItemCallback<EmployeeModel>(){
    override fun areItemsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
        return (oldItem.id==newItem.id)
    }

    override fun areContentsTheSame(oldItem: EmployeeModel, newItem: EmployeeModel): Boolean {
        return oldItem==newItem
    }

}