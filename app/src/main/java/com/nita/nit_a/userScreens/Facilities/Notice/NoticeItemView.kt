package com.nita.nit_a.userScreens.Facilities.Notice

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nita.nit_a.Models.NoticeModel
import com.nita.nit_a.R

@Composable

fun NoticeItemView(noticeModel: NoticeModel,
                   navController: NavController){

    ElevatedCard(modifier = Modifier
        .padding(4.dp)
        .padding(start = 4.dp, end = 4.dp)
        .fillMaxWidth()
        .clickable {
            navController.navigate("pdfScreen/${Uri.encode(noticeModel.url)}")
        },
        shape = CardDefaults.outlinedShape,
        ) {

        Row(modifier = Modifier.fillMaxWidth(), Arrangement.Start) {

            Image(
                painter = painterResource(id = R.drawable.pdffile),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .size(50.dp),
                contentScale = ContentScale.Crop

            )
            Text(
                text = noticeModel.title!!,
                modifier = Modifier.padding(start = 10.dp)
                    .wrapContentWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )


        }

    }


}