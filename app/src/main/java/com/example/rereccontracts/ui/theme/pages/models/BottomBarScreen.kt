package com.example.rereccontracts.ui.theme.pages.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Contracts: BottomBarScreen(
        route = "contracts",
        title = "Contracts",
        icon = Icons.Default.Home

    )
    object Licences: BottomBarScreen(
        route = "licences",
        title = "Licences",
        icon = Icons.Default.Book

    )
}
