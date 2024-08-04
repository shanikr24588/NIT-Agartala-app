package com.nita.nit_a.userScreens.MessMenu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nita.nit_a.Models.MessMenuModel

@Composable

fun MessMenuItemView(messmenuModel:MessMenuModel){
    Column(modifier = Modifier
        .fillMaxSize()) {

         ElevatedCard(modifier = Modifier
             .fillMaxWidth()
             .weight(1f)
             .height(intrinsicSize = IntrinsicSize.Max)
             .padding(8.dp),
             colors = CardDefaults.cardColors(containerColor = Color.Blue)
              ) {

             Column(modifier =  Modifier.fillMaxSize()) {

                 Text(text = "Breakfast", modifier = Modifier
                     .align(Alignment.CenterHorizontally)
                     .padding(top = 15.dp, bottom = 16.dp),
                     fontWeight = FontWeight.Bold,
                     fontSize = 22.sp,
                     color = Color.White
                 )

                 Text(
                     text = messmenuModel.breakfast!!,
                     modifier = Modifier
                         .align(Alignment.CenterHorizontally)
                         .padding(8.dp),
                     fontWeight = FontWeight.Bold,
                     color = Color.White,

                 )

             }

         }

        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Blue)) {

            Column(modifier = Modifier.fillMaxSize()) {

                Text(text = "Lunch", modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp, bottom = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )

                Text(
                    text = messmenuModel.lunch!!,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

            }

        }

        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Blue)
             ) {

            Column(modifier = Modifier.fillMaxSize()) {

                Text(text = "Dinner", modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 15.dp, bottom = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.White
                )

                Text(
                    text = messmenuModel.dinner!!,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

            }

        }

    }

}