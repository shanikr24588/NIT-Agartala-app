package com.shani.nita.adminScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shani.nita.AdminPanel_itemview.MessItemsItemView
import com.shani.nita.ui.theme.Purple40
import com.shani.nita.viewModel.MessMenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MessItemScreen(navController: NavController, hostel:String, day:String){

    val messmenuViewModel: MessMenuViewModel = viewModel()


    val loading by messmenuViewModel.loading.observeAsState(false)

    val messmenuList by messmenuViewModel.messmenuList.observeAsState(emptyList())

    LaunchedEffect(true) {
        messmenuViewModel.getMessMenu(hostel, day)

    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = day,
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
    ){padding->
        if(loading){
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {

                items(messmenuList){messmenuModel ->
                    MessItemsItemView(messmenuModel = messmenuModel, hostel , day,
                        messmenuModel.docId!! , delete = {hostel, day, docId ->
                            messmenuViewModel.deleteMessMenu(hostel, day, docId)
                        })

                }

            }
        }


    }

}