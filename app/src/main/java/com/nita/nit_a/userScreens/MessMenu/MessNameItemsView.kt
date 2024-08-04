package com.nita.nit_a.userScreens.MessMenu

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
fun MessNameItemView(hostel:String, navController: NavController){

    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .size(65.dp)
        .clickable { navController.navigate("user_day/${hostel}") }
        ,colors = CardDefaults.cardColors(containerColor = Color.LightGray)) {

        Text(
            text = hostel,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp, top = 15.dp, bottom = 15.dp)
        )

    }


}