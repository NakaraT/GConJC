package com.example.myapplication.ui.components


import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.LocalTheme
import com.example.myapplication.utils.EDIT_KEY
import com.example.myapplication.utils.SEARCH_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.internal.StringUtil.padding


@Composable
fun HomeScreen(navController:NavController, sharedPreferences: SharedPreferences) {
    val customFontFamily = FontFamily(
        Font(R.font.casual)
    )
    LocalTheme.value = sharedPreferences.getBoolean(EDIT_KEY, false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 0.dp)
    ) {
        IconButton(modifier = Modifier.align(alignment = Alignment.End),
            onClick = {
            LocalTheme.value = !LocalTheme.value
            sharedPreferences.edit().putBoolean(EDIT_KEY, LocalTheme.value).apply()

        }) {
            Icon(
                painter = painterResource(if (!LocalTheme.value) R.drawable.baseline_dark_mode_24 else R.drawable.baseline_light_mode_24),
                contentDescription = "theme switcher"

            )
        }
        Text(
            text = "GENETICS",
            fontSize = 54.sp,
            fontFamily = customFontFamily,
            color = colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 128.dp)
                .fillMaxWidth()
        )
        Text(
            text = "CALCULATOR",
            fontSize = 44.sp,
            fontFamily = customFontFamily,
            color = colorScheme.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 64.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_remove_red_eye_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
            Button(
                onClick =
                {
                    navController.navigate("eyes")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_eye),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ){
            Image(
                painter = painterResource(R.drawable.baseline_face_retouching_natural_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
            Button(
                onClick =
                {
                    /* Обработка нажатия */

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_hairs),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ){
            Image(
                painter = painterResource(R.drawable.baseline_water_drop_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
            Button(
                onClick =
                {
                    /* Обработка нажатия */

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_blood),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_back_hand_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
            Button(
                onClick =
                {
                    /* Обработка нажатия */

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_hand),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_new_releases_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )

            Button(
                onClick =
                {
                    navController.navigate("news")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_news),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.secondary)
            }
        }
    }
}

@Composable
fun DashboardScreen() {

    val parameters = listOf("A", "a")
    val selectedParam1 = remember { mutableStateOf(parameters[0]) }
    val selectedParam2 = remember { mutableStateOf(parameters[0]) }
    val selectedParam3 = remember { mutableStateOf(parameters[0]) }
    val selectedParam4 = remember { mutableStateOf(parameters[0]) }

    val cell1Text = remember { mutableStateOf("") }
    val cell2Text = remember { mutableStateOf("") }
    val cell3Text = remember { mutableStateOf("") }
    val cell4Text = remember { mutableStateOf("") }

    var expandedParam1 by remember { mutableStateOf(false) }
    var expandedParam2 by remember { mutableStateOf(false) }
    var expandedParam3 by remember { mutableStateOf(false) }
    var expandedParam4 by remember { mutableStateOf(false) }
    updateCellValues(
        selectedParam1,
        selectedParam2,
        selectedParam3,
        selectedParam4,
        cell1Text,
        cell2Text,
        cell3Text,
        cell4Text
    )
    LazyColumn(Modifier.padding(16.dp)) {
        item {
            Text(
                text = "A - Доминантный ген\na - Рецессивный ген",
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 16.dp),
                color = colorScheme.primary
            )
        }

// 1 cтолбец

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {

//1.1

            Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart,
                ) {
                Text(text = "A/a", color = colorScheme.primary)
                }

//1.2
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam1.value,
                        modifier = Modifier.clickable { expandedParam1 = true },
                        color = colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam1,
                        onDismissRequest = { expandedParam1 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam1.value = param
                                expandedParam1 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = colorScheme.primary)
                            }
                        }
                    }
                }

//1.3
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam2.value,
                        modifier = Modifier.clickable { expandedParam2 = true },
                        color = colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam2,
                        onDismissRequest = { expandedParam2 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam2.value = param
                                expandedParam2 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = colorScheme.primary)
                            }
                        }
                    }
                }
            }
        }

