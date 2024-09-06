package com.shani.nita.adminScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.shani.nita.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Notes_Pyq_PinDocsScreen( navController: NavController){


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text =  "Documents Types",
                fontWeight = FontWeight.Bold, color = Color.White, fontSize = 22.sp ) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()
                    }){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
    ){padding->

        Column(modifier = Modifier.padding(padding)) {

            ElevatedCard(modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
                .padding(top = 10.dp, start = 8.dp)
                .clickable { navController.navigate("notes_branch_screen")}) {

                Text(text = "NOTES",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )

            }


            ElevatedCard(modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
                .padding(top = 10.dp, start = 8.dp)
                .clickable { navController.navigate("pyq_branch_screen")}) {

                Text(text = "PYQs",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )

            }


            ElevatedCard(modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
                .padding(top = 10.dp, start = 8.dp)
                .clickable { navController.navigate("pindocs_branch_screen")}) {

                Text(text = "PinDocs",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )

            }

        }

    }



}