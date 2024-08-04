package com.nita.nit_a.userScreens.Facilities.Events

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nita.nit_a.Models.EventsModel


@Composable
fun UserEventsItemView(eventsModel:EventsModel, context:Context){

    ElevatedCard(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            Alignment.CenterHorizontally) {

            Image(painter = rememberAsyncImagePainter(model = eventsModel.imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .height(220.dp) ,
                contentScale = ContentScale.Crop
            )

            Text(text = eventsModel.description!!,
                modifier = Modifier
                    .padding(start = 8.dp)
                .fillMaxWidth().align(Alignment.CenterHorizontally))

        }

    }










}