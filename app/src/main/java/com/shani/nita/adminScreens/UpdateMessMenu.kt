package com.shani.nita.adminScreens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shani.nita.AdminPanel_itemview.MessItemView
import com.shani.nita.Widgets.LoadingDialog
import com.shani.nita.ui.theme.Purple40
import com.shani.nita.viewModel.MessMenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateMessMenu(navController: NavController){

    val context = LocalContext.current

    var breakfast by remember {
        mutableStateOf("")
    }
    var lunch by remember {
        mutableStateOf("")
    }
    var dinner by remember {
        mutableStateOf("")
    }
    var day by remember {
        mutableStateOf("")
    }
    var hostel by remember {
        mutableStateOf("")
    }
    var ishostel by remember {
        mutableStateOf(false)
    }
    var isday by remember {
        mutableStateOf(false)
    }
    var isItems by remember {
        mutableStateOf(false)
    }
    var hExpanded by remember {
        mutableStateOf(false)
    }
    var dExpanded by remember {
        mutableStateOf(false)
    }

    val messmenuViewModel:MessMenuViewModel = viewModel()
    val isUploaded by messmenuViewModel.isPosted.observeAsState(false)
    val isDeleted by messmenuViewModel.isDeleted.observeAsState(false)
    val hostelList by messmenuViewModel.hostelList.observeAsState(emptyList())
    val dayList by messmenuViewModel.dayList.observeAsState(emptyList())
    val messmenuList by messmenuViewModel.messmenuList.observeAsState(emptyList())

    val showLoader = remember {
        mutableStateOf(false)
    }

    if(showLoader.value){
        LoadingDialog(onDismissRequest = {
            showLoader.value = false
        })
    }




    LaunchedEffect(true) {
        messmenuViewModel.gethostel()
    }

    // Fetch days when hostel changes
    LaunchedEffect(hostel) {
        if (hostel.isNotBlank()) {
            messmenuViewModel.getday(hostel)
        }
    }



    val option_day = arrayListOf<String>()
    val option_hostel = arrayListOf<String>()

    LaunchedEffect(isUploaded) {
        showLoader.value = false
        Toast.makeText(context, "Data Uploaded", Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(isDeleted) {
        Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
    }



    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Update Mess Menu", fontWeight = FontWeight.Bold)},
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )

                    }
                }
            )
        }
    ) {padding->
        Column(modifier = Modifier.padding(padding)) {
            Row(horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {
                Card(modifier = Modifier
                    .weight(1f)
                    .clickable {
                        isItems = false
                        isday = false
                        ishostel = true
                    }) {
                    Text(text = "Add Mess",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                }

                Card(modifier = Modifier
                    .weight(1f)
                    .clickable {
                        isItems = false
                        isday = true
                        ishostel = false
                    }) {
                    Text(text = "Add Day",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                }

                Card(modifier = Modifier
                    .weight(1f)
                    .clickable {
                        isItems = true
                        isday = false
                        ishostel = false
                    }) {
                    Text(text = "Add Items",modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Bold
                    )

                }

            }

            if(ishostel)
                ElevatedCard(modifier = Modifier.padding(8.dp)) {
                     OutlinedTextField(value = hostel,
                         onValueChange = {hostel = it},
                         placeholder = { Text(text = "Add Mess")},
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(4.dp)
                     )

                    Row{
                        Button(onClick = {if (hostel == "") {
                            Toast.makeText(context, "Please insert Mess", Toast.LENGTH_SHORT).show()

                        } else
                            showLoader.value = true
                            messmenuViewModel.uploadhostel(hostel)
                            hostel = ""
                                         },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Add Mess")

                        }
                        OutlinedButton(onClick = {

                            ishostel = false
                            isday = false
                            isItems = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Cancel", color = Color.Black)

                        }

                    }

                }
            if(isday)
                ElevatedCard(modifier = Modifier.padding(8.dp)) {

                    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                        OutlinedTextField(value = hostel,
                            onValueChange = {hostel = it},
                            readOnly = true,
                            placeholder = {
                                Text(text = "Select Mess")
                            },
                            label = { Text(text = "Mess Name")},
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = hExpanded)
                            }

                        )
                        DropdownMenu(expanded = hExpanded,
                            onDismissRequest = { hExpanded = false}) {
                            if(hostelList != null && hostelList!!.isNotEmpty()) {
                                option_hostel.clear()
                                for(data in hostelList!!){
                                    option_hostel.add(data)
                                }

                            }
                            option_hostel.forEach { selectedOption->
                                DropdownMenuItem(text = { Text(text = selectedOption)},
                                    onClick = {
                                        hostel = selectedOption
                                        hExpanded = false
                                    }, modifier = Modifier.fillMaxWidth()
                                )

                            }


                        }

                        Spacer(modifier = Modifier
                            .matchParentSize()
                            .padding(10.dp)
                            .clickable { hExpanded = !hExpanded }
                        )

                    }

                    OutlinedTextField(value = day,
                        onValueChange = {day = it},
                        placeholder = { Text(text = "Add Day")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )

                    Row{
                        Button(onClick = {if (day == "") {
                            Toast.makeText(context, "Please Add Day", Toast.LENGTH_SHORT).show()

                        } else
                            showLoader.value = true
                            messmenuViewModel.uploadDay(hostel, day)
                            day = ""

                        },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Add Day")

                        }
                        OutlinedButton(onClick = {

                            ishostel = false

                            isItems = false
                            hostel = ""
                            day = ""
                                                 },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Cancel", color = Color.Black)

                        }

                    }

                }
            if(isItems)
                ElevatedCard(modifier = Modifier.padding(8.dp)) {
                    OutlinedTextField(value = breakfast,
                        onValueChange = {breakfast = it},
                        placeholder = { Text(text = "Enter BreakFast Items")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )

                    OutlinedTextField(value = lunch,
                        onValueChange = {lunch = it},
                        placeholder = { Text(text = "Enter Lunch Items")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )

                    OutlinedTextField(value = dinner,
                        onValueChange = {dinner = it},
                        placeholder = { Text(text = "Enter Dinner Items")},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )


                    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                        OutlinedTextField(value = hostel,
                            onValueChange = {hostel = it},
                            readOnly = true,
                            placeholder = {
                                Text(text = "Select Hostel")
                            },
                            label = { Text(text = "Hostels Name")},
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = hExpanded)
                            }

                        )
                        DropdownMenu(expanded = hExpanded,
                            onDismissRequest = { hExpanded = false}) {
                            if(hostelList != null && hostelList!!.isNotEmpty()) {
                                option_hostel.clear()
                                for(data in hostelList!!){
                                    option_hostel.add(data)
                                }

                            }
                            option_hostel.forEach { selectedOption->
                                DropdownMenuItem(text = { Text(text = selectedOption)},
                                    onClick = {
                                        hostel = selectedOption
                                        hExpanded = false
                                    }, modifier = Modifier.fillMaxWidth()
                                )

                            }


                        }

                        Spacer(modifier = Modifier
                            .matchParentSize()
                            .padding(10.dp)
                            .clickable { hExpanded = !hExpanded }
                        )

                    }

                    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                        OutlinedTextField(value = day,
                            onValueChange = {day = it},
                            readOnly = true,
                            placeholder = {
                                Text(text = "Select Day")
                            },
                            label = { Text(text = "Days Name")},
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = dExpanded)
                            }

                        )
                        DropdownMenu(expanded = dExpanded,
                            onDismissRequest = { dExpanded = false}) {
                            if(dayList != null && dayList!!.isNotEmpty()) {
                                option_day.clear()
                                for(data in dayList!!){
                                    option_day.add(data)
                                }

                            }
                            option_day.forEach { selectedOption->
                                DropdownMenuItem(text = { Text(text = selectedOption)},
                                    onClick = {
                                        day = selectedOption
                                        dExpanded = false
                                    }, modifier = Modifier.fillMaxWidth()
                                )

                            }


                        }

                        Spacer(modifier = Modifier
                            .matchParentSize()
                            .padding(10.dp)
                            .clickable { dExpanded = !dExpanded }
                        )

                    }


                    Row{
                        Button(onClick = {if (breakfast == "" || lunch == "" || dinner == "") {
                            Toast.makeText(context, "Please insert Hostel", Toast.LENGTH_SHORT).show()

                        } else
                            showLoader.value = true
                            messmenuViewModel.saveMessMenu(breakfast, lunch, dinner, hostel, day)
                            breakfast = ""
                            lunch = ""
                            dinner = ""
                            day = ""
                        },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Add Menu")

                        }
                        OutlinedButton(onClick = {

                            ishostel = false
                            isday = false
                            isItems = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(4.dp)) {
                            Text(text = "Cancel", color = Color.Black)

                        }

                    }

                }

            LazyColumn{
                items(hostelList){
                    MessItemView(hostel = it, delete = {hostel ->  messmenuViewModel.deleteHostel(hostel)

                    }, navController = navController )


                }

            }

        }

    }

}