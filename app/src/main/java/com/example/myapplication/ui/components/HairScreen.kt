package com.example.myapplication.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HairScreen() {
    val colors = listOf("Неизвестно", "Светлый", "Коричневый", "Рыжий", "Черный")
    val fatherHairColor = remember { mutableStateOf(colors[0]) }
    val motherHairColor = remember { mutableStateOf(colors[0]) }
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
                HairColorDropdown("Папа", fatherHairColor.value) { fatherHairColor.value = it }
            }
            Box() {
                HairColorDropdown("Мама", motherHairColor.value) { motherHairColor.value = it }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Button(
                onClick = {
                    result.value = calculateHairProbability(
                        fatherHairColor.value, motherHairColor.value
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
            Text("${result.value}",
                modifier = Modifier.padding(vertical = 32.dp), fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}


@Composable
fun HairColorDropdown(label: String, selectedColor: String, onColorSelected: (String) -> Unit) {
    val colors = listOf("Светлый", "Коричневый", "Рыжий", "Черный")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary,)
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

fun calculateHairProbability(fatherHairColor: String, motherHairColor: String): String {
    return when {
        fatherHairColor == "Карий" && motherHairColor == "Карий" -> "75% Карие\n18,75% Голубые\n6,25% Зелёные"
        fatherHairColor == "Голубой" && motherHairColor == "Голубой" -> "0% Карие\n99% Голубые\n1% Зелёные"
        fatherHairColor == "Зелёный" && motherHairColor == "Зелёный" -> "1% Карие\n24% Голубые\n75% Зелёные"
        fatherHairColor == "Зелёный" && motherHairColor == "Карий" -> "50% Карие\n37,5% Голубые\n12,5% Зелёные"
        fatherHairColor == "Карий" && motherHairColor == "Зелёный" -> "50% Карие\n37,5% Голубые\n12,5% Зелёные"
        fatherHairColor == "Карий" && motherHairColor == "Голубой" -> "50% Карие\n50% Голубые\n0% Зелёные"
        fatherHairColor == "Голубой" && motherHairColor == "Карий" -> "50% Карие\n50% Голубые\n0% Зелёные"
        fatherHairColor == "Голубой" && motherHairColor == "Зелёный" -> "0% Карие\n50% Голубые\n50% Зелёные"
        fatherHairColor == "Зелёный" && motherHairColor == "Голубой" -> "0% Карие\n50% Голубые\n50% Зелёные"
        else -> "Пожалуйста, укажите цвет волос обоих родителей для рассчета вероятности."
    }
}