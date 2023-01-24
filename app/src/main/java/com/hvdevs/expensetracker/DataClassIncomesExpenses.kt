package com.hvdevs.expensetracker

data class DataClassIncomesExpenses(
    var category: String? = null, /* Only: Income, Expense */
    var date: String? = null,
    var description: String? = null,
    var name: String? = null,
    var price: Int = 0,
    var quotes: Int = 0
)
