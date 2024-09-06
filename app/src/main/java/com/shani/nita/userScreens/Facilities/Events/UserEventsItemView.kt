package com.shani.nita.userScreens.Facilities.Events

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.shani.nita.Models.EventsModel


@Composable
fun UserEventsItemView(eventsModel: EventsModel, context: Context) {
    ElevatedCard(modifier = Modifier.padding(10.dp)) {
        Column(modifier = Modifier
            .wrapContentSize(align = Alignment.Center),
//            .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = rememberAsyncImagePainter(model = eventsModel.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // Set aspect ratio to maintain image proportions
            )

            Text(text = eventsModel.description!!,
                modifier = Modifier
                    .fillMaxWidth().padding(start = 8.dp)
                    .align(Alignment.CenterHorizontally),

                fontSize = 15.sp
            )

        }
    }
}