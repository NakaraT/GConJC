package com.example.myapplication.ui.components

import android.app.Application
import android.app.DatePickerDialog
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.ui.stateholder.viewModels.RelativesListViewModel
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationScreen(relativesListViewModel: RelativesListViewModel) {
    val allRelatives by relativesListViewModel.allRelatives.observeAsState(listOf())

    var selectedName by remember { mutableStateOf("")}
    var selectedHairColor by remember { mutableStateOf("")}
    val onSelectedNameChange = { text: String -> selectedName = text }
    val onSelectedHairColorChange = { text: String -> selectedHairColor = text }
    var selectedEyeColor by remember { mutableStateOf("")}
    var selectedDate by remember { mutableStateOf("")}
    var selectedBloodType by remember { mutableStateOf("")}
    val bloodTypes = arrayOf("I+", "I-", "II+", "II-", "III+", "III-", "III+", "IV+", "IV-")
    val eyeColors = arrayOf("Голубые", "Карие", "Серые", "Зелёные")
    var isBloodTypesExpanded by remember { mutableStateOf(false)}
    var isEyeColorsExpanded by remember { mutableStateOf(false)}
    var isAddMenuVisible by remember { mutableStateOf(false)}
    var isDateExpanded by remember { mutableStateOf(false)}
    if (!isAddMenuVisible) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 48.dp)
    ) {
        item { Button(onClick = { relativesListViewModel.clearRelatives() }) {
            Text(text = "ОЧИСТИТЬ ТАБЛИЦУ", color = Color.Red)
        } }
        item {
            IconButton(
                onClick = { isAddMenuVisible = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(128.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
            //asd
        items(allRelatives) { relative ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_man_24),
                    contentDescription = "Relative Logo",
                    modifier = Modifier.size(120.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Имя: ${relative.relativeName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Дата рождения: ${relative.dateofBirth}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Цвет глаз: ${relative.eyeColor}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Цвет волос: ${relative.hairColor}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Группа крови: ${relative.bloodType}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Button(onClick = { relativesListViewModel.deleteRelative(relative.id)}) {
                        Text(text = "Удалить!")
                    }
                    Button(onClick = {
                        selectedDate = relative.dateofBirth
                        selectedName = relative.relativeName
                        selectedBloodType = relative.bloodType
                        selectedEyeColor = relative.eyeColor
                        selectedHairColor = relative.hairColor
                        relativesListViewModel.deleteRelative(relative.id)
                        isAddMenuVisible = true
                    }) {
                        Text(text = "Редактировать")
                    }
                }
            }
        }
    }
}
    else {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = selectedName,
                onValueChange = {onSelectedNameChange(it)},
                label = { Text(text = "Имя")},
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {if (selectedName.isNotEmpty()) IconButton(onClick = { onSelectedNameChange("") }) {
                    Icon(Icons.Filled.Clear, contentDescription = null)
                } else null})
            OutlinedTextField(
                value = selectedHairColor,
                onValueChange = {onSelectedHairColorChange(it)},
                label = { Text(text = "Цвет волос")},
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {if (selectedHairColor.isNotEmpty()) IconButton(onClick = { onSelectedHairColorChange("") }) {
                    Icon(Icons.Filled.Clear, contentDescription = null)
                } else null})
            ExposedDropdownMenuBox(expanded = isDateExpanded, onExpandedChange = {isDateExpanded = !isDateExpanded} ) {
                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = {},
                    label = { Text("Дата рождения", fontSize = 10.sp) },
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .alpha(if (isDateExpanded) 0f else 1f),
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = isDateExpanded
                        )})
                if (isDateExpanded){
                AndroidView(
                    { CalendarView(it) },
                    modifier = Modifier
                        .wrapContentWidth()
                        .border(2.dp, color = Color.Black)
                        .align(Alignment.CenterHorizontally),
                    update = { views ->
                        views.setOnDateChangeListener { calendarView, year, month, dayOfMonth ->
                            selectedDate = "$dayOfMonth, $month, $year"
                            isDateExpanded = false
                        }
                    }
                )}
            }
            ExposedDropdownMenuBox(expanded = isBloodTypesExpanded, onExpandedChange = {isBloodTypesExpanded = !isBloodTypesExpanded} ) {
                OutlinedTextField(
                    value = selectedBloodType,
                    onValueChange = {},
                    label = { Text("Группа крови", fontSize = 10.sp) },
                    readOnly = true,
                    modifier = Modifier.menuAnchor(),
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = isBloodTypesExpanded
                        )})
                ExposedDropdownMenu(expanded = isBloodTypesExpanded, onDismissRequest = { /*TODO*/ }) {
                    bloodTypes.forEach { item ->  androidx.compose.material3.DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedBloodType = item
                            isBloodTypesExpanded = false})
                }
            }}
            ExposedDropdownMenuBox(expanded = isEyeColorsExpanded, onExpandedChange = {isEyeColorsExpanded = !isEyeColorsExpanded} ) {
                OutlinedTextField(
                    value = selectedEyeColor,
                    onValueChange = {},
                    label = { Text("Цвет глаз", fontSize = 10.sp) },
                    readOnly = true,
                    modifier = Modifier.menuAnchor(),
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = isEyeColorsExpanded
                        )})
                ExposedDropdownMenu(expanded = isEyeColorsExpanded, onDismissRequest = { /*TODO*/ }) {
                    eyeColors.forEach { item ->  androidx.compose.material3.DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedEyeColor = item
                            isEyeColorsExpanded = false})
                    }
                }}
            Button(onClick = {
                relativesListViewModel.addRelative(RelativesEntity(0,selectedName,selectedEyeColor,selectedHairColor,
                    selectedDate, selectedBloodType))
                selectedName = ""
                selectedEyeColor = ""
                selectedDate = ""
                selectedBloodType = ""
                selectedHairColor = ""
                isAddMenuVisible = false }) {
                Text(text = "Готово")
            }
        }
    }
}


class RelativeListViewModelFactory(val application: Application):
        ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RelativesListViewModel(application) as T
    }
        }

