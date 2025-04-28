package com.example.myapplication.ui.components

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.myapplication.utils.SEARCH_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

data class NewsItemData(val title: String, val link: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(sharedPreferences: SharedPreferences) {
    val newsList = remember { mutableStateOf(listOf<NewsItemData>()) }
    val searchText = rememberSaveable { mutableStateOf("") }
    val filteredNewsList = remember { mutableStateOf(listOf<NewsItemData>()) }
    val showClearButton = remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    val savedSearchQueries = sharedPreferences.getStringSet(SEARCH_KEY, mutableSetOf()) ?: mutableSetOf()
    val localItems = remember { mutableStateOf(savedSearchQueries.toMutableSet()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        loadNews(newsList)
    }

    LaunchedEffect(searchText.value) {
        filteredNewsList.value = if (searchText.value.isEmpty()) {
            newsList.value
        } else {
            newsList.value.filter {
                it.title.contains(searchText.value, ignoreCase = true)
            }
        }
    }
    LaunchedEffect(newsList.value) {
        if (searchText.value.isEmpty()) {
            filteredNewsList.value = newsList.value
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (newsList.value.isEmpty()) {
            EmptyState()
        } else {
            SearchBar(
                searchText = searchText.value,
                onTextChange = { newText -> searchText.value = newText },
                onSearch = {
                    localItems.value.add(searchText.value)
                    CoroutineScope(Dispatchers.IO).launch {
                        sharedPreferences.edit().putStringSet(SEARCH_KEY, localItems.value).apply()
                    }
                    showClearButton.value = true
                },
                onClear = {
                    searchText.value = ""
                },
                suggestions = localItems.value.toList(),
                expanded = expanded
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredNewsList.value.isEmpty()) {
                Text(
                    "Ничего не найдено.",
                    modifier = Modifier.padding(32.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Top,
                    content = {
                        items(filteredNewsList.value, key = { it.link }) { newsItem ->
                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn() + expandVertically()
                            ) {
                                NewsItem(newsItem) {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.link))
                                    context.startActivity(intent)
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

private fun loadNews(newsList: MutableState<List<NewsItemData>>) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val document = Jsoup.connect("https://ria.ru/keyword_genetika/").get()
            val loadedNews = document
                .getElementsByClass("list-item__title color-font-hover-only")
                .map { element ->
                    val title = element.text()
                    val link = element.attr("href")
                    val fullLink = if (link.startsWith("http")) link else "https://ria.ru$link"
                    NewsItemData(title, fullLink)
                }
            newsList.value = loadedNews
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClear: () -> Unit,
    suggestions: List<String>,
    expanded: MutableState<Boolean>
) {
    Column {
        TextField(
            value = searchText,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.secondary),
            onValueChange = { onTextChange(it) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.padding(16.dp),
            placeholder = {
                Text(
                    "Поиск...",
                    modifier = Modifier.clickable { expanded.value = true },
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = { onSearch() }
            ),
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    IconButton(onClick = onClear) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "Close Button",
                            modifier = Modifier.size(25.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        )
        DropdownMenu(expanded = expanded.value, onDismissRequest = { expanded.value = false }) {
            suggestions.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onTextChange(item)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Composable
fun NewsItem(newsItem: NewsItemData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        Text(
            text = newsItem.title,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Не удалось загрузить данные.",
            modifier = Modifier.padding(32.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomCircularProgressBar()
    }
}

@Composable
private fun CustomCircularProgressBar() {
    CircularProgressIndicator(
        modifier = Modifier.size(64.dp),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 10.dp
    )
}
