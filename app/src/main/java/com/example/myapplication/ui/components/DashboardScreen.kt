package com.example.myapplication.ui.components

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geneticcalc.ui.stateholder.viewModels.DashboardViewModel
import com.example.geneticcalc.ui.stateholder.viewModels.RelativesListViewModel

@Composable
fun DashboardScreen(dashboardViewModel: DashboardViewModel) {
    val parameters = listOf("A", "a")
    val selectedParam1 = remember { mutableStateOf(parameters[0]) }
    val selectedParam2 = remember { mutableStateOf(parameters[0]) }
    val selectedParam3 = remember { mutableStateOf(parameters[0]) }
    val selectedParam4 = remember { mutableStateOf(parameters[0]) }

    val cell1Text = remember { mutableStateOf("") }
    val cell2Text = remember { mutableStateOf("") }
    val cell3Text = remember { mutableStateOf("") }
    val cell4Text = remember { mutableStateOf("") }

    var expandedParam1 by remember { mutableStateOf(false) }
    var expandedParam2 by remember { mutableStateOf(false) }
    var expandedParam3 by remember { mutableStateOf(false) }
    var expandedParam4 by remember { mutableStateOf(false) }

    val countSub = remember {mutableStateOf(100.0)}
    val countNeSub = remember {mutableStateOf(0.0)}

    val params = UpdateCellValuesParams(selectedParam1, selectedParam2, selectedParam3,
        selectedParam4, cell1Text, cell2Text, cell3Text, cell4Text, countSub, countNeSub)

    updateCellValues(params)
    LazyColumn(Modifier.padding(16.dp)) {
        item {
            Text(
                text = "A - Доминантный ген\na - Рецессивный ген",
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp),
                color = colorScheme.secondary
            )
        }
// 1 cтолбец
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.background(colorScheme.inverseOnSurface)) {
//1.1
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(
                        text = "A/a",
                        color = colorScheme.primary,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center))
                }
//1.2
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam1.value,
                        modifier = Modifier.clickable { expandedParam1 = true }.align(Alignment.Center),
                        color = colorScheme.primary,
                        fontSize = 20.sp
                    )
                    DropdownMenu(
                        expanded = expandedParam1,
                        onDismissRequest = { expandedParam1 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam1.value = param
                                expandedParam1 = false
                                updateCellValues(params)
                            }) {
                                Text(text = param, color = colorScheme.primary, fontSize = 20.sp)
                            }
                        }
                    }
                }
//1.3
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam2.value,
                        modifier = Modifier.clickable { expandedParam2 = true }.align(Alignment.Center),
                        color = colorScheme.primary,
                        fontSize = 20.sp
                    )
                    DropdownMenu(
                        expanded = expandedParam2,
                        onDismissRequest = { expandedParam2 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam2.value = param
                                expandedParam2 = false
                                updateCellValues(params)
                            }) {
                                Text(text = param, color = colorScheme.primary, fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
// 2 cтолбец
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.background(colorScheme.inverseOnSurface)) {
//2.1
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam3.value,
                        modifier = Modifier.clickable { expandedParam3 = true }.align(Alignment.Center),
                        color = colorScheme.primary,
                        fontSize = 20.sp
                    )
                    DropdownMenu(
                        expanded = expandedParam3,
                        onDismissRequest = { expandedParam3 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam3.value = param
                                expandedParam3 = false
                                updateCellValues(params)
                            }) {
                                Text(text = param,
                                    color = colorScheme.primary,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }
//2.2
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell1Text.value,
                        color = colorScheme.primary,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center))
                }
//2.3
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = cell2Text.value,
                        color = colorScheme.primary,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center))
                }
            }
        }
// 3 cтолбец
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.background(colorScheme.inverseOnSurface)) {
//3.1
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam4.value,
                        modifier = Modifier.clickable { expandedParam4 = true }.align(Alignment.Center),
                        color = colorScheme.primary,
                        fontSize = 20.sp
                    )
                    DropdownMenu(
                        expanded = expandedParam4,
                        onDismissRequest = { expandedParam4 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam4.value = param
                                expandedParam4 = false
                                updateCellValues(params)
                            }) {
                                Text(text = param,
                                    color = colorScheme.primary,
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }
//3.2
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = cell3Text.value,
                        color = colorScheme.primary,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center))
                }
//3.3
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = cell4Text.value,
                        color = colorScheme.primary,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Center))
                }
            }
        }
        item {
            Text(
                text = "${countSub.value} - Вероятность передачи доминантного гена\n${countNeSub.value} - Вероятность передачи рецессивного гена",
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp),
                color = colorScheme.secondary
            )
        }
    }
}

fun updateCellValues(params: UpdateCellValuesParams) {
    with(params) {
        cell1Text.value = transformGeneFormat("${selectedParam1.value}/${selectedParam3.value}")
        cell2Text.value = transformGeneFormat("${selectedParam2.value}/${selectedParam3.value}")
        cell3Text.value = transformGeneFormat("${selectedParam4.value}/${selectedParam1.value}")
        cell4Text.value = transformGeneFormat("${selectedParam4.value}/${selectedParam2.value}")
        val textCells = listOf(cell1Text.value, cell2Text.value, cell3Text.value, cell4Text.value)
        val aaCount = textCells.sumOf { countSubstring(it, "a/a") }
        countSub.value = (4 - aaCount) / 4.0 * 100
        countNeSub.value = 100.0 - countSub.value
    }
}

fun transformGeneFormat(gene: String): String {
    return if (gene.equals("a/A")){
        "A/a"
    }
    else {
        gene
    }
}

fun countSubstring(str: String, substr: String): Int {
    var count = 0
    var idx = 0
    while (str.indexOf(substr, idx).also { idx = it } != -1) {
        idx += substr.length
        count++
    }
    return count
}

class DashboardViewModelFactory(val application: Application):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(application) as T
    }
}

data class UpdateCellValuesParams(
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