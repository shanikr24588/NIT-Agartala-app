package com.shani.nita.adminScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shani.nita.R
import com.shani.nita.ui.theme.Purple40
import com.shani.nita.viewModel.AdminAuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AdminDashboard(navController: NavController) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val adminAuthViewModel:AdminAuthViewModel = viewModel()




    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Admin Dashboard",
                fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
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
        content = {padding->
            Column(modifier = Modifier
                .padding(padding)
                .fillMaxSize()){

                Box(modifier = Modifier.padding(top = 10.dp)){
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly){

                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("banner_update") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.banner),
                                    contentDescription = "banner", modifier = Modifier
                                        .size(45.dp)
                                )
                                Text("Add Banner", fontWeight = FontWeight.Bold,
                                    modifier =Modifier, fontSize = 10.sp
                                )


                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("notice_update") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.notice),
                                    contentDescription = null, modifier = Modifier
                                        .size(45.dp)
                                )
                                Text(" Add Notice", fontWeight = FontWeight.Bold,
                                    modifier =Modifier, fontSize = 10.sp
                                )




                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("notice_list") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.messmenu),
                                    contentDescription = "Lists", modifier = Modifier
                                        .size(45.dp)
                                )
                                Text("Lists", fontWeight = FontWeight.Bold,
                                    modifier =Modifier,
                                    fontSize = 12.sp
                                )


                            }

                        }
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("faculty_update") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Icon(
                                    painter = painterResource(id = R.drawable.faculty),
                                    contentDescription = "Faculty", modifier = Modifier
                                        .size(45.dp)
                                )
                                Text("Add Faculty", fontWeight = FontWeight.Bold,
                                    modifier =Modifier,
                                    fontSize = 10.sp
                                )


                            }

                        }





                    }

                }
                 Box(modifier = Modifier.padding(top = 10.dp)) {
                     Row(modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = Arrangement.SpaceEvenly) {
                         Card(modifier = Modifier
                             .height(70.dp)
                             .width(70.dp)
                             .clickable { navController.navigate("scholarship_update") },
                             shape = RoundedCornerShape(8.dp),
                             elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                             colors = CardDefaults.cardColors(containerColor = Color.White)){
                             Column(verticalArrangement = Arrangement.SpaceBetween,
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 modifier = Modifier.fillMaxSize()){
                                 Text("Update", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 12.sp
                                 )
                                 Text("Scholarship", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 10.sp
                                 )



                             }

                         }

                         Card(modifier = Modifier
                             .height(70.dp)
                             .width(70.dp)
                             .clickable { navController.navigate("events_update") },
                             shape = RoundedCornerShape(8.dp),
                             elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                             colors = CardDefaults.cardColors(containerColor = Color.White)){
                             Column(verticalArrangement = Arrangement.SpaceBetween,
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 modifier = Modifier.fillMaxSize()){
                                 Text("Update", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 12.sp
                                 )
                                 Text("Events", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 12.sp
                                 )



                             }

                         }

                         Card(modifier = Modifier
                             .height(70.dp)
                             .width(70.dp)
                             .clickable { navController.navigate("messmenu_update") },
                             shape = RoundedCornerShape(8.dp),
                             elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                             colors = CardDefaults.cardColors(containerColor = Color.White)){
                             Column(verticalArrangement = Arrangement.SpaceBetween,
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 modifier = Modifier.fillMaxSize()){
                                 Text("Update", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 12.sp
                                 )
                                 Text("MessMemu", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 12.sp
                                 )



                             }

                         }

                         Card(modifier = Modifier
                             .height(70.dp)
                             .width(70.dp)
                             .clickable { navController.navigate("department_update") },
                             shape = RoundedCornerShape(8.dp),
                             elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                             colors = CardDefaults.cardColors(containerColor = Color.White)){
                             Column(verticalArrangement = Arrangement.SpaceBetween,
                                 horizontalAlignment = Alignment.CenterHorizontally,
                                 modifier = Modifier.fillMaxSize()){
                                 Text("Update", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 12.sp
                                 )
                                 Text("Departments", fontWeight = FontWeight.Bold,
                                     modifier =Modifier,
                                     fontSize = 10.sp
                                 )



                             }

                         }




                     }

                 }

                Box(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp).fillMaxSize()) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("Notes_Pyq_PinDocs") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Text("Notes, Pyq", fontWeight = FontWeight.Bold,
                                    modifier =Modifier,
                                    fontSize = 10.sp
                                )
                                Text("PinDocs", fontWeight = FontWeight.Bold,
                                    modifier =Modifier,
                                    fontSize = 10.sp
                                )



                            }

                        }

                        Card(modifier = Modifier
                            .height(70.dp)
                            .width(70.dp)
                            .clickable { navController.navigate("calender") },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Column(verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()){
                                Text("Calender", fontWeight = FontWeight.Bold,
                                    modifier =Modifier,
                                    fontSize = 10.sp
                                )




                            }

                        }

                    }

                    Button(onClick = {
                        navController.navigate("home"){
                            navController.popBackStack()
                        }
                    }, modifier = Modifier.align(Alignment.BottomCenter)) {

                        Text(text = "LogOut")


                    }

                }




            }

        }
    )





}