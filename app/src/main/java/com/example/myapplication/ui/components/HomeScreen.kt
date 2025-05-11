package com.example.myapplication.ui.components

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.LocalTheme
import com.example.myapplication.utils.EDIT_KEY

@Composable
fun HomeScreen(navController: NavController, sharedPreferences: SharedPreferences) {
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
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxWidth()
        )
        Text(
            text = "CALCULATOR",
            fontSize = 44.sp,
            fontFamily = customFontFamily,
            color = MaterialTheme.colorScheme.secondary,
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
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
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

                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_eye),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ){
            Image(
                painter = painterResource(R.drawable.baseline_face_retouching_natural_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
            Button(
                onClick =
                {
                    navController.navigate("hair")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_hairs),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ){
            Image(
                painter = painterResource(R.drawable.baseline_water_drop_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
            Button(
                onClick =
                {
                    navController.navigate("blood")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_blood),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_back_hand_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
            Button(
                onClick =
                {
                    navController.navigate("hand")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 16.dp, end = 48.dp),

                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_hand),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_new_releases_24),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
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

                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Text(text = stringResource(id = R.string.button_news),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}