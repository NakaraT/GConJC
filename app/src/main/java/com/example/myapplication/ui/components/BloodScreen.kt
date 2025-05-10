package com.example.myapplication.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.utils.calculateBloodProbability

@Composable
fun BloodScreen() {
    val fatherBlood = remember { mutableStateOf("Неизвестно") }
    val motherBlood = remember { mutableStateOf("Неизвестно") }
    val result = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Группа крови",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            BloodDropdown("Папа", fatherBlood.value) { fatherBlood.value = it }
            BloodDropdown("Мама", motherBlood.value) { motherBlood.value = it }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                result.value = calculateBloodProbability(
                    fatherBlood.value,
                    motherBlood.value
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
            AnimatedVisibility (visible = true, enter = fadeIn()) {
                if (result.value.contains("Пожалуйста")) {
                    Text(
                        result.value,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    ResultBloodChances(result.value)
                }
            }
        }
    }
}

@Composable
fun BloodDropdown(label: String, selected: String, onSelect: (String) -> Unit) {
    val options = listOf("I", "II", "III", "IV")
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
            options.forEach { blood ->
                DropdownMenuItem(
                    onClick = {
                        onSelect(blood)
                        expanded = false
                    }
                )
                {
                    Text(blood, color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}

@Composable
fun ResultBloodChances(result: String) {
    val entries = result.split("\n").mapNotNull {
        val parts = it.split(" - ")
        if (parts.size == 2) parts[0] to parts[1] else null
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            "Группа крови ребенка будет:",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        entries.forEach { (percent, bloodGroup) ->
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
                    text = bloodGroup,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}