package com.example.myapplication.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.utils.calculateEyeProbability
import com.example.myapplication.utils.getEyeColorFromName

@Composable
fun EyesScreen() {
    val fatherEyeColor = remember { mutableStateOf("Неизвестно") }
    val motherEyeColor = remember { mutableStateOf("Неизвестно") }
    val result = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Цвет глаз",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            EyeColorDropdown("Папа", fatherEyeColor.value, ) { fatherEyeColor.value = it }
            EyeColorDropdown("Мама", motherEyeColor.value) { motherEyeColor.value = it }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                result.value = calculateEyeProbability(
                    fatherEyeColor.value,
                    motherEyeColor.value
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
                    ResultEyeChances(result.value)
                }
            }
        }
    }
}

@Composable
fun EyeColorDropdown(label: String, selectedColor: String, onColorSelected: (String) -> Unit) {
    val colors = listOf("Карий", "Зелёный", "Голубой")
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
                .width(100.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(getEyeColorFromName(selectedColor), CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (selectedColor == "Неизвестно") "Выбрать" else selectedColor,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            colors.forEach { color ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(getEyeColorFromName(color), CircleShape)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(color, color = MaterialTheme.colorScheme.secondary)
                        }
                    },
                    onClick = {
                        onColorSelected(color)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun ResultEyeChances(result: String) {
    val entries = result.split("\n").mapNotNull {
        val parts = it.split(" ")
        if (parts.size >= 2) {
            val percent = parts[0]
            val colorName = parts.subList(1, parts.size).joinToString(" ")
            percent to colorName
        } else null
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            "Цвет глаз ребенка будет:",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        entries.forEach { (percent, colorName) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(getEyeColorFromName(colorName), CircleShape)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = percent,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = colorName,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}