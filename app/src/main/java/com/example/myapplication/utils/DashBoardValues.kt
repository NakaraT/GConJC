package com.example.myapplication.utils

import androidx.compose.runtime.MutableState

data class DashBoardValues(
    val selectedParam1: MutableState<String>,
    val selectedParam2: MutableState<String>,
    val selectedParam3: MutableState<String>,
    val selectedParam4: MutableState<String>,
    val cell1Text: MutableState<String>,
    val cell2Text: MutableState<String>,
    val cell3Text: MutableState<String>,
    val cell4Text: MutableState<String>,
    val countSub: MutableState<Double>,
    val countNeSub: MutableState<Double>
)