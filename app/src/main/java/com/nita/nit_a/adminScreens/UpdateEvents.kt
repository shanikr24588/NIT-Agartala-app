package com.nita.nit_a.adminScreens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nita.nit_a.R
import com.nita.nit_a.Widgets.LoadingDialog
import com.nita.nit_a.AdminPanel_itemview.EventsItemView
import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.EventsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEvents(navController: NavController){

    val context = LocalContext.current
    val eventsViewModel:EventsViewModel = viewModel()
    val isUploaded by eventsViewModel.isPosted.observeAsState(false)
    val isDeleted by eventsViewModel.isDeleted.observeAsState(false)
    val eventsList by eventsViewModel.eventsList.observeAsState(emptyList())
    LaunchedEffect(true) {
        eventsViewModel.getEvents()

    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var description by remember {
        mutableStateOf("")
    }

    var isEvents by remember {
        mutableStateOf(false)
    }
    val showLoader = remember {
        mutableStateOf(false)
    }
    if (showLoader.value){
        LoadingDialog(onDismissRequest = {showLoader.value = false})
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        imageUri = it

    }

    LaunchedEffect(isUploaded) {
        if (isUploaded)
            showLoader.value = false
            Toast.makeText(context, "Event Uploaded", Toast.LENGTH_SHORT).show()
        imageUri = null
        description = ""

    }
    LaunchedEffect(key1 = isDeleted) {
        if(isDeleted)
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Update Events") },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp()}) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null,
                        tint = Color.White
                    )

                }
            })
        },

        floatingActionButton = {
            FloatingActionButton(onClick = { isEvents = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)

            }
        }
    ){padding ->
         Column(modifier = Modifier.padding(padding)) {
             if (isEvents)
                 ElevatedCard(modifier = Modifier.padding(8.dp)){
                     Image(painter = if (imageUri == null) painterResource(id = R.drawable.scholarship_image)
                       else rememberAsyncImagePainter(model = imageUri),
                       contentDescription = "Events Poster",
                       modifier = Modifier.height(300.dp)
                           .fillMaxWidth()
                           .align(Alignment.CenterHorizontally)
                         .clickable { launcher.launch("image/*") },
                       contentScale = ContentScale.Fit
                     )

                     OutlinedTextField(
                         value = description,
                         onValueChange = { description = it },
                         placeholder = { Text(text = "Write About Event") },
                         modifier = Modifier
                             .padding(4.dp)
                             .fillMaxWidth()
                     )

                     Spacer(modifier = Modifier.padding(10.dp))

                     Row {
                         Button(
                             onClick = {
                                 if (description == "")
                                     Toast.makeText(context, "Please Add Description", Toast.LENGTH_SHORT)
                                         .show()
                                 else {
                                     showLoader.value = true
                                     eventsViewModel.saveEvents(imageUri!!, description)
                                     showLoader.value = false

                                 }
                                 isEvents = false


                             },
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .weight(1f)
                                 .padding(4.dp)
                         ) {
                             Text(text = "Upload Details")

                         }

                         OutlinedButton(
                             onClick = {
                                 imageUri = null
                                 description = ""
                             },
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .weight(1f)
                                 .padding(4.dp)
                         ) {
                             Text(text = "Cancel", color = Color.Black)

                         }

                     }
                 }


             LazyColumn() {
                 items(eventsList) {
                     EventsItemView(
                         eventsModel = it,
                         context = context,
                         delete = { eventsModel ->
                             eventsViewModel.deleteEvents(eventsModel)

                             })
                         }

                     }


         }
    }


}