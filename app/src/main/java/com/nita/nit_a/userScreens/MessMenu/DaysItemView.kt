package com.nita.nit_a.userScreens.MessMenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nita.nit_a.R

@Composable

fun DaysItemView(hostel:String, day:String, navController: NavController ){
    
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(6.dp)
                .clickable { navController.navigate("MessItem/${hostel}/${day}") },
            colors = CardDefaults.cardColors(
                containerColor =   colorResource(id = R.color.purple_700)
            ),
        ) {
            
            Text(text = day,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
                color = Color.White,
                modifier = Modifier.padding(8.dp)

            )

        }
        

    
    
    
    
}