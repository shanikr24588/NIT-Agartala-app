package com.nita.nit_a.AdminPanel_itemview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter

import com.nita.nit_a.Models.BannerModel
import com.nita.nit_a.R

@Composable

fun BannerItemView (bannerModel: BannerModel,
                    delete:(docId:BannerModel) -> Unit) {
    OutlinedCard(modifier = Modifier.padding(4.dp)) {
        ConstraintLayout {
            val (image, delete) = createRefs()
            Image(painter = rememberAsyncImagePainter(model = bannerModel.url),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Card (modifier = Modifier
                .clickable { delete(bannerModel) }
                .constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }

            ){

                Image(painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete button",
                    modifier = Modifier.padding(10.dp)
                )

            }

        }

    }


}