// 2 cтолбец

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
//2.1
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam3.value,
                        modifier = Modifier.clickable { expandedParam3 = true },
                        color = colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam3,
                        onDismissRequest = { expandedParam3 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam3.value = param
                                expandedParam3 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = colorScheme.primary)
                            }
                        }
                    }
                }

//2.2

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell1Text.value, color = colorScheme.primary)
                }

//2.3

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell2Text.value, color = colorScheme.primary)
                }
            }
        }

// 3 cтолбец

        item {
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
//3.1
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedParam4.value,
                        modifier = Modifier.clickable { expandedParam4 = true },
                        color = colorScheme.primary
                    )

                    DropdownMenu(
                        expanded = expandedParam4,
                        onDismissRequest = { expandedParam4 = false }
                    ) {
                        parameters.forEach { param ->
                            DropdownMenuItem(onClick = {
                                selectedParam4.value = param
                                expandedParam4 = false

                                updateCellValues(
                                    selectedParam1,
                                    selectedParam2,
                                    selectedParam3,
                                    selectedParam4,
                                    cell1Text,
                                    cell2Text,
                                    cell3Text,
                                    cell4Text
                                )
                            }) {
                                Text(text = param, color = colorScheme.primary)
                            }
                        }
                    }
                }

//3.2

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell3Text.value, color = colorScheme.primary)
                }

//3.3

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = cell4Text.value, color = colorScheme.primary)
                }
            }
        }

    }
}

// Функция для обновления значений ячеек
fun updateCellValues(
    selectedParam1: MutableState<String>,
    selectedParam2: MutableState<String>,
    selectedParam3: MutableState<String>,
    selectedParam4: MutableState<String>,
    cell1Text: MutableState<String>,
    cell2Text: MutableState<String>,
    cell3Text: MutableState<String>,
    cell4Text: MutableState<String>
) {
    cell1Text.value = "${selectedParam1.value}/${selectedParam3.value}"
    cell2Text.value = "${selectedParam2.value}/${selectedParam3.value}"
    cell3Text.value = "${selectedParam4.value}/${selectedParam1.value}"
    cell4Text.value = "${selectedParam4.value}/${selectedParam2.value}"
}

// Функция для получения комбинации генов
fun getGeneCombination(cross: String): String {
    if (cross.length < 3) {
        return "Invalid input"
    }

    val gene1 = cross.substring(0, 1)
    val gene2 = cross.substring(2, 3)

    return gene1 + gene2
}


@Composable
fun InformationScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 48.dp)
    ) {
        item {
            IconButton(
                onClick = { /* Действия по клику */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(128.dp),
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add",
                    tint = colorScheme.primary
                )
            }
        }

        items(2) { index ->
            RelativeItem()
        }
    }
}

@Composable
fun RelativeItem() {
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
                text = "Родственник",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = "Дата рождения",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}



@Composable
fun EyesScreen() {
    val colors = listOf("Неизвестно", "Карий", "Зелёный", "Голубой")
    val fatherEyeColor = remember { mutableStateOf(colors[0]) }
    val motherEyeColor = remember { mutableStateOf(colors[0]) }
    val result = remember { mutableStateOf(0.0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 64.dp), horizontalArrangement = Arrangement.SpaceAround
        ) {
            EyeColorDropdown("Папа", fatherEyeColor.value) { fatherEyeColor.value = it }
            EyeColorDropdown("Мама", motherEyeColor.value) { motherEyeColor.value = it }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 128.dp),
            onClick = {
                calculateProbability(
                    fatherEyeColor.value, motherEyeColor.value
                )
            }
        ) {
            Text("Расчитать вероятность")
        }

        // Вывод результата
        Text("Вероятность: ${result.value}",modifier = Modifier.padding(vertical = 184.dp),)
    }


@Composable
fun EyeColorDropdown(label: String, selectedColor: String, onColorSelected: (String) -> Unit) {
    val colors = listOf("Карий", "Зелёный", "Голубой")
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, fontSize = 20.sp)
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                selectedColor,
                color = colorScheme.primary,
                fontSize = 20.sp,
                modifier = Modifier.clickable { expanded = true }
            )

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
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = colorScheme.primary,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

