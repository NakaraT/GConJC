package com.example.myapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
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

@Composable
fun DashboardScreen() {

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
    updateCellValues(
        selectedParam1,
        selectedParam2,
        selectedParam3,
        selectedParam4,
        cell1Text,
        cell2Text,
        cell3Text,
        cell4Text
    )
    LazyColumn(Modifier.padding(16.dp)) {
        item {
            Text(
                text = "A - Доминантный ген\na - Рецессивный ген",
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

// 1 cтолбец

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {

//1.1

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    Text(text = "A/a", color = MaterialTheme.colorScheme.primary)
                }

//1.2
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam1.value,
                        modifier = Modifier.clickable { expandedParam1 = true },
                        color = MaterialTheme.colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam1,
                        onDismissRequest = { expandedParam1 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam1.value = param
                                expandedParam1 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = MaterialTheme.colorScheme.primary)
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
                        modifier = Modifier.clickable { expandedParam2 = true },
                        color = MaterialTheme.colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam2,
                        onDismissRequest = { expandedParam2 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam2.value = param
                                expandedParam2 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        }

// 2 cтолбец

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
//2.1
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam3.value,
                        modifier = Modifier.clickable { expandedParam3 = true },
                        color = MaterialTheme.colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam3,
                        onDismissRequest = { expandedParam3 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam3.value = param
                                expandedParam3 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }

//2.2

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell1Text.value, color = MaterialTheme.colorScheme.primary)
                }

//2.3

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell2Text.value, color = MaterialTheme.colorScheme.primary)
                }
            }
        }

// 3 cтолбец

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
//3.1
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam4.value,
                        modifier = Modifier.clickable { expandedParam4 = true },
                        color = MaterialTheme.colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam4,
                        onDismissRequest = { expandedParam4 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam4.value = param
                                expandedParam4 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }

//3.2

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell3Text.value, color = MaterialTheme.colorScheme.primary)
                }

//3.3

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell4Text.value, color = MaterialTheme.colorScheme.primary)
                }
            }
        }

    }
}

// Функция для обновления значений ячеек
fun updateCellValues(
    selectedParam1: MutableState<String>,
    selectedParam2: MutableState<String>,
    selectedParam3: MutableState<String>,
    selectedParam4: MutableState<String>,
    cell1Text: MutableState<String>,
    cell2Text: MutableState<String>,
    cell3Text: MutableState<String>,
    cell4Text: MutableState<String>
) {
    cell1Text.value = "${selectedParam1.value}/${selectedParam3.value}"
    cell2Text.value = "${selectedParam2.value}/${selectedParam3.value}"
    cell3Text.value = "${selectedParam4.value}/${selectedParam1.value}"
    cell4Text.value = "${selectedParam4.value}/${selectedParam2.value}"
}

// Функция для получения комбинации генов
fun getGeneCombination(cross: String): String {
    if (cross.length < 3) {
        return "Invalid input"
    }

    val gene1 = cross.substring(0, 1)
    val gene2 = cross.substring(2, 3)

    return gene1 + gene2
}