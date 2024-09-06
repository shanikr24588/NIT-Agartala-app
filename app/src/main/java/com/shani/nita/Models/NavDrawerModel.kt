package com.shani.nita.Models

import androidx.compose.ui.graphics.vector.ImageVector

data class NavDrawerModel(
    val title :String,
    val route :String,
    val selectedIcon :Int,
    val unselectedIcon: ImageVector,
    val badgeCount :Int? = null
)
