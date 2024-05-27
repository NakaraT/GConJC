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
fun HandScreen() {
    val hands = listOf("Неизвестно", "Левша", "Правша")
    val fatherHand = remember { mutableStateOf(hands[0]) }
    val motherHand = remember { mutableStateOf(hands[0]) }
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
                HandDropdown("Папа", fatherHand.value) { fatherHand.value = it }
            }
            Box() {
                HandDropdown("Мама", motherHand.value) { motherHand.value = it }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Button(
                onClick = {
                    result.value = calculateHandProbability(
                        fatherHand.value, motherHand.value
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
fun HandDropdown(label: String, selectedHand: String, onHandSelected: (String) -> Unit) {
    val hands = listOf("Левша", "Правша")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary,)
        Box(contentAlignment = Alignment.CenterStart) {
            val textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )

            Text(selectedHand, style = textStyle, modifier = Modifier.clickable { expanded = true })

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                hands.forEach { hand ->
                    DropdownMenuItem(onClick = {
                        onHandSelected(hand)
                        expanded = false
                    }) {
                        Text(
                            hand,
                            style = textStyle,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

fun calculateHandProbability(fatherHand: String, motherHand: String): String {
    return when {
        fatherHand == "Левша" && motherHand == "Левша" -> "91% Правша\n9% Левша"
        fatherHand == "Левша" && motherHand == "Правша" -> "88% Правша\n12% Левша"
        fatherHand == "Правша" && motherHand == "Левша" -> "84% Правша\n16% Левша"
        fatherHand == "Правша" && motherHand == "Правша" -> "80% Правша\n20% Левша"
        else -> "Пожалуйста, укажите основные руки обоих родителей для рассчета вероятности."
    }
}