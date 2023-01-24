package com.hvdevs.expensetracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hvdevs.expensetracker.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var aAdapter: AdapterAnalytics
    private lateinit var mAdapter: AdapterIncomesExpenses
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        /** Analytics list */
        val exampleList = arrayListOf(
            DataClassAnalytics(30000f, "Jan"),
            DataClassAnalytics(2000f, "Feb"),
            DataClassAnalytics(10000f, "Mar"),
            DataClassAnalytics(50000f, "Apr"),
            DataClassAnalytics(20000f, "May"),
            DataClassAnalytics(15000f, "Jun"),
            DataClassAnalytics(34000.54f, "Jul")
        )

        var total = 0f
        for(i in exampleList){
            total += i.total
        }
        for (i in exampleList){
            i.percent = (i.total * 100) / total
        }

        binding.recyclerAnalytics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        aAdapter = AdapterAnalytics(exampleList)
        binding.recyclerAnalytics.adapter = aAdapter


        /** Incomes & Expenses list */
        val exampleList2 = arrayListOf(
            DataClassIncomesExpenses("Expense", "24/01/2023", "Netflix quote", "Netflix", 1700, 0),
            DataClassIncomesExpenses("Income", "04/01/2023", "Salary of january", "Salary", 134000, 0),
            DataClassIncomesExpenses("Expense", "08/01/2023", "Loan quote", "Posta", 16000, 4),
        )
        binding.recyclerMovements.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = AdapterIncomesExpenses(exampleList2)
        binding.recyclerMovements.adapter = mAdapter

        return binding.root
    }

}