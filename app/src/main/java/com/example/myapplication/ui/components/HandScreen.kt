package com.example.myapplication.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.utils.calculateHandProbability

@Composable
fun HandScreen() {
    val fatherHand = remember { mutableStateOf("Неизвестно") }
    val motherHand = remember { mutableStateOf("Неизвестно") }
    val result = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Левша или правша",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            HandDropdown("Папа", fatherHand.value) { fatherHand.value = it }
            HandDropdown("Мама", motherHand.value) { motherHand.value = it }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                result.value = calculateHandProbability(
                    fatherHand.value,
                    motherHand.value
                )
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Рассчитать вероятность", fontSize = 18.sp, color = MaterialTheme.colorScheme.secondary)
        }

        Spacer(Modifier.height(32.dp))

        if (result.value.isNotEmpty()) {
            AnimatedVisibility(visible = true, enter = fadeIn()) {
                if (result.value.contains("Пожалуйста")) {
                    Text(
                        result.value,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    ResultHandChances(result.value)
                }
            }
        }
    }
}

@Composable
fun HandDropdown(label: String, selected: String, onSelect: (String) -> Unit) {
    val options = listOf("Левша", "Правша")
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 18.sp, color = MaterialTheme.colorScheme.secondary)

        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { expanded = true }
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .width(70.dp)
        ) {
            Text(
                text = if (selected == "Неизвестно") "Выбрать" else selected,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 16.sp
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { hand ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(hand)
                        expanded = false
                    }
                ) {
                    Text(hand, color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}

@Composable
fun ResultHandChances(result: String) {
    val entries = result.split("\n").mapNotNull {
        val parts = it.split(" - ")
        if (parts.size == 2) parts[0] to parts[1] else null
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            "Ведущая рука ребёнка будет:",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        entries.forEach { (percent, handType) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = percent,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = handType,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
