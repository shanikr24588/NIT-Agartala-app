package com.nita.nit_a.UserPanel_ItemsView

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.nita.nit_a.Models.FacultyModel
import com.nita.nit_a.R

@Composable

fun UserFacultyItemView(facultyModel: FacultyModel){

    val context = LocalContext.current

    OutlinedCard(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
    ) {
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
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),

                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = colorResource(id = R.color.Orange)
            )
            Text(
                text =  facultyModel.position!!,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp

            )
            Text(
                text =  facultyModel.branch!!,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp

            )
            Text(
                text =  facultyModel.interest!!,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                fontSize = 13.sp
                     


            )
            ClickableText(
                text =  AnnotatedString(facultyModel.email!!) ,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                onClick = {
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data= Uri.parse("mailto:${facultyModel.email}")
                    context.startActivity(intent)
                },
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 17.sp
                )
            )





        }
    }



}