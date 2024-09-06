package com.shani.nita.UserPanel_ItemsView

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shani.nita.Models.BranchModel
import com.shani.nita.R

@Composable

fun EePdfItemView(branchModel: BranchModel,
                navController: NavController
                 ){

    ElevatedCard(modifier = Modifier
        .padding(4.dp)
        .padding(start = 4.dp, end = 4.dp)
        .fillMaxWidth()
        .clickable {
            navController.navigate("pdfScreen/${Uri.encode(branchModel.url)}")
        }) {

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
                text = branchModel.title!!,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
                    .wrapContentWidth(),
                fontSize = 15.sp
            )


        }

    }


}