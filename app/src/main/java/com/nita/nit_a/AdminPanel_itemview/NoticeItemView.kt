package com.nita.nit_a.AdminPanel_itemview

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController

import com.nita.nit_a.Models.NoticeModel
import com.nita.nit_a.R



@Composable
fun NoticeItem(noticeModel: NoticeModel,
               navController: NavController,
               delete:(noticeModel:NoticeModel) -> Unit) {


    OutlinedCard(modifier = Modifier
        .padding(4.dp)
        .clickable {
            navController.navigate("pdfScreen/${Uri.encode(noticeModel.url)}")
        }) {
        ConstraintLayout {
            val (image, delete) = createRefs()
            Column {
                Image(
                    painter = painterResource(id = R.drawable.pdf),
                    contentDescription = null,
                    modifier = Modifier,
                    contentScale = ContentScale.Crop

                )
                Text(
                    text = noticeModel.title!!,
                    modifier = Modifier.padding(start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
            Card(modifier = Modifier
                .constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .clickable {
                    delete(noticeModel)
                }
            ){

                Image(painter = painterResource(id = R.drawable.delete),
                    contentDescription =null, modifier = Modifier.padding(8.dp)
                )

            }

        }

    }

}











