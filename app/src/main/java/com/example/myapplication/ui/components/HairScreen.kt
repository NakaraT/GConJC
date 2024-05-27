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
        fatherHairColor == "Светлый" && motherHairColor == "Светлый" -> "85,3% Светлый\n5,7% Коричневый\n2,5% Рыжий\n6,5% Черный"
        fatherHairColor == "Светлый" && motherHairColor == "Коричневый" -> "23,2% Светлый\n67,4% Коричневый\n2,7% Рыжий\n6,7% Черный"
        fatherHairColor == "Светлый" && motherHairColor == "Рыжий" -> "13,1% Светлый\n36,4% Коричневый\n44% Рыжий\n6,5% Черный"
        fatherHairColor == "Светлый" && motherHairColor == "Черный" -> "7,8% Светлый\n6,7% Коричневый\n2,5% Рыжий\n83% Черный"
        fatherHairColor == "Коричневый" && motherHairColor == "Светлый" -> "23,2% Светлый\n67,4% Коричневый\n2,7% Рыжий\n6,7% Черный"
        fatherHairColor == "Коричневый" && motherHairColor == "Коричневый" -> "7,9% Светлый\n82,6% Коричневый\n2,7% Рыжий\n6,7% Черный"
        fatherHairColor == "Коричневый" && motherHairColor == "Рыжий" -> "10,7% Светлый\n59,7% Коричневый\n23,2% Рыжий\n6,5% Черный"
        fatherHairColor == "Коричневый" && motherHairColor == "Черный" -> "5,1% Светлый\n12,8% Коричневый\n2,5% Рыжий\n79,6% Черный"
        fatherHairColor == "Рыжий" && motherHairColor == "Светлый" -> "13,1% Светлый\n36,4% Коричневый\n44% Рыжий\n6,5% Черный"
        fatherHairColor == "Рыжий" && motherHairColor == "Коричневый" -> "10,7% Светлый\n59,7% Коричневый\n23,2% Рыжий\n6,5% Черный"
        fatherHairColor == "Рыжий" && motherHairColor == "Рыжий" ->"6,9% Светлый\n31,9% Коричневый\n50,7% Рыжий\n10,5% Черный"
        fatherHairColor == "Рыжий" && motherHairColor == "Черный" -> "3,2% Светлый\n7,4% Коричневый\n6,5% Рыжий\n83% Черный"
        fatherHairColor == "Черный" && motherHairColor == "Светлый" -> "7,8% Светлый\n6,7% Коричневый\n2,5% Рыжий\n83% Черный"
        fatherHairColor == "Черный" && motherHairColor == "Коричневый" -> "5,1% Светлый\n12,8% Коричневый\n2,5% Рыжий\n79,6% Черный"
        fatherHairColor == "Черный" && motherHairColor == "Рыжий" -> "3,2% Светлый\n7,4% Коричневый\n6,5% Рыжий\n83% Черный"
        fatherHairColor == "Черный" && motherHairColor == "Черный" -> "3% Светлый\n5,7% Коричневый\n2,5% Рыжий\n88,8% Черный"
        else -> "Пожалуйста, укажите цвет волос обоих родителей для рассчета вероятности."
    }
}