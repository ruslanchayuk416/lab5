package com.example.lab5

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

fun calculateTip(amount: Double, percent: Double) = amount * percent / 100.0
fun calculateDiscountPercent(dishes: Int) = when {
    dishes in 1..2 -> 3
    dishes in 3..5 -> 5
    dishes in 6..10 -> 7
    dishes > 10 -> 10
    else -> 0
}
fun calculateDiscount(amount: Double, percent: Int) = amount * percent / 100.0
fun calculateTotal(amount: Double, tipPercent: Double, discountPercent: Int) =
    amount + calculateTip(amount, tipPercent) - calculateDiscount(amount, discountPercent)

@Composable
fun TipCalculatorScreen() {
    var amountInput by remember { mutableStateOf("") }
    var dishesInput by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(0f) }
    var showTotal by remember { mutableStateOf(false) }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val dishes = dishesInput.toIntOrNull() ?: 0
    val discountPercent = calculateDiscountPercent(dishes)
    val tip = calculateTip(amount, tipPercent.toDouble())
    val discount = calculateDiscount(amount, discountPercent)
    val total = calculateTotal(amount, tipPercent.toDouble(), discountPercent)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Сумма заказа")
        OutlinedTextField(
            value = amountInput, onValueChange = { amountInput = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Количество блюд")
        OutlinedTextField(
            value = dishesInput, onValueChange = { dishesInput = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

