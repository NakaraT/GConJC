package com.example.myapplication.ui.components

import android.app.Application
import android.app.DatePickerDialog
import android.widget.CalendarView
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
    var selectedDate by remember { mutableStateOf("")}
    val onSelectedNameChange = { text: String -> selectedName = text }
    val onSelectedHairColorChange = { text: String -> selectedHairColor = text }
    val onSelectedDateChange = { text: String -> selectedDate = text }
    var selectedEyeColor by remember { mutableStateOf("")}
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
                    .padding(4.dp)
                    .clickable{
                        selectedDate = relative.dateofBirth
                        selectedName = relative.relativeName
                        selectedBloodType = relative.bloodType
                        selectedEyeColor = relative.eyeColor
                        selectedHairColor = relative.hairColor
                        relativesListViewModel.deleteRelative(relative.id)
                        isAddMenuVisible = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_man_24),
                    contentDescription = "Relative Logo",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                    modifier = Modifier.size(120.dp)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Имя:\n${relative.relativeName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Дата рождения:\n${relative.dateofBirth}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                IconButton(onClick = {
                    relativesListViewModel.deleteRelative(relative.id)
                }) {
                    Icon(Icons.Filled.Close, contentDescription = "Close Button", modifier = Modifier.size(25.dp), tint = (MaterialTheme.colorScheme.secondary),)
                }
            }
        }
    }
}
    else {
        Column(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedTextField(
                    value = selectedName,
                    textStyle = TextStyle(MaterialTheme.colorScheme.secondary),
                    onValueChange = {onSelectedNameChange(it)},
                    label = { Text(text = "Имя", color = MaterialTheme.colorScheme.secondary, fontSize = 16.sp)},
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {if (selectedName.isNotEmpty()) IconButton(onClick = { onSelectedNameChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = null)
                    } else null})}
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedTextField(
                    value = selectedHairColor,
                    textStyle = TextStyle(MaterialTheme.colorScheme.secondary),
                    onValueChange = {onSelectedHairColorChange(it)},
                    label = { Text(text = "Цвет волос", color = MaterialTheme.colorScheme.secondary, fontSize = 16.sp)},
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {if (selectedHairColor.isNotEmpty()) IconButton(onClick = { onSelectedHairColorChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = null)
                    } else null})}
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedTextField(
                    value = selectedDate,
                    textStyle = TextStyle(MaterialTheme.colorScheme.secondary),
                    onValueChange = {onSelectedDateChange(it)},
                    label = { Text("Дата рождения", color = MaterialTheme.colorScheme.secondary, fontSize = 16.sp) },
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {if (selectedDate.isNotEmpty()) IconButton(onClick = { onSelectedDateChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = null)
                    } else null})}
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                ExposedDropdownMenuBox(expanded = isBloodTypesExpanded, onExpandedChange = {isBloodTypesExpanded = !isBloodTypesExpanded} ) {
                    OutlinedTextField(
                        value = selectedBloodType,
                        textStyle = TextStyle(MaterialTheme.colorScheme.secondary),
                        onValueChange = {},
                        label = { Text("Группа крови",color = MaterialTheme.colorScheme.secondary, fontSize = 16.sp) },
                        readOnly = true,
                        modifier = Modifier.menuAnchor(),
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = isBloodTypesExpanded
                            )})
                ExposedDropdownMenu(expanded = isBloodTypesExpanded, onDismissRequest = { /*TODO*/ }) {
                    bloodTypes.forEach { item ->  androidx.compose.material3.DropdownMenuItem(
                        text = { Text(text = item, color = MaterialTheme.colorScheme.secondary) },
                        onClick = {
                            selectedBloodType = item
                            isBloodTypesExpanded = false})
                }
            }}}
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                ExposedDropdownMenuBox(expanded = isEyeColorsExpanded, onExpandedChange = {isEyeColorsExpanded = !isEyeColorsExpanded} ) {
                    OutlinedTextField(
                        value = selectedEyeColor,
                        textStyle = TextStyle(MaterialTheme.colorScheme.secondary),
                        onValueChange = {},
                        label = { Text("Цвет глаз",color = MaterialTheme.colorScheme.secondary, fontSize = 16.sp) },
                        readOnly = true,
                        modifier = Modifier.menuAnchor(),
                        shape = RoundedCornerShape(16.dp),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = isEyeColorsExpanded
                            )})
                    ExposedDropdownMenu(expanded = isEyeColorsExpanded, onDismissRequest = { /*TODO*/ }) {
                        eyeColors.forEach { item ->  androidx.compose.material3.DropdownMenuItem(
                            text = { Text(text = item, color = MaterialTheme.colorScheme.secondary) },
                            onClick = {
                                selectedEyeColor = item
                                isEyeColorsExpanded = false})
                        }
                    }}}
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp), horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    relativesListViewModel.addRelative(RelativesEntity(0,selectedName,selectedEyeColor,selectedHairColor,
                        selectedDate, selectedBloodType))
                    selectedName = ""
                    selectedEyeColor = ""
                    selectedDate = ""
                    selectedBloodType = ""
                    selectedHairColor = ""
                    isAddMenuVisible = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(start = 48.dp, end = 48.dp),

                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Готово",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

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

