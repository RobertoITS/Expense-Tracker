package com.hvdevs.expensetracker

import java.time.LocalDate

data class DataClassIncomesExpenses(
    var category: String = "", /* Only: Income, Expense */
    var date: String = "",
    var description: String = "",
    var name: String = "",
    var price: Int = 0,
    var quotes: Int = 0
)
