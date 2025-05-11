package com.example.myapplication.ui.components

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.geneticcalc.ui.stateholder.viewModels.DashboardViewModel
import com.example.myapplication.R
import com.example.myapplication.utils.DashBoardValues
import com.example.myapplication.utils.updateCellValues

@Composable
fun DashboardScreen(navController: NavController) {


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

    val countSub = remember { mutableStateOf(100.0) }
    val countNeSub = remember { mutableStateOf(0.0) }

    val params = DashBoardValues(
        selectedParam1, selectedParam2, selectedParam3, selectedParam4,
        cell1Text, cell2Text, cell3Text, cell4Text, countSub, countNeSub
    )

    updateCellValues(params)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.inverseOnSurface
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.genes_hint),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            item {
                GeneTableCard(
                    selectedParam1, selectedParam2, selectedParam3, selectedParam4,
                    expandedParam1, expandedParam2, expandedParam3, expandedParam4,
                    { expandedParam1 = it }, { expandedParam2 = it },
                    { expandedParam3 = it }, { expandedParam4 = it },
                    parameters, params, cell1Text.value, cell2Text.value,
                    cell3Text.value, cell4Text.value
                )
            }

            item {
                StatisticsCard(countSub.value, countNeSub.value)
            }
        }
    }
}

@Composable
fun GeneTableCard(
    selectedParam1: MutableState<String>,
    selectedParam2: MutableState<String>,
    selectedParam3: MutableState<String>,
    selectedParam4: MutableState<String>,
    expandedParam1: Boolean,
    expandedParam2: Boolean,
    expandedParam3: Boolean,
    expandedParam4: Boolean,
    onExpandedChange1: (Boolean) -> Unit,
    onExpandedChange2: (Boolean) -> Unit,
    onExpandedChange3: (Boolean) -> Unit,
    onExpandedChange4: (Boolean) -> Unit,
    parameters: List<String>,
    params: DashBoardValues,
    cell1Text: String,
    cell2Text: String,
    cell3Text: String,
    cell4Text: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Таблица
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
            ) {
                // Первая строка
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.inverseOnSurface),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "A/a",
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    DropdownGeneBox(
                        selected = selectedParam1,
                        expanded = expandedParam1,
                        onExpandedChange = onExpandedChange1,
                        parameters = parameters,
                        params = params,
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    )
                    DropdownGeneBox(
                        selected = selectedParam2,
                        expanded = expandedParam2,
                        onExpandedChange = onExpandedChange2,
                        parameters = parameters,
                        params = params,
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                    )
                }

                // Вторая строка
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DropdownGeneBox(
                        selected = selectedParam3,
                        expanded = expandedParam3,
                        onExpandedChange = onExpandedChange3,
                        parameters = parameters,
                        params = params,
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                    )
                    Text(
                        text = cell1Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = getColorForGene(cell1Text)
                    )
                    Text(
                        text = cell2Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = getColorForGene(cell2Text)
                    )
                }

                // Третья строка
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DropdownGeneBox(
                        selected = selectedParam4,
                        expanded = expandedParam4,
                        onExpandedChange = onExpandedChange4,
                        parameters = parameters,
                        params = params,
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .background(MaterialTheme.colorScheme.inverseOnSurface)
                    )
                    Text(
                        text = cell3Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = getColorForGene(cell3Text)
                    )
                    Text(
                        text = cell4Text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(12.dp),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = getColorForGene(cell4Text)
                    )
                }
            }
        }
    }
}

@Composable
fun StatisticsCard(countSub: Double, countNeSub: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface // У тебя нет такого цвета
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatRow(stringResource(R.string.dominant_gene), countSub, MaterialTheme.colorScheme.primary)
            StatRow(stringResource(R.string.recessive_gene), countNeSub, MaterialTheme.colorScheme.tertiary)
        }
    }
}

@Composable
fun StatRow(label: String, value: Double, color: Color) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "${value.toInt()}%",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        }
        LinearProgressIndicator(
            progress = { (value / 100).toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = color,
            trackColor = MaterialTheme.colorScheme.background,
        )
    }
}

@Composable
fun DropdownGeneBox(
    selected: MutableState<String>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    parameters: List<String>,
    params: DashBoardValues,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                RoundedCornerShape(8.dp)
            )
            .clickable { onExpandedChange(true) }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = selected.value,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            parameters.forEach { param ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = param,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                    },
                    onClick = {
                        selected.value = param
                        onExpandedChange(false)
                        updateCellValues(params)
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.inverseOnSurface)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun getColorForGene(gene: String): Color {
    return when (gene) {
        "A/A" -> MaterialTheme.colorScheme.primary
        "A/a" -> MaterialTheme.colorScheme.tertiary
        "a/a" -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        else -> MaterialTheme.colorScheme.primary
    }
}


class DashboardViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(application) as T
    }
}