fun calculateProbability(fatherEyeColor: String, motherEyeColor: String) {

}

@Composable
fun NewsScreen(sharedPreferences: SharedPreferences) {
    val newsList = remember { mutableStateOf(listOf<String>()) }
    val searchText = rememberSaveable { mutableStateOf("") }
    val filteredNewsList = remember { mutableStateOf(newsList.value) }
    val showClearButton = remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    val items = sharedPreferences.getStringSet(SEARCH_KEY, mutableSetOf())
    var localItems = mutableSetOf<String>()

    @Composable
    fun addNews() {
        rememberCoroutineScope().launch(Dispatchers.IO) {
            try {
                val document =
                    Jsoup.connect("https://ria.ru/keyword_genetika/").get()
                newsList.value = document
                    .getElementsByClass("list-item__title color-font-hover-only")
                    .map { it.text().toString() }
            }
            catch (e:Exception){

            }
        }
    }

    addNews()
    
    //мув гения
    if (searchText.value.isEmpty()) {
        filteredNewsList.value = newsList.value
    }

    val trailingIconView = @Composable {
        IconButton(onClick = {
            searchText.value = ""
            filteredNewsList.value = newsList.value
        }) {
            Icon(Icons.Filled.Close, contentDescription = "Close Button", modifier = Modifier.size(25.dp), tint = Color.Black)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (newsList.value.isEmpty()) {
            Column {
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        "Не удалось загрузить данные.",
                        modifier = Modifier.padding(32.dp),
                        color = colorScheme.primary
                    )
                }
//                CustomLinearProgressBar()
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    CustomCircularProgressBar()
                }

            }

        } else {
            Column {
                TextField(
                    value = searchText.value,
                    onValueChange = { searchText.value = it },
                    modifier = Modifier.padding(16.dp),
                    placeholder = {
                        Text(
                            "Поиск...",
                            modifier = Modifier.clickable{ expanded.value = true },
                            color = colorScheme.secondary) },

                    singleLine = true,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (searchText.value.isEmpty()) {
                                filteredNewsList.value = newsList.value
                            } else {
                                filteredNewsList.value = newsList.value.filter {
                                    it.contains(searchText.value, true)
                                }
                                localItems.add(searchText.value)
                                sharedPreferences.edit().putStringSet(SEARCH_KEY, localItems).apply()
                                showClearButton.value = true
                            }
                        }
                    ), trailingIcon = if (!searchText.value.isEmpty()) trailingIconView else null
                )
                DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
                    items?.forEach { item ->
                        androidx.compose.material3.DropdownMenuItem(text = {
                            Text(text = item)
                        }, onClick = {
                            searchText.value = item // Обработка выбора элемента
                            expanded.value = false // Скрытие меню
                        })
                    }
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        if (filteredNewsList.value.isEmpty()) {
            Text("Ничего не найдено.",
                modifier = Modifier.padding(32.dp),
                color = colorScheme.primary)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp),
                verticalArrangement = Arrangement.Top,
                content = {
                    items(filteredNewsList.value) { newsItem ->
                        NewsItem(newsItem)
                    }
                }
            )
        }
    }
}

//@Composable
//private fun CustomLinearProgressBar(){
//    Column(modifier = Modifier.fillMaxWidth()) {
//        LinearProgressIndicator(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(15.dp),
//            backgroundColor = Color.LightGray,
//            color = colorScheme.primary
//        )
//    }
//}

@Composable
private fun CustomCircularProgressBar(){
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp),
        color = colorScheme.primary,
        strokeWidth = 10.dp)

}




@Composable
fun NewsItem(newsItem: String) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Text(
            text = newsItem,
            modifier = Modifier.padding(16.dp),
            color = colorScheme.primary
        )
    }
}


//@Preview
//@Composable
//fun NewsScreenPreview(sharedPreferences: SharedPreferences) {
//    NewsScreen(sharedPreferences: SharedPreferences)
//}