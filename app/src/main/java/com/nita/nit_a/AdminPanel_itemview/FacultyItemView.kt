package com.nita.nit_a.AdminPanel_itemview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.nita.nit_a.Models.FacultyModel
import com.nita.nit_a.R

@Composable
fun FacultyItemView(facultyModel: FacultyModel, delete:(facultyModel:FacultyModel)->Unit) {
    OutlinedCard(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)) {
            val (image, delete) = createRefs()
            Column(modifier = Modifier.fillMaxWidth()) {

                Image(
                    painter = rememberAsyncImagePainter(model = facultyModel.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .align(Alignment.CenterHorizontally)
                        .height(120.dp)
                        .width(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text =  facultyModel.name!!,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),

                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.Orange)
                )
                Text(
                    text =  facultyModel.position!!,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp

                )
                Text(
                    text =  facultyModel.branch!!,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp

                )
                Text(
                    text =  facultyModel.interest!!,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),



                )
                Text(
                    text =  facultyModel.email!!,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    color = Color.Blue



                )
            }
            Card(modifier = Modifier
                .constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .clickable {
                    delete(facultyModel)
                }
            ){

                Image(painter = painterResource(id = R.drawable.delete),
                    contentDescription =null, modifier = Modifier.padding(8.dp)
                )

            }

        }

    }

}

