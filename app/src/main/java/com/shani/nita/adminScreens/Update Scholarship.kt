package com.shani.nita.adminScreens
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
import com.shani.nita.R
import com.shani.nita.Widgets.LoadingDialog
import com.shani.nita.AdminPanel_itemview.ScholarshipItemView
import com.shani.nita.ui.theme.Purple40
import com.shani.nita.utils.Constant.SCHOLARSHIP
import com.shani.nita.viewModel.ScholarshipViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScholarship(navController: NavController){

    val context =  LocalContext.current
    val scholarshipViewModel:ScholarshipViewModel= viewModel()
    val isUploaded by scholarshipViewModel.isPosted.observeAsState(initial = false)
    val isDeleted by scholarshipViewModel.isDeleted.observeAsState(false)
    val ScholarshipList by scholarshipViewModel.scholarshipList.observeAsState(emptyList())


    LaunchedEffect(true) {
        scholarshipViewModel.getScholarship()
    }

    val showLoader = remember {
        mutableStateOf(false)
    }

    if(showLoader.value){
        LoadingDialog(onDismissRequest = {
            showLoader.value = false
        })
    }

    var imageUri by remember{
         mutableStateOf<Uri?>(null)
    }
    var name by remember {
        mutableStateOf("")
    }
    var eligibity by remember {
        mutableStateOf("")
    }
    var date by remember {
        mutableStateOf("")
    }
    var url by remember {
        mutableStateOf("")
    }
    var isScholarship by remember{
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    LaunchedEffect(isUploaded) {
        if(isUploaded)
            showLoader.value = false
            Toast.makeText(context, "Details Uploaded", Toast.LENGTH_SHORT).show()
        imageUri = null
        name = ""
        eligibity = ""
        date = ""
        url = ""
    }

    LaunchedEffect(isDeleted) {
        if(isDeleted)
            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()

    }




    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Update Scholarship")},
                colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Purple40),

                navigationIcon = {
                    IconButton(onClick = {navController.navigateUp()}) {
                         Icon(imageVector = Icons.Default.ArrowBack,
                             contentDescription = null,
                             tint = Color.White
                             )

                    }
                })


        },
        floatingActionButton = {
            FloatingActionButton(onClick = { isScholarship = true}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null )

            }
        }


    ) {padding->

            Column( modifier = Modifier.padding(padding)) {
                if(isScholarship)
                    ElevatedCard(modifier = Modifier.padding(8.dp)){

                        Image(painter = if(imageUri == null) painterResource(id = R.drawable.scholarship_image)
                        else rememberAsyncImagePainter(model = imageUri), contentDescription = SCHOLARSHIP,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .clickable { launcher.launch("image/*") },
                            contentScale = ContentScale.Fit
                        )

                        OutlinedTextField(value = name, onValueChange = {name = it}, placeholder = {
                            Text("Enter Scholarship Name")},
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        )

                        OutlinedTextField(value = eligibity, onValueChange = {eligibity = it}, placeholder = {
                            Text("Enter Eligibity")},
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        )

                        OutlinedTextField(value = date, onValueChange = {date = it}, placeholder = {
                            Text("Enter Date Start To End")},
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        )

                        OutlinedTextField(value = url, onValueChange = {url = it}, placeholder = {
                            Text("Paste Scholarship Link Here")},
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.padding(10.dp))

                        Row {
                            Button(onClick = { if (name == "" || eligibity == "" || date == "" || url == "") {
                                Toast.makeText(context, "Please Enter All Detail", Toast.LENGTH_SHORT).show()
                            }
                            else {
                                showLoader.value = true
                                scholarshipViewModel.saveScholarship(imageUri!!, name, eligibity, date, url)
                                showLoader.value = false
                            }
                                isScholarship = false

                            },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Upload Details")

                            }

                            OutlinedButton(onClick = {
                                imageUri = null
                                name = ""
                                eligibity = ""
                                date = ""
                                url = ""

                            },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Cancel", color = Color.Black)

                            }
                        }

                    }



                LazyColumn() {

                    items(ScholarshipList){
                        ScholarshipItemView(scholarshipModel = it, context = context,delete = {
                                scholarshipModel ->  scholarshipViewModel.deleteScholarship(scholarshipModel)
                        })
                    }

                }




            }





    }



}
