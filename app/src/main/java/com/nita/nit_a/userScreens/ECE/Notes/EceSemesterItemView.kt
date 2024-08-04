package com.nita.nit_a.userScreens.ECE.Notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable

fun EceSemesterItemView(navController: NavController,
                          branch:String,
                          semester:String,
                          ){

     ElevatedCard(modifier = Modifier
         .padding(4.dp)
         .fillMaxWidth()
         .size(75.dp)
         .clickable {
             navController.navigate("ece_notes/${branch}/${semester}")
         },
         colors = CardDefaults.cardColors(containerColor = Color.Blue)) {

         Text(
             text = semester,
             fontWeight = FontWeight.Bold,
             fontSize = 20.sp,
             modifier = Modifier.padding(8.dp),
             color = Color.White
         )

     }
}