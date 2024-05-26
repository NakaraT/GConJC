package com.example.myapplication.ui.components

import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.utils.SEARCH_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

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

    if (searchText.value.isEmpty()) {
        filteredNewsList.value = newsList.value
    }

    val trailingIconView = @Composable {
        IconButton(

            onClick = {
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
                        color = MaterialTheme.colorScheme.primary
                    )
                }

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
                            color = MaterialTheme.colorScheme.secondary) },

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
                        DropdownMenuItem(text = {
                            Text(text = item)
                        }, onClick = {
                            searchText.value = item // Обработка выбора элемента
                            filteredNewsList.value = newsList.value.filter {
                                it.contains(searchText.value, true)
                            }
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
                color = MaterialTheme.colorScheme.primary)
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
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 10.dp)

}




@Composable
fun NewsItem(newsItem: String) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {
        Text(
            text = newsItem,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}