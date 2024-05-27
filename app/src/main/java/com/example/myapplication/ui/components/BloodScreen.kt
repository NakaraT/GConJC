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
fun BloodScreen() {
    val bloods = listOf("Неизвестно", "I", "II", "III","IV")
    val fatherBlood = remember { mutableStateOf(bloods[0]) }
    val motherBlood = remember { mutableStateOf(bloods[0]) }
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
                BloodDropdown("Папа", fatherBlood.value) { fatherBlood.value = it }
            }
            Box() {
                BloodDropdown("Мама", motherBlood.value) { motherBlood.value = it }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Button(
                onClick = {
                    result.value = calculateBloodProbability(
                        fatherBlood.value, motherBlood.value
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
fun BloodDropdown(label: String, selectedBloods: String, onBloodSelected: (String) -> Unit) {
    val bloods = listOf("I", "II", "III", "IV")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, fontSize = 20.sp, color = MaterialTheme.colorScheme.secondary,)
        Box(contentAlignment = Alignment.CenterStart) {
            val textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )

            Text(selectedBloods, style = textStyle, modifier = Modifier.clickable { expanded = true })

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                bloods.forEach { bloods ->
                    DropdownMenuItem(onClick = {
                        onBloodSelected(bloods)
                        expanded = false
                    }) {
                        Text(
                            bloods,
                            style = textStyle,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

fun calculateBloodProbability(fatherBlood: String, motherBlood: String): String {
    return when {
        fatherBlood == "I" && motherBlood == "I" -> "100% - I\n0% - II\n0% - III\n0% - IV"
        fatherBlood == "I" && motherBlood == "II" -> "50% - I\n50% - II\n0% - III\n0% - IV"
        fatherBlood == "I" && motherBlood == "III" -> "50% - I\n0% - II\n50% - III\n0% - IV"
        fatherBlood == "I" && motherBlood == "IV" -> "0% - I\n50% - II\n50% - III\n0% - IV"
        fatherBlood == "II" && motherBlood == "I" -> "50% - I\n50% - II\n0% - III\n0% - IV"
        fatherBlood == "II" && motherBlood == "II" -> "50% - I\n50% - II\n0% - III\n0% - IV"
        fatherBlood == "II" && motherBlood == "III" -> "25% - I\n25% - II\n25% - III\n25% - IV"
        fatherBlood == "II" && motherBlood == "IV" -> "0% - I\n50% - II\n25% - III\n25% - IV"
        fatherBlood == "III" && motherBlood == "I" -> "50% - I\n0% - II\n50% - III\n0% - IV"
        fatherBlood == "III" && motherBlood == "II" -> "25% - I\n25% - II\n25% - III\n25% - IV"
        fatherBlood == "III" && motherBlood == "III" -> "50% - I\n0% - II\n50% - III\n0% - IV"
        fatherBlood == "III" && motherBlood == "IV" -> "0% - I\n25% - II\n50% - III\n25% - IV"
        fatherBlood == "IV" && motherBlood == "I" -> "0% - I\n50% - II\n50% - III\n0% - IV"
        fatherBlood == "IV" && motherBlood == "II" -> "0% - I\n50% - II\n25% - III\n25% - IV"
        fatherBlood == "IV" && motherBlood == "III" -> "0% - I\n25% - II\n50% - III\n25% - IV"
        fatherBlood == "IV" && motherBlood == "IV" -> "0% - I\n25% - II\n25% - III\n50% - IV"
        else -> "Пожалуйста, укажите группу крови обоих родителей для рассчета вероятности."
    }
}