package com.shani.nita.adminScreens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.shani.nita.R
import com.shani.nita.AdminPanel_itemview.CategoryItemView
import com.shani.nita.Widgets.LoadingDialog
import com.shani.nita.ui.theme.Purple40
import com.shani.nita.utils.Constant.FACULTY
import com.shani.nita.viewModel.FacultyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun FacultyUpdate(navController: NavController){

    val context = LocalContext.current

    val facultyViewModel:FacultyViewModel = viewModel()

    val isUploaded by facultyViewModel.isPosted.observeAsState(false)
    val isDeleted by facultyViewModel.isDeleted.observeAsState(false)
    val categoryList by facultyViewModel.categoryList.observeAsState(emptyList())

     LaunchedEffect(true) {
         facultyViewModel.getCategory()

     }

    val option = arrayListOf<String>()





    var mExpanded by remember{ mutableStateOf(false) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var isCategory by remember{
        mutableStateOf(false)
    }
    var isTeacher by remember {
        mutableStateOf(false)
    }
     
    var name by remember {
        mutableStateOf("")
    }
    var position by remember {
        mutableStateOf("")
    }
    var branch by remember {
        mutableStateOf("")
    }
    var interest by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var category by remember {
        mutableStateOf("")
    }

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) {

        imageUri = it

    }

    val showLoader = remember {
        mutableStateOf(false)
    }

    if(showLoader.value){
        LoadingDialog (onDismissRequest = {
            showLoader.value = false
        })
    }

    LaunchedEffect(isUploaded) {
        if(isUploaded)
            Toast.makeText(context, "Data Uploaded", Toast.LENGTH_SHORT).show()
        imageUri = null
        isCategory = false
        isTeacher = false
        category = ""
        name = ""
        position = ""
        branch = ""
        interest = ""
        email = ""

    }

    LaunchedEffect(isDeleted) {
        if(isDeleted)
            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()

    }

     Scaffold(
         topBar = {
             TopAppBar(title = { Text(text = "Manage Faculty") },
                 colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),

                 navigationIcon = {
                     IconButton(onClick = {navController.navigateUp()}) {
                         Icon(
                             imageVector = Icons.Default.ArrowBack,
                             contentDescription = null,
                             tint = Color.White
                         )
                     }
                 }
             )
         }
     ) {padding->

         Column(modifier = Modifier
             .fillMaxSize()
             .padding(padding)){
             Row{
                 Card(modifier = Modifier
                     .weight(1f)
                     .clickable {
                         isCategory = true
                         isTeacher = false
                     }) {
                     Text("Add Category",
                         fontWeight = FontWeight.Bold,
                         fontSize = 18.sp,
                         modifier = Modifier.padding(8.dp)
                     )
                 }

                 Card(modifier = Modifier
                     .weight(1f)
                     .clickable {
                         isCategory = false
                         isTeacher = true
                     }) {
                     Text("Add Teacher",
                         fontWeight = FontWeight.Bold,
                         fontSize = 18.sp,
                         modifier = Modifier.padding(8.dp)
                     )
                 }
             }
             if(isCategory)
                 ElevatedCard(modifier = Modifier.padding(8.dp)){
                     OutlinedTextField(value = category, onValueChange = {
                         category = it },
                         placeholder = {
                             Text("category..")
                         },
                         modifier = Modifier
                             .fillMaxWidth()
                             .padding(4.dp)
                     )
                     Row{
                         Button(onClick = {if (category == "") {
                             Toast.makeText(context, "Please insert Category", Toast.LENGTH_SHORT).show()

                         } else {
                             showLoader.value = true
                             facultyViewModel.uploadCategory(category)
                             showLoader.value = false
                             category = ""} },
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .weight(1f)
                                 .padding(4.dp)) {
                             Text(text = "Add Category")

                         }
                         OutlinedButton(onClick = {
                             imageUri = null
                             isCategory = false
                             isTeacher = false },
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .weight(1f)
                                 .padding(4.dp)) {
                             Text(text = "Cancel", color = Color.Black)

                         }

                     }

                 }

             if(isTeacher)

                 LazyColumn {
                     item {
                         ElevatedCard(modifier = Modifier.padding(8.dp)) {

                             Image(
                                 painter = if(imageUri == null) painterResource(id = R.drawable.facultyimage)
                                 else rememberAsyncImagePainter(model = imageUri),
                                 contentDescription = FACULTY,
                                 modifier = Modifier
                                     .height(130.dp)
                                     .width(130.dp)
                                     .clip(CircleShape)
                                     .align(Alignment.CenterHorizontally)
                                     .clickable {
                                         launcher.launch("image/*")
                                     },
                                 contentScale = ContentScale.Fit
                             )
                             OutlinedTextField(value = name, onValueChange = {
                                 name = it },
                                 placeholder = {
                                     Text("Enter Name")
                                 },
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .padding(4.dp)
                             )

                             OutlinedTextField(value = position, onValueChange = {
                                 position = it },
                                 placeholder = {
                                     Text("Enter Position")
                                 },
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .padding(4.dp)
                             )
                             OutlinedTextField(value = branch, onValueChange = {
                                 branch = it },
                                 placeholder = {
                                     Text("Enter branch")
                                 },
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .padding(4.dp)
                             )

                             OutlinedTextField(value = interest, onValueChange = {
                                 interest = it },
                                 placeholder = {
                                     Text("Enter Interest")
                                 },
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .padding(4.dp)
                             )

                             OutlinedTextField(value = email, onValueChange = {
                                 email = it },
                                 placeholder = {
                                     Text("Enter Email")
                                 },
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .padding(4.dp)
                             )

                             Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                                 OutlinedTextField(value = category, onValueChange = {
                                     category = it
                                 },
                                     readOnly = true,
                                     placeholder = {
                                         Text("Select your department")
                                     },
                                     label = { Text("Department Name") },
                                     modifier = Modifier
                                         .fillMaxWidth()
                                         .padding(4.dp),
                                     trailingIcon = {
                                         ExposedDropdownMenuDefaults.TrailingIcon(expanded = mExpanded)
                                     }
                                 )

                                 DropdownMenu(
                                     expanded = mExpanded,
                                     onDismissRequest = { mExpanded = false }) {

                                     if (categoryList != null && categoryList!!.isNotEmpty()) {
                                         option.clear()
                                         for (data in categoryList!!) {
                                             option.add(data)
                                         }
                                     }
                                     option.forEach { selectedOption ->
                                         DropdownMenuItem(
                                             text = { Text(text = selectedOption) },
                                             onClick = {
                                                 category = selectedOption
                                                 mExpanded = false
                                             }, modifier = Modifier.fillMaxWidth()
                                         )
                                     }

                                 }

                                 Spacer(modifier = Modifier
                                     .matchParentSize()
                                     .padding(10.dp)
                                     .clickable { mExpanded = !mExpanded }
                                 )
                             }


                             Row{
                                 Button(onClick = {if (imageUri == null) {
                                     Toast.makeText(context, "Please insert image", Toast.LENGTH_SHORT).show()

                                 } else if (name == "" || email == "" || position == "" || branch == "") {
                                     Toast.makeText(context, "Please enter all detail", Toast.LENGTH_SHORT).show()
                                 }

                                     facultyViewModel.saveFaculty(imageUri!!, name, position, branch,interest, email, category)
                                     imageUri = null
                                     name = ""
                                     position = ""
                                     branch = ""
                                     interest = ""
                                     email = ""
                                     category =""},
                                     modifier = Modifier
                                         .fillMaxWidth()
                                         .weight(1f)
                                         .padding(4.dp)) {
                                     Text(text = "Add Teacher")

                                 }
                                 OutlinedButton(onClick = {
                                     imageUri = null
                                     isCategory = false
                                     isTeacher = false
                                 },
                                     modifier = Modifier
                                         .fillMaxWidth()
                                         .weight(1f)
                                         .padding(4.dp)) {
                                     Text(text = "Cancel", color = Color.Black)

                                 }

                             }

                         }
                     }
                 }



             LazyColumn{
                 items(categoryList){
                     CategoryItemView(catName = it, delete = {catName ->  facultyViewModel.deleteCategory(catName)

                     }, navController = navController )


                 }

             }
         }



     }

    

}