package com.example.myapplication.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.myapplication.R

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

fun calculateEyeProbability(fatherEyeColor: String, motherEyeColor: String): String {
    return when {
        fatherEyeColor == "Карий" && motherEyeColor == "Карий" -> "75% Карие\n18,75% Голубые\n6,25% Зелёные"
        fatherEyeColor == "Голубой" && motherEyeColor == "Голубой" -> "<1% Карие\n99% Голубые\n1% Зелёные"
        fatherEyeColor == "Зелёный" && motherEyeColor == "Зелёный" -> "1% Карие\n24% Голубые\n75% Зелёные"
        fatherEyeColor == "Зелёный" && motherEyeColor == "Карий" -> "50% Карие\n37,5% Голубые\n12,5% Зелёные"
        fatherEyeColor == "Карий" && motherEyeColor == "Зелёный" -> "50% Карие\n37,5% Голубые\n12,5% Зелёные"
        fatherEyeColor == "Карий" && motherEyeColor == "Голубой" -> "50% Карие\n50% Голубые\n<1% Зелёные"
        fatherEyeColor == "Голубой" && motherEyeColor == "Карий" -> "50% Карие\n50% Голубые\n<1% Зелёные"
        fatherEyeColor == "Голубой" && motherEyeColor == "Зелёный" -> "<1% Карие\n50% Голубые\n50% Зелёные"
        fatherEyeColor == "Зелёный" && motherEyeColor == "Голубой" -> "<1% Карие\n50% Голубые\n50% Зелёные"
        else -> "Пожалуйста, выберите цвет глаз обоих родителей."
    }
}

@Composable
fun getEyeColorFromName(color: String): Color {
    return when (color) {
        "Карий", "Карие" -> colorResource(id = R.color.brown_eye)
        "Голубые", "Голубой" -> colorResource(id = R.color.blue_eye)
        "Зелёные", "Зелёный" -> colorResource(id = R.color.green_eye)
        else -> colorResource(id = R.color.default_circle)
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

@Composable
fun getHairColorFromName(color: String): Color {
    return when (color) {
        "Светлый" -> colorResource(id = R.color.light_hair)
        "Коричневый" -> colorResource(id = R.color.brown_hair)
        "Рыжий" -> colorResource(id = R.color.red_hair)
        "Черный" -> colorResource(id = R.color.dark_hair)
        else -> colorResource(id = R.color.default_circle)
    }
}

fun calculateHandProbability(fatherHand: String, motherHand: String): String {
    return when {
        fatherHand == "Левша" && motherHand == "Левша" -> "80% Правша\n20% Левша"
        fatherHand == "Левша" && motherHand == "Правша" -> "88% Правша\n12% Левша"
        fatherHand == "Правша" && motherHand == "Левша" -> "84% Правша\n16% Левша"
        fatherHand == "Правша" && motherHand == "Правша" -> "91% Правша\n9% Левша"
        else -> "Пожалуйста, укажите основные руки обоих родителей для рассчета вероятности."
    }
}