package com.example.lab5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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

