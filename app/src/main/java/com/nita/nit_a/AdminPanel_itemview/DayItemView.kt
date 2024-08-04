package com.nita.nit_a.AdminPanel_itemview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.nita.nit_a.R


@Composable
fun DayItemView(hostel:String,day:String,
                 delete:(hostel:String, day:String) -> Unit,
                 navController: NavController
){
    OutlinedCard(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            navController.navigate("MessItems/${hostel}/${day}")
        }) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (dayText, delete) = createRefs()
            Text(
                text = day,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp,).padding(start = 15.dp)
                    .constrainAs(dayText){
                        start.linkTo(parent.start)
                        end.linkTo(delete.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)



                    },
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Card(modifier = Modifier
                .constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .clickable {
                    delete(hostel, day)
                }
            ){

                Image(painter = painterResource(id = R.drawable.delete),
                    contentDescription =null, modifier = Modifier.padding(8.dp)
                )

            }

        }

    }

}