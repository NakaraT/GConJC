package com.example.myapplication.ui.components
import android.app.Application
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.remember
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.lifecycle.ViewModelProvider
import com.example.geneticcalc.data.database.entity.RelativesEntity
import com.example.geneticcalc.ui.stateholder.viewModels.RelativesListViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InformationScreen(relativesListViewModel: RelativesListViewModel) {
    val allRelatives by relativesListViewModel.allRelatives.observeAsState()

    var selectedName by remember { mutableStateOf("") }
    var selectedHairColor by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedId by remember { mutableStateOf(0) }
    var selectedEyeColor by remember { mutableStateOf("") }
    var selectedBloodType by remember { mutableStateOf("") }
    var isAddMenuVisible by remember { mutableStateOf(false) }
    var openedForEditing by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val bloodTypes = arrayOf("I+", "I-", "II+", "II-", "III+", "III-", "IV+", "IV-")
    val eyeColors = arrayOf("Голубые", "Карие", "Серые", "Зелёные")
    val hairColors = arrayOf("Светлый", "Карий", "Рыжий", "Тёмный")

    val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    if (!isAddMenuVisible) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 48.dp)
        ) {
            Text(
                text = "ДАННЫЕ",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Здесь вы можете добавлять данные для подсчета генетического результата",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { isAddMenuVisible = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .shadow(4.dp, RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp),
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = MaterialTheme.colorScheme.secondary)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Добавить человека", color = MaterialTheme.colorScheme.secondary, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(allRelatives ?: emptyList()) { relative ->
                    RelativeCard(
                        relative = relative,
                        onEditClick = {
                            selectedDate = relative.dateofBirth
                            selectedName = relative.relativeName
                            selectedBloodType = relative.bloodType
                            selectedEyeColor = relative.eyeColor
                            selectedHairColor = relative.hairColor
                            selectedId = relative.id
                            isAddMenuVisible = true
                            openedForEditing = true
                        },
                        onDeleteClick = { relativesListViewModel.deleteRelative(relative.id) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = if (openedForEditing) "Редактирование персоны" else "Добавление персоны",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = selectedName,
                    onValueChange = { selectedName = it },
                    label = { Text("Введите имя человека", color = MaterialTheme.colorScheme.secondary) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.secondary),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Цвет волос",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        hairColors.take(2).forEach { color ->
                            SelectableItem(
                                text = color,
                                isSelected = selectedHairColor == color,
                                color = when (color) {
                                    "Светлый" -> Color(0xFFE6D5A1)
                                    "Карий" -> Color(0xFF5B381E)
                                    "Рыжий" -> Color(0xFFD2691E)
                                    else -> Color(0xFF211E1C)
                                },
                                onClick = { selectedHairColor = color },
                                modifier = Modifier.weight(1f) // Растягиваем элемент по ширине
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        hairColors.drop(2).forEach { color ->
                            SelectableItem(
                                text = color,
                                isSelected = selectedHairColor == color,
                                color = when (color) {
                                    "Светлый" -> Color(0xFFE6D5A1)
                                    "Карий" -> Color(0xFF5B381E)
                                    "Рыжий" -> Color(0xFFD2691E)
                                    else -> Color(0xFF211E1C)
                                },
                                onClick = { selectedHairColor = color },
                                modifier = Modifier.weight(1f) // Растягиваем элемент по ширине
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Группа крови",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        bloodTypes.take(4).forEach { type ->
                            SelectableItem(
                                text = type,
                                isSelected = selectedBloodType == type,
                                color = null,
                                onClick = { selectedBloodType = type },
                                modifier = Modifier.width(80.dp) // Фиксированная ширина для кнопок группы крови
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        bloodTypes.drop(4).forEach { type ->
                            SelectableItem(
                                text = type,
                                isSelected = selectedBloodType == type,
                                color = null,
                                onClick = { selectedBloodType = type },
                                modifier = Modifier.width(80.dp) // Фиксированная ширина для кнопок группы крови
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Дата рождения",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { },
                    label = { Text("Выберите дату", color = MaterialTheme.colorScheme.secondary) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDatePicker = true },
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.secondary),
                    shape = RoundedCornerShape(12.dp),
                    enabled = false
                )

                if (showDatePicker) {
                    val datePickerState = rememberDatePickerState()
                    DatePickerDialog(
                        onDismissRequest = { showDatePicker = false },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        selectedDate = dateFormatter.format(Date(millis))
                                    }
                                    showDatePicker = false
                                }
                            ) {
                                Text("Подтвердить", color = MaterialTheme.colorScheme.primary)
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = { showDatePicker = false }
                            ) {
                                Text("Отмена", color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    ) {
                        DatePicker(
                            state = datePickerState,
                            modifier = Modifier.padding(16.dp),
                            title = {
                                Text(
                                    "Выберите дату рождения",
                                    modifier = Modifier.padding(16.dp),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Цвет глаз",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        eyeColors.take(2).forEach { color ->
                            SelectableItem(
                                text = color,
                                isSelected = selectedEyeColor == color,
                                color = when (color) {
                                    "Голубые" -> Color(0xFF15AAE5)
                                    "Карие" -> Color(0xFF60391C)
                                    "Серые" -> Color(0xFFB2B2B2)
                                    else -> Color(0xFF23DA23)
                                },
                                onClick = { selectedEyeColor = color },
                                modifier = Modifier.weight(1f) // Растягиваем элемент по ширине
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        eyeColors.drop(2).forEach { color ->
                            SelectableItem(
                                text = color,
                                isSelected = selectedEyeColor == color,
                                color = when (color) {
                                    "Голубые" -> Color(0xFF15AAE5)
                                    "Карие" -> Color(0xFF60391C)
                                    "Серые" -> Color(0xFFB2B2B2)
                                    else -> Color(0xFF23DA23)
                                },
                                onClick = { selectedEyeColor = color },
                                modifier = Modifier.weight(1f) // Растягиваем элемент по ширине
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp)) // Отступ для кнопки
            }

            FloatingActionButton(
                onClick = {
                    if (openedForEditing) {
                        relativesListViewModel.updateRelative(
                            selectedName, selectedEyeColor,
                            selectedHairColor, selectedDate, selectedBloodType, selectedId
                        )
                        openedForEditing = false
                    } else {
                        relativesListViewModel.addRelative(
                            RelativesEntity(
                                0, selectedName,
                                selectedEyeColor, selectedHairColor, selectedDate, selectedBloodType
                            )
                        )
                    }
                    selectedName = ""
                    selectedEyeColor = ""
                    selectedDate = ""
                    selectedBloodType = ""
                    selectedHairColor = ""
                    isAddMenuVisible = false
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .shadow(4.dp, CircleShape),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary,
                shape = CircleShape
            ) {
                Icon(Icons.Default.Check, contentDescription = "Подтвердить")
            }
        }
    }
}

@Composable
fun SelectableItem(
    text: String,
    isSelected: Boolean,
    color: Color?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier // Добавляем параметр modifier для гибкости
) {
    val scale by animateFloatAsState(if (isSelected) 1.05f else 1f)

    Row(
        modifier = modifier
            .height(48.dp)
            .scale(scale)
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
                else MaterialTheme.colorScheme.primary
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (color != null) Arrangement.SpaceBetween else Arrangement.Center
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp
        )
        if (color != null) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(color)
                    .then(
                        if (isSelected) Modifier.border(2.dp, Color.White, CircleShape)
                        else Modifier
                    )
            )
        }
    }
}

@Composable
fun RelativeCard(
    relative: RelativesEntity,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    var isHoveredEdit by remember { mutableStateOf(false) }
    var isHoveredDelete by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
                    )
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = relative.relativeName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = relative.dateofBirth,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(
                                    when (relative.eyeColor) {
                                        "Голубые" -> Color(0xFF15AAE5)
                                        "Карие" -> Color(0xFF60391C)
                                        "Серые" -> Color(0xFFB2B2B2)
                                        else -> Color(0xFF23DA23)
                                    }
                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = relative.eyeColor,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(
                                    when (relative.hairColor) {
                                        "Светлый" -> Color(0xFFE6D5A1)
                                        "Карий" -> Color(0xFF5B381E)
                                        "Рыжий" -> Color(0xFFD2691E)
                                        else -> Color(0xFF211E1C)
                                    }
                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = relative.hairColor,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row {
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .size(36.dp)
                        .scale(if (isHoveredEdit) 1.1f else 1f)
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(20.dp)
                            .onHover { isHoveredEdit = it }
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .size(36.dp)
                        .scale(if (isHoveredDelete) 1.1f else 1f)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(20.dp)
                            .onHover { isHoveredDelete = it }
                    )
                }
            }
        }
    }
}

@Composable
fun Modifier.onHover(onHover: (Boolean) -> Unit): Modifier = this.then(
    Modifier.pointerInput(Unit) {
        detectHover { onHover(it) }
    }
)

private suspend fun PointerInputScope.detectHover(onHover: (Boolean) -> Unit) {
    awaitPointerEventScope {
        while (true) {
            val event = awaitPointerEvent()
            when (event.type) {
                PointerEventType.Enter -> onHover(true)
                PointerEventType.Exit -> onHover(false)
            }
        }
    }
}

class RelativeListViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RelativesListViewModel(application) as T
    }
}