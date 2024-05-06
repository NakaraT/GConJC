package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun EyesScreen() {
    val colors = listOf("Неизвестно", "Карий", "Зелёный", "Голубой")
    val fatherEyeColor = remember { mutableStateOf(colors[0]) }
    val motherEyeColor = remember { mutableStateOf(colors[0]) }
    val result = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 128.dp), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(){
                EyeColorDropdown("Папа", fatherEyeColor.value) { fatherEyeColor.value = it }
            }
            Box() {
                EyeColorDropdown("Мама", motherEyeColor.value) { motherEyeColor.value = it }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Button(
                onClick = {
                    result.value = calculateProbability(
                        fatherEyeColor.value, motherEyeColor.value
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 32.dp, end = 32.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(
                    text = "Рассчитать вероятность",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text("${result.value}",modifier = Modifier.padding(vertical = 32.dp), fontSize = 20.sp)
        }
    }
}


@Composable
fun EyeColorDropdown(label: String, selectedColor: String, onColorSelected: (String) -> Unit) {
    val colors = listOf("Карий", "Зелёный", "Голубой")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, fontSize = 20.sp)
        Box(contentAlignment = Alignment.CenterStart) {
            val textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )

            Text(selectedColor, style = textStyle, modifier = Modifier.clickable { expanded = true })

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                colors.forEach { color ->
                    DropdownMenuItem(onClick = {
                        onColorSelected(color)
                        expanded = false
                    }) {
                        Text(
                            color,
                            style = textStyle,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

fun calculateProbability(fatherEyeColor: String, motherEyeColor: String): String {
    return when {
        fatherEyeColor == "Карий" && motherEyeColor == "Карий" -> "75% Карие\n18,75% Голубые\n6,25% Зелёные"
        fatherEyeColor == "Голубой" && motherEyeColor == "Голубой" -> "0% Карие\n99% Голубые\n1% Зелёные"
        fatherEyeColor == "Зелёный" && motherEyeColor == "Зелёный" -> "1% Карие\n24% Голубые\n75% Зелёные"
        fatherEyeColor == "Зелёный" && motherEyeColor == "Карий" -> "50% Карие\n37,5% Голубые\n12,5% Зелёные"
        fatherEyeColor == "Карий" && motherEyeColor == "Зелёный" -> "50% Карие\n37,5% Голубые\n12,5% Зелёные"
        fatherEyeColor == "Карий" && motherEyeColor == "Голубой" -> "50% Карие\n50% Голубые\n0% Зелёные"
        fatherEyeColor == "Голубой" && motherEyeColor == "Карий" -> "50% Карие\n50% Голубые\n0% Зелёные"
        fatherEyeColor == "Голубой" && motherEyeColor == "Зелёный" -> "0% Карие\n50% Голубые\n50% Зелёные"
        fatherEyeColor == "Зелёный" && motherEyeColor == "Голубой" -> "0% Карие\n50% Голубые\n50% Зелёные"
        else -> "Пожалуйста, введите цвета глаз обоих родителей для рассчета вероятности."
    }
}