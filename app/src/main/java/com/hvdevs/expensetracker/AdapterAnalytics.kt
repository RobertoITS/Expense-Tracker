package com.hvdevs.expensetracker

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.hvdevs.expensetracker.databinding.RecyclerAnalyticsCardBinding

class AdapterAnalytics(private var list: ArrayList<DataClassAnalytics>): RecyclerView.Adapter<AdapterAnalytics.ViewHolderAnalytics>() {

    class ViewHolderAnalytics (val binding: RecyclerAnalyticsCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAnalytics {
        val binding = RecyclerAnalyticsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderAnalytics(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderAnalytics, position: Int) {
        val binding = holder.binding
        binding.total.text = "$${list[position].total}"
        binding.month.text = list[position].month

        //The factor to convert px to dp
        val factor = holder.binding.root.context.resources.displayMetrics.density
        val layout: View = holder.binding.root.findViewById(R.id.percent)
        val params: ViewGroup.LayoutParams = layout.layoutParams
        params.height = (((list[position].percent * 100f) / 100) * factor).toInt() + 40
        layout.layoutParams = params
    }

    override fun getItemCount(): Int {
        return list.size
    }
}