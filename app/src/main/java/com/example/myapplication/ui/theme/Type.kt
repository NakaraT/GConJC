package com.example.myapplication.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

val abelFamily = FontFamily(
    Font(R.font.casual, FontWeight.Normal),
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 56.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 16.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        color = Color.Black,
        fontSize = 14.sp
    )
)
