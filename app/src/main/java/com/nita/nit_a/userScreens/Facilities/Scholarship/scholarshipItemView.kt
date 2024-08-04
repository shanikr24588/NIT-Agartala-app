package com.nita.nit_a.userScreens.Facilities.Scholarship

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.nita.nit_a.Models.ScholarshipModel


@Composable
fun UserScholarshipItemView(scholarshipModel: ScholarshipModel,context:Context ){

    OutlinedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)){
             Column(modifier = Modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {

                Image(painter = rememberAsyncImagePainter(model = scholarshipModel.imageUrl),
                    contentDescription = null, modifier = Modifier.fillMaxWidth().height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Name: ", modifier = Modifier.padding(start = 5.dp, end = 10.dp), color = Color.Red)
                    Text(text = scholarshipModel.name!!, fontWeight = FontWeight.Bold)
                }

                Row(modifier = Modifier.fillMaxWidth() ) {
                    Text(text = "Eligibility: ", modifier = Modifier.padding(start = 5.dp, end = 10.dp), color = Color.Red)
                    Text(text = scholarshipModel.eligibity!!)
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Date: ", modifier = Modifier.padding(start = 5.dp, end = 10.dp), color = Color.Red)
                    Text(text = scholarshipModel.date!!)
                }

                Row(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)) {
                    Text(text = "URL: ", modifier = Modifier.padding(start = 5.dp, end = 10.dp), color = Color.Red)
                     ClickableText(text = buildAnnotatedString { append(scholarshipModel.url!!) },
                         onClick = {
                             val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scholarshipModel.url))
                             context.startActivity(intent)
                         },
                         style = TextStyle(color = Color.Blue)
                     )
                }


            }




    }

}