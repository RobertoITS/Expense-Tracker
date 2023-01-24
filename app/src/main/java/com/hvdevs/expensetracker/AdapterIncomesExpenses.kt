package com.hvdevs.expensetracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hvdevs.expensetracker.databinding.RecyclerIncomesExpensesCardBinding

class AdapterIncomesExpenses(private var list: ArrayList<DataClassIncomesExpenses>): RecyclerView.Adapter<AdapterIncomesExpenses.ViewHolderIncomesExpenses>() {
    class ViewHolderIncomesExpenses (val binding: RecyclerIncomesExpensesCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderIncomesExpenses {
        val binding = RecyclerIncomesExpensesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderIncomesExpenses(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderIncomesExpenses, position: Int) {
        val binding = holder.binding
        binding.date.text = list[position].date
        binding.description.text = list[position].description
        if (list[position].category != "Expense") {
            binding.image.setBackgroundResource(R.drawable.icon_arrow_up)
            binding.price.setTextColor(Color.parseColor("#9B63FB"))
        }
        binding.price.text = list[position].price.toString()
        binding.name.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}