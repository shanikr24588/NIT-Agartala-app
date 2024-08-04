package com.nita.nit_a.userScreens.Facilities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nita.nit_a.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chat(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Chat",
                fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {padding->
            Column(modifier = Modifier.padding(padding)){

            }

        }
    )



}