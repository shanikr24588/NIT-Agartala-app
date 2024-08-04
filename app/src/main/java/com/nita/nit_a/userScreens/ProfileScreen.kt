package com.nita.nit_a.userScreens

 

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit

import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController
import com.nita.nit_a.Models.UserProfileModel

import com.nita.nit_a.R
import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.AuthViewModel
import com.nita.nit_a.viewModel.UserProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {


    val  userProfileViewModel:UserProfileViewModel = viewModel()
    val authViewModel:AuthViewModel = viewModel()

    val _enrollment by authViewModel.editenrollment.observeAsState("")
    val _email by authViewModel.editemail.observeAsState("")


    val context = LocalContext.current

    var isEditing by remember {
        mutableStateOf(false)
    }
     var name by remember {
         mutableStateOf("")
     }

    var enrollment by remember {
        mutableStateOf("")
    }


    var hostel by remember {
        mutableStateOf( "")
    }

    var department by remember {
        mutableStateOf(  "")
    }

    var year by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var hExpanded by remember{ mutableStateOf(false) }
    var dExpanded by remember{ mutableStateOf(false) }
    var yExpanded by remember{ mutableStateOf(false) }

    val hostelList = arrayListOf(
        "RNT HOSTEL",
        "ARYABHATA HOSTEL",
        "GARGI HOSTEL"
    )

    val departmentList = arrayListOf(
        "Computer Science",
        "Electronics & Communication",
        "Electrical",
        "Electronics & Instrumentation",
        "Mechanical",
        "Civil",
        "Chemical",
        "Production",
        "BioTech",
        "Physics",
        "Chemistry",
        "Mathematics",
        "IIIT"
    )

    val yearList = arrayListOf(
        "1st Year",
        "2nd Year",
        "3rd Year",
        "4th Year"
    )



    LaunchedEffect(true) {
        authViewModel.getUserData()

    }



     LaunchedEffect(true) {
         userProfileViewModel.getData(context) { userData ->
             name = userData.name!!
             hostel = userData.hostel!!
             department = userData.department!!
             year = userData.year!!
         }

     }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Profile",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 28.sp,
                modifier = Modifier.padding(start = 100.dp)) },
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),
                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()},
                        modifier = Modifier ){
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        },
        content = {padding->
            Box(modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
                contentAlignment = Alignment.Center) {

                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Image(painter = painterResource(id = R.drawable.person ),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(CircleShape)
                            .height(140.dp)
                            .width(140.dp)
                            .background(Color.Blue)
                            .border(
                                width = 1.dp,
                                color = Color.White,
                                shape = CircleShape
                            )

                    )
                    if (isEditing) {
                        ElevatedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)

                        ) {
                            Column(
                                modifier = Modifier,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {

                                OutlinedTextField(
                                    value =  name,
                                    onValueChange = { name = it },
                                    label = { Text(text = "Enter Your Name") },
                                    readOnly = !isEditing,

                                )



                                Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                                    OutlinedTextField(value = hostel, onValueChange = {
                                        hostel = it
                                    },
                                        readOnly = true,
                                        placeholder = {
                                            Text("Select your Hostel")
                                        },
                                        label = { Text("Hostel Name") },
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(4.dp),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = hExpanded)
                                        }
                                    )

                                    DropdownMenu(
                                        expanded = hExpanded,
                                        onDismissRequest = { hExpanded = false }) {


                                        hostelList.forEach { selectedOption ->
                                            DropdownMenuItem(
                                                text = { Text(text = selectedOption) },
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
                                    OutlinedTextField(value = department, onValueChange = {
                                        department = it
                                    },
                                        readOnly = true,
                                        placeholder = {
                                            Text("Select your department")
                                        },
                                        label = { Text("Department Name") },
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(4.dp),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = dExpanded)
                                        }
                                    )

                                    DropdownMenu(
                                        expanded = dExpanded,
                                        onDismissRequest = { dExpanded = false }) {


                                        departmentList.forEach { selectedOption ->
                                            DropdownMenuItem(
                                                text = { Text(text = selectedOption) },
                                                onClick = {
                                                    department = selectedOption
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

                                Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                                    OutlinedTextField(value = year, onValueChange = {
                                        year = it
                                    },
                                        readOnly = true,
                                        placeholder = {
                                            Text("Select your Year")
                                        },
                                        label = { Text("Year") },
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(4.dp),
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = yExpanded)
                                        }
                                    )

                                    DropdownMenu(
                                        expanded = yExpanded,
                                        onDismissRequest = { yExpanded = false }) {


                                        yearList.forEach { selectedOption ->
                                            DropdownMenuItem(
                                                text = { Text(text = selectedOption) },
                                                onClick = {
                                                    year = selectedOption
                                                    yExpanded = false
                                                }, modifier = Modifier.fillMaxWidth()
                                            )
                                        }

                                    }

                                    Spacer(modifier = Modifier
                                        .matchParentSize()
                                        .padding(10.dp)
                                        .clickable { yExpanded = !yExpanded }
                                    )
                                }



                            }

                        }
                    }
                    else {

                        ElevatedCard(modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .size(320.dp) ,
                            colors = CardDefaults.cardColors(colorResource(id = R.color.Blue1),
                                )
                            
                        ) {
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                                verticalArrangement = Arrangement.SpaceEvenly) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.name),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )

                                    Text(
                                        text = if (name == "") "Update Your Name" else name,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier =Modifier.padding(start = 80.dp)
                                    )

                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.enrollment),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )

                                    Text(
                                        text = _enrollment!!,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier =Modifier.padding(start = 80.dp)
                                    )

                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth()

                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.hostel),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )



                                    Text(
                                        text = if (hostel == "") "Update Your Hostel" else hostel,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier.padding(start = 80.dp)
                                    )

                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.department),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )

                                    Text(
                                        text = if (department == "") "Update Your Department" else department,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier =Modifier.padding(start = 80.dp)
                                    )

                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.year),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )

                                    Text(
                                        text = if (year == "") "Update Your Year" else year,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier =Modifier.padding(start = 80.dp)
                                    )

                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.mail),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp)
                                    )

                                    Text(
                                        text = _email!!,
                                        color = Color.White,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier =Modifier.padding(start = 70.dp)
                                    )

                                }
                            }
                            
                        }

                         
                    }




                    Row (modifier = Modifier.padding(12.dp)){
                        Button(
                            onClick = {isEditing = true},
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 5.dp)
                                .height(50.dp)) {
                            Text(text =  "Edit Profile"  )
                             Spacer( modifier = Modifier.padding(horizontal = 5.dp))
                            Icon(imageVector =   Icons.Default.Edit , contentDescription = null)
                        }

                        Button(
                            onClick = {val userProfileModel = UserProfileModel(
                                name = name,
                                hostel = hostel,
                                department = department,
                                year = year)
                                userProfileViewModel.saveData( userProfileModel, context)
                                      isEditing = false},
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 5.dp)
                                .height(50.dp)
                        ) {
                            Text(text =  "Save"  )
                            Spacer( modifier = Modifier.padding(horizontal = 5.dp))
                            Icon(imageVector =   Icons.Default.Check , contentDescription = null)
                        }



                    }

                }

            }

        }
    )

}