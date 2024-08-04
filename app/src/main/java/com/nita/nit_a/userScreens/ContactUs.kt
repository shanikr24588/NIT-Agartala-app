package com.nita.nit_a.userScreens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.nita.nit_a.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUs(navController:NavController ) {

    val context = LocalContext.current


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "ContactUs",
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
    ){padding->

        fun openEmailClient(emailAddress: String) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$emailAddress")
            }
            context.startActivity(intent)
        }

        Column(modifier = Modifier.padding(padding).padding(16.dp)){
            Text(
                text = "We'd love to hear from you! If you have any questions or need assistance, please email us at:",
                fontSize = 16.sp,

            )
            Text(
                text = "shanikr24588@gmail.com",
                color = Color.Blue,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable { openEmailClient("shanikr24588@gmail.com") }
                    .padding(top = 8.dp)
            )

        }

    }
}

