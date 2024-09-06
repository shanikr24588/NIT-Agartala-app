package com.shani.nita.AdminPanel_itemview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.shani.nita.Models.MessMenuModel
import com.shani.nita.R

@Composable
 fun MessItemsItemView(messmenuModel:MessMenuModel,
                       hostel:String,
                       day:String,
                       docId:String,
                       delete:(hostel:String, day:String, docId:String)->Unit){

 OutlinedCard(modifier = Modifier
  .padding(4.dp)
  .fillMaxWidth()) {
  ConstraintLayout(modifier = Modifier
   .fillMaxWidth()
   .align(Alignment.CenterHorizontally)) {
   val (TextDetail, delete) = createRefs()
   Column(modifier = Modifier.fillMaxWidth()) {


    Text(
     text =  messmenuModel.breakfast!!,
     modifier = Modifier
      .align(Alignment.CenterHorizontally),

     fontWeight = FontWeight.Bold,
     fontSize = 14.sp

    )

    Spacer(modifier = Modifier.padding(10.dp))

    Text(
     text =  messmenuModel.lunch!!,
     modifier = Modifier
      .align(Alignment.CenterHorizontally),
     fontWeight = FontWeight.Bold,
     fontSize = 14.sp

    )

    Spacer(modifier = Modifier.padding(10.dp))


    Text(
     text =  messmenuModel.dinner!!,
     modifier = Modifier
      .align(Alignment.CenterHorizontally),
     fontWeight = FontWeight.Bold,
     fontSize = 14.sp

    )


   }
   Card(modifier = Modifier
    .constrainAs(TextDetail) {
     top.linkTo(parent.top)
     end.linkTo(parent.end)
    }
    .clickable {
     delete(hostel, day, docId)
    }
   ){

    Image(painter = painterResource(id = R.drawable.delete),
     contentDescription =null, modifier = Modifier.padding(8.dp)
    )

   }

  }

 }


}