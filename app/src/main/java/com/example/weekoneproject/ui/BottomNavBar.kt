package com.example.weekoneproject.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavBar() {
    NavigationBar(containerColor = Color.Black) {
        val items = listOf("Browse", "Watchlist", "Downloads", "Search")
        val icons = listOf(Icons.Filled.Menu, Icons.Filled.List, Icons.Filled.Star, Icons.Filled.Search)
        var selectedItem by remember { mutableStateOf(0) }

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item, tint = if (selectedItem == index) Color.White else Color.Gray) },
                label = { Text(item, color = if (selectedItem == index) Color.White else Color.Gray) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}
