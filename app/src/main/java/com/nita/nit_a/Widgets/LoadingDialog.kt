package com.nita.nit_a.Widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingDialog(
    onDismissRequest :() -> Unit
){
    Dialog(onDismissRequest = { onDismissRequest }) {

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {

            Column(modifier = Modifier.align(Alignment.Center)) {

                CircularProgressIndicator(color = Color.Red)

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Loading...",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    fontSize = 16.sp
                )

            }
        }

    }

}