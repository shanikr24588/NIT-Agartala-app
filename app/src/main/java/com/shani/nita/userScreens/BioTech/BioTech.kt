package com.shani.nita.userScreens.BioTech

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shani.nita.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BioTech(navController: NavController){

    Surface(color = colorResource(id = R.color.surfaceColor)) {

        val branch = "BE"
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "BioTech Department",
                    fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = colorResource(
                        id = R.color.scaffoldColor
                    )),
                    navigationIcon = {
                        IconButton(onClick = {navController.navigateUp()}){
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                )
            },
            containerColor = Color.Transparent,
            content = {padding->
                Column(modifier = Modifier.padding(padding)){

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly){

                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("bio_faculty") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.collegefaculty),
                                    contentDescription = "faculty", modifier = Modifier
                                        .size(45.dp),
                                    tint = colorResource(id = R.color.IconColor)
                                )
                                Text("Faculty",
                                    modifier =Modifier
                                )


                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("bio_notes_semester/${branch}") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.notebook),
                                    contentDescription = "notes", modifier = Modifier
                                        .size(45.dp),
                                    tint = colorResource(id = R.color.IconColor)
                                )
                                Text("Notes",
                                    modifier =Modifier
                                )




                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("bio_pyq_semester/${branch}") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.questionpaper),
                                    contentDescription = "PYQ", modifier = Modifier
                                        .size(45.dp),
                                    tint = colorResource(id = R.color.IconColor)
                                )
                                Text("PYQ",
                                    modifier =Modifier,

                                )


                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("bio_pindocs_semester/${branch}") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.impdoc),
                                    contentDescription = "PinDocs", modifier = Modifier
                                        .size(45.dp),
                                    tint = colorResource(id = R.color.IconColor)
                                )
                                Text("Pin Doc",
                                    modifier =Modifier,

                                )


                            }

                        }





                    }

                }

            }
        )

    }




}