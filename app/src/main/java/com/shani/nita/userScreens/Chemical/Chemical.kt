package com.shani.nita.userScreens.Chemical

import androidx.compose.foundation.Image
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
fun Chemical(navController: NavController){
    Surface(color = colorResource(id = R.color.surfaceColor)) {
        val branch = "Chemical Engineering"
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Chemical Department",
                    fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = colorResource(id = R.color.scaffoldColor)),
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
                            .clickable { navController.navigate("chemical_faculty") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Image(
                                    painter = painterResource(id = R.drawable.teacher),
                                    contentDescription = "faculty", modifier = Modifier
                                        .size(45.dp)
                                )
                                Text("Faculty", fontWeight = FontWeight.Bold,
                                    modifier =Modifier
                                )


                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("chemical_notes_semester/${branch}") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Image(
                                    painter = painterResource(id = R.drawable.read),
                                    contentDescription = "notes", modifier = Modifier
                                        .size(45.dp)
                                )
                                Text("Notes", fontWeight = FontWeight.Bold,
                                    modifier =Modifier
                                )




                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("chemical_pyq_semester/${branch}") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Image(
                                    painter = painterResource(id = R.drawable.collection),
                                    contentDescription = "PYQ", modifier = Modifier
                                        .size(45.dp)
                                )
                                Text("PYQ", fontWeight = FontWeight.Bold,
                                    modifier =Modifier

                                )


                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("chemical_pindocs_semester/${branch}") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Image(
                                    painter = painterResource(id = R.drawable.pindo),
                                    contentDescription = "Pin Docs", modifier = Modifier
                                        .size(45.dp)
                                )
                                Text("Pin Doc", fontWeight = FontWeight.Bold,
                                    modifier =Modifier

                                )


                            }

                        }





                    }

                }

            }
        )
    }




}