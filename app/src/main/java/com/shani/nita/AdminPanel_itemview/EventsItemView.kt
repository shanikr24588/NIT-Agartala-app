package com.shani.nita.AdminPanel_itemview

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.shani.nita.Models.EventsModel
import com.shani.nita.R


@Composable
fun EventsItemView(eventsModel:EventsModel,
                   delete:(eventsModel:EventsModel)-> Unit, context:Context){
    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {

            val (image, delete) = createRefs()
            Column(modifier = Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Image(painter = rememberAsyncImagePainter(model = eventsModel.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Fit
                )

                Text(text = eventsModel.description!!, modifier = Modifier.padding(start = 5.dp))

            }

            Card(modifier = Modifier
                .constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .clickable { delete(eventsModel) }){

                Image(painter = painterResource(id = R.drawable.delete),
                    contentDescription = null, modifier = Modifier.padding(8.dp) )

            }


        }

    }

}