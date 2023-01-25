package com.hvdevs.expensetracker

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hvdevs.expensetracker.databinding.FragmentHomeBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var aAdapter: AdapterAnalytics
    private lateinit var mAdapter: AdapterIncomesExpenses

    /** Incomes & Expenses list */
    private val dataClassIncomesExpensesArrayList = arrayListOf(
        DataClassIncomesExpenses("Expense", "24/01/2022", "Netflix quote", "Netflix", 1700, 0),
        DataClassIncomesExpenses("Income", "04/01/2022", "Salary of january", "Salary", 134000, 0),
        DataClassIncomesExpenses("Expense", "08/01/2022", "Loan quote", "Posta", 16000, 4),

        DataClassIncomesExpenses("Expense", "24/01/2023", "Netflix quote", "Netflix", 1700, 0),
        DataClassIncomesExpenses("Income", "04/01/2023", "Salary of january", "Salary", 134000, 0),
        DataClassIncomesExpenses("Expense", "08/01/2023", "Loan quote", "Posta", 16000, 4),

        DataClassIncomesExpenses("Expense", "24/02/2023", "Netflix quote", "Netflix", 1700, 0),
        DataClassIncomesExpenses("Income", "04/02/2023", "Salary of january", "Salary", 134000, 0),
        DataClassIncomesExpenses("Expense", "08/02/2023", "Loan quote", "Posta", 16000, 4),

        DataClassIncomesExpenses("Expense", "24/03/2023", "Netflix quote", "Netflix", 1700, 0),
        DataClassIncomesExpenses("Income", "04/03/2023", "Salary of january", "Salary", 134000, 0),
        DataClassIncomesExpenses("Expense", "08/03/2023", "Loan quote", "Posta", 16000, 4),

        DataClassIncomesExpenses("Expense", "24/04/2023", "Netflix quote", "Netflix", 1700, 0),
        DataClassIncomesExpenses("Income", "04/04/2023", "Salary of january", "Salary", 134000, 0),
        DataClassIncomesExpenses("Expense", "08/04/2023", "Loan quote", "Posta", 16000, 4),

        DataClassIncomesExpenses("Expense", "24/05/2023", "Netflix quote", "Netflix", 1700, 0),
        DataClassIncomesExpenses("Income", "04/05/2023", "Salary of january", "Salary", 134000, 0),
        DataClassIncomesExpenses("Expense", "08/05/2023", "Loan quote", "Posta", 16000, 4),

        DataClassIncomesExpenses("Expense", "24/06/2023", "Netflix quote", "Netflix", 1700, 0),
        DataClassIncomesExpenses("Income", "04/06/2023", "Salary of january", "Salary", 134000, 0),
        DataClassIncomesExpenses("Expense", "08/06/2023", "Loan quote", "Posta", 16000, 4),
    )

    private val analyticsList: ArrayList<DataClassAnalytics> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        dataAnalytics()

        selectEntryByYear("2023")

        /** Array of years */
        var year: Array<String> = arrayOf()
        for (i in analyticsList){
            if (i.year !in year) year += i.year
        }

        binding.yearFilter.setOnClickListener {
            dialogSelectYear(year)
        }

        return binding.root
    }

    private fun dataAnalytics() {
        var minus = 2023
        var major = 0
        for (i in dataClassIncomesExpensesArrayList){
            val l = LocalDate.parse(i.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            if (minus >= l.year) minus = l.year //Año menor
            if (major <= l.year) major = l.year //Año mayor
        }
        for (year in minus..major){ //Recorremos los años
            for (month in 1..12){ //Recorremos los meses
                var total = 0
                for (element in dataClassIncomesExpensesArrayList){
                    val l = LocalDate.parse(element.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    if (month == l.monthValue && year == l.year){
                        total += element.price //Sumamos todos los precios pertenecientes al mes
                    }
                }
                analyticsList.add(DataClassAnalytics(total.toFloat(), month.toString(),0f,year.toString())) //Guardamos en la lista
            }
        }

        //Sacamos los porcentajes
        var total = 0f
        for(i in analyticsList){
            total += i.total
        }
        for (i in analyticsList){
            i.percent = (i.total * 100) / total
        }
    }

    private fun dialogSelectYear(list: Array<String>){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select year")
            .setItems(list) { dialog, position ->
                selectEntryByYear(list[position])
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

    private fun selectEntryByYear(year: String) {
        val selectedYearList = arrayListOf<DataClassAnalytics>()
        val selectedYearExpensesIncomesList = arrayListOf<DataClassIncomesExpenses>()

        for (i in analyticsList){
            if (i.year == year){
                selectedYearList.add(i)
            }
        }
        for (i in dataClassIncomesExpensesArrayList){
            val l = LocalDate.parse(i.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            if (l.year.toString() == year){
                selectedYearExpensesIncomesList.add(i)
            }
        }
        binding.recyclerAnalytics.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        aAdapter = AdapterAnalytics(selectedYearList)
        binding.recyclerAnalytics.adapter = aAdapter

        binding.recyclerMovements.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = AdapterIncomesExpenses(selectedYearExpensesIncomesList)
        binding.recyclerMovements.adapter = mAdapter
    }

    private fun showDatePicker(){
        val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
            val selectedDate = "$day/${month + 1}/$year"
            binding.yearFilter.text = selectedDate
        }
        newFragment.show(requireFragmentManager(), "datePicker")
    }
}

