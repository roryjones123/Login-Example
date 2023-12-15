package com.rozworks.features.presentation.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.rozworks.core.navigation.NavigationDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodsterTopBar(navController: NavController) {
    var text by remember { mutableStateOf("App") }

    Row(
        Modifier
            .background(color = MaterialTheme.colorScheme.secondary)
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = { }, modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search icon",
                tint = MaterialTheme.colorScheme.background,
            )
        }

        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.background,
                unfocusedTextColor = MaterialTheme.colorScheme.background,
                cursorColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            value = text,
            onValueChange = {
                if (it.length > 2) {
                    navController.navigate(NavigationDestination.Search.route)
                }
                text = it
            },
            modifier = Modifier
                .align(CenterVertically)
                .weight(6f),
            singleLine = true
        )

        IconButton(
            onClick = { }, modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "Settings icon",
                tint = MaterialTheme.colorScheme.background,
            )
        }
    }
}