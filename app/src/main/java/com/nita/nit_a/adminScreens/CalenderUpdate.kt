package com.nita.nit_a.adminScreens

import android.graphics.RectF
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.nita.nit_a.R
import com.nita.nit_a.Widgets.LoadingDialog


import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.CalenderViewModel
import com.pspdfkit.document.PdfDocumentLoader


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalenderUpdate(navController: NavController){

    val context = LocalContext.current
    val  calenderViewModel:CalenderViewModel = viewModel()
    val isUploaded by  calenderViewModel.isPosted.observeAsState(false)
    val isDeleted by calenderViewModel.isDeleted.observeAsState(false)
    val  calender by calenderViewModel.calender.observeAsState()

    var showLoader = remember {
        mutableStateOf(false)
    }
    if (showLoader.value){
        LoadingDialog(onDismissRequest = {showLoader.value = false})
    }

    LaunchedEffect(key1 = true) {
        calenderViewModel.getCalender()
    }

    var pdfUri by remember { mutableStateOf<Uri?>(null) }
    var isCalender by remember{
        mutableStateOf(false)
    }


    val thumbnailImage by remember {
         derivedStateOf {
             pdfUri?.let {
                 val document = PdfDocumentLoader.openDocument(context, it)

                 val pageImageSize = document.getPageSize(0).toRect()

                 val thumbnailImageSize = RectF(1f, 1f, pageImageSize.width(),
                     pageImageSize.height())

                 document.renderPageToBitmap(context, 0, thumbnailImageSize.width().toInt(),
                     thumbnailImageSize.height().toInt()).asImageBitmap()
             }
         }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        pdfUri = it
    }

    LaunchedEffect(isUploaded) {
        if(isUploaded)
            Toast.makeText(context, "Calender uploaded", Toast.LENGTH_SHORT).show()
        pdfUri = null
        isCalender = false

    }

    LaunchedEffect(isDeleted) {
        if(isDeleted)
            Toast.makeText(context, "Calender Deleted", Toast.LENGTH_SHORT).show()

    }



    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Notice Update",
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
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isCalender = true
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription =null )
            }
        },
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()) {

            Column(modifier = Modifier.verticalScroll(rememberScrollState()))  {
                if (isCalender)
//
                    ElevatedCard(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        if (pdfUri == null)
                            Image(painter = painterResource(id = R.drawable.pdfholder),
                                contentDescription = null,
                                modifier = Modifier.clickable { launcher.launch("application/pdf") })
                        if (pdfUri != null)
                            Image(bitmap = thumbnailImage!!,
                                contentDescription = "Thumbnail of pdf",
                                modifier = Modifier
                                    .clickable { launcher.launch("application/pdf") }
                                    .padding(start = 80.dp, bottom = 12.dp)
//
                            )


                        Row {
                            Button(
                                onClick = { if (pdfUri == null)
                                    Toast.makeText(context, "Please Add Pdf", Toast.LENGTH_SHORT)
                                        .show()
                                    else
                                        showLoader.value = true
                                    calenderViewModel.saveCalender(pdfUri!!)

                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)
                            ) {
                                Text(text = "Add Calender")

                            }
                            OutlinedButton(
                                onClick = {
                                    pdfUri = null
                                    isCalender = false
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
                if (calender != null)
                    OutlinedCard(modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            navController.navigate("pdfScreen/${Uri.encode(calender!!.url)}")
                        }) {
                        ConstraintLayout {
                            val (image, delete) = createRefs()
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.pdf),
                                    contentDescription = null,
                                    modifier = Modifier,
                                    contentScale = ContentScale.Crop

                                )

                            }
                            Card(modifier = Modifier
                                .constrainAs(delete) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                }
                                .clickable {
                                    calenderViewModel.deleteCalender(calender!!)
                                }
                            ){

                                Image(painter = painterResource(id = R.drawable.delete),
                                    contentDescription =null, modifier = Modifier.padding(8.dp)
                                )

                            }

                        }

                    }



            }

        }
    }



}