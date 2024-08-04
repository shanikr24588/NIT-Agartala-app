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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.R
import com.nita.nit_a.Widgets.LoadingDialog
import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.BranchViewModel
import com.pspdfkit.document.PdfDocumentLoader

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun UpdateBranch(navController: NavController){

    val context = LocalContext.current


    var pdfUri by remember { mutableStateOf<Uri?>(null) }

    var branch by remember {
        mutableStateOf("")
    }
    var semester by remember {
        mutableStateOf("")
    }
    var title by remember {
        mutableStateOf("")
    }


    var isBranch by remember {
        mutableStateOf(false)
    }
    var isSemester by remember {
        mutableStateOf(false)
    }
    var isNotes by remember {
        mutableStateOf(false)
    }
    var isPyq by remember {
        mutableStateOf(false)
    }
    var isPinDocs by remember{
        mutableStateOf(false)
    }
    var bExpanded by remember {
        mutableStateOf(false)
    }
    var sExpanded by remember {
        mutableStateOf(false)
    }

    val branchViewModel: BranchViewModel = viewModel()
    val isUploaded by branchViewModel.isPosted.observeAsState()

    val branchList by branchViewModel.branchList.observeAsState(emptyList())
    val semesterList by branchViewModel.semesterList.observeAsState(emptyList())


    val option_semester = arrayListOf<String>()
    val option_branch = arrayListOf<String>()

    val showLoader = remember {
        mutableStateOf(false)
    }

    if(showLoader.value){
        LoadingDialog (onDismissRequest = {
            showLoader.value = false
        })
    }




    LaunchedEffect(true) {
        branchViewModel.getBranch()
    }

    // Fetch days when hostel changes
    LaunchedEffect(branch) {
        if (branch.isNotBlank()) {
            branchViewModel.getSemester(branch)
        }
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
        showLoader.value = false
        Toast.makeText(context, "Data Uploaded", Toast.LENGTH_SHORT).show()
    }



    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Update Documents", fontWeight = FontWeight.Bold) },
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

            Column(modifier = Modifier
                .padding(padding)
                 ){

                    Card(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            isNotes = false
                            isSemester = false
                            isPyq = false
                            isPinDocs = false
                            isBranch = true
                        }) {
                        Text(text = "Add Branch",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()

                        )

                    }

                    Card(modifier = Modifier

                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isNotes = false
                            isSemester = true
                            isBranch = false
                            isPyq = false
                            isPinDocs = false
                        }) {
                        Text(text = "Add Semester",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)

                        )

                    }

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                        .clickable {
                            isNotes = true
                            isSemester = false
                            isBranch = false
                            isPyq = false
                            isPinDocs = false
                        }) {
                        Text(text = "Add Notes",modifier = Modifier
                            .padding(8.dp),
                            fontWeight = FontWeight.Bold
                        )

                    }


                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isNotes = false
                            isSemester = false
                            isBranch = false
                            isPyq = true
                            isPinDocs = false
                        }) {
                        Text(text = "Add PYQ",modifier = Modifier
                            .padding(8.dp),
                            fontWeight = FontWeight.Bold
                        )

                    }

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isNotes = false
                            isSemester = false
                            isBranch = false
                            isPyq = false
                            isPinDocs = true
                        }) {
                        Text(text = "Add PinDocs",modifier = Modifier
                            .padding(8.dp),
                            fontWeight = FontWeight.Bold
                        )

                    }



                if(isBranch)
                    ElevatedCard(modifier = Modifier.padding(8.dp)) {
                        OutlinedTextField(value = branch,
                            onValueChange = {branch = it},
                            placeholder = { Text(text = "Add Branch")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )

                        Row{
                            Button(onClick = {if (branch == "") {
                                Toast.makeText(context, "Please insert Branch", Toast.LENGTH_SHORT).show()

                            } else
//                                showLoader.value = true
                                branchViewModel.uploadBranch(branch)
                                branch = ""
                            },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Add Branch")

                            }
                            OutlinedButton(onClick = {

                                isBranch = false
                                isSemester = false
                                isNotes = false
                                isPyq = false
                                isPinDocs = false},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Cancel", color = Color.Black)

                            }

                        }

                    }

                if(isSemester)
                    ElevatedCard(modifier = Modifier.padding(8.dp)) {

                        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                            OutlinedTextField(value = branch,
                                onValueChange = {branch = it},
                                readOnly = true,
                                placeholder = {
                                    Text(text = "Select Branch")
                                },
                                label = { Text(text = "Branch Name")},
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = bExpanded)
                                }

                            )
                            DropdownMenu(expanded = bExpanded,
                                onDismissRequest = { bExpanded = false}) {
                                if(branchList != null && branchList.isNotEmpty()) {
                                    option_branch.clear()
                                    for(data in branchList){
                                        option_branch.add(data.toString())
                                    }

                                }
                                option_branch.forEach { selectedOption->
                                    DropdownMenuItem(text = { Text(text = selectedOption)},
                                        onClick = {
                                            branch = selectedOption
                                            bExpanded = false
                                        }, modifier = Modifier.fillMaxWidth()
                                    )

                                }


                            }

                            Spacer(modifier = Modifier
                                .matchParentSize()
                                .padding(10.dp)
                                .clickable { bExpanded = !bExpanded }
                            )

                        }

                        OutlinedTextField(value = semester,
                            onValueChange = {semester = it},
                            placeholder = { Text(text = "Add Semester")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )

                        Row{
                            Button(onClick = {if (semester == "") {
                                Toast.makeText(context, "Please Add Day", Toast.LENGTH_SHORT).show()

                            } else
//                                showLoader.value = true
                                  branchViewModel.uploadSemester(branch, semester)
                                semester = ""

                            },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Add Semester")

                            }
                            OutlinedButton(onClick = {

                                isBranch = false
                                isNotes = false
                                isPyq = false
                                isPinDocs = false
                                branch = ""
                                semester = ""
                            },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Cancel", color = Color.Black)

                            }

                        }

                    }

                if(isNotes)
                    ElevatedCard(modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize()) {


                             if (pdfUri == null)
                                 Image(painter = painterResource(id = R.drawable.attachfile),
                                     contentDescription = null,
                                     modifier = Modifier
                                         .size(80.dp)
                                         .clickable { launcher.launch("application/pdf") })
                             if (pdfUri != null)
                                 Image(bitmap = thumbnailImage!!,
                                     contentDescription = "Thumbnail of pdf",
                                     modifier = Modifier.size(120.dp)
                                         .clickable { launcher.launch("application/pdf") }
                                         .padding(start = 80.dp, bottom = 12.dp)
//
                                 )
                             OutlinedTextField(value = title,
                                 onValueChange = {title = it},
                                 placeholder = { Text(text = "Enter About Pdf")},
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .padding(4.dp)
                             )



                             Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                                 OutlinedTextField(value = branch,
                                     onValueChange = {branch = it},
                                     readOnly = true,
                                     placeholder = {
                                         Text(text = "Select Branch")
                                     },
                                     label = { Text(text = "Branch Name")},
                                     modifier = Modifier
                                         .padding(4.dp)
                                         .fillMaxWidth(),
                                     trailingIcon = {
                                         ExposedDropdownMenuDefaults.TrailingIcon(expanded = bExpanded)
                                     }

                                 )
                                 DropdownMenu(expanded = bExpanded,
                                     onDismissRequest = { bExpanded = false}) {
                                     if(branchList != null && branchList!!.isNotEmpty()) {
                                         option_branch.clear()
                                         for(data in branchList!!){
                                             option_branch.add(data.toString())
                                         }

                                     }
                                     option_branch.forEach { selectedOption->
                                         DropdownMenuItem(text = { Text(text = selectedOption)},
                                             onClick = {
                                                 branch = selectedOption
                                                 bExpanded = false
                                             }, modifier = Modifier.fillMaxWidth()
                                         )

                                     }


                                 }

                                 Spacer(modifier = Modifier
                                     .matchParentSize()
                                     .padding(10.dp)
                                     .clickable { bExpanded = !bExpanded }
                                 )

                             }

                             Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                                 OutlinedTextField(value = semester,
                                     onValueChange = {semester = it},
                                     readOnly = true,
                                     placeholder = {
                                         Text(text = "Select Semester")
                                     },
                                     label = { Text(text = "Semester Name")},
                                     modifier = Modifier
                                         .padding(4.dp)
                                         .fillMaxWidth(),
                                     trailingIcon = {
                                         ExposedDropdownMenuDefaults.TrailingIcon(expanded = sExpanded)
                                     }

                                 )
                                 DropdownMenu(expanded = sExpanded,
                                     onDismissRequest = { sExpanded = false}) {
                                     if(semesterList != null && semesterList!!.isNotEmpty()) {
                                         option_semester.clear()
                                         for(data in semesterList!!){
                                             option_semester.add(data)
                                         }

                                     }
                                     option_semester.forEach { selectedOption->
                                         DropdownMenuItem(text = { Text(text = selectedOption)},
                                             onClick = {
                                                 semester = selectedOption
                                                 sExpanded = false
                                             }, modifier = Modifier.fillMaxWidth()
                                         )

                                     }


                                 }

                                 Spacer(modifier = Modifier
                                     .matchParentSize()
                                     .padding(10.dp)
                                     .clickable { sExpanded = !sExpanded }
                                 )

                             }


                             Row{
                                 Button(onClick = {if (pdfUri == null || title == "" ) {
                                     Toast.makeText(context, "Please insert All Details", Toast.LENGTH_SHORT).show()

                                 } else
//                                     showLoader.value = true
                                     branchViewModel.saveNotes(pdfUri!!, title, branch,semester)
                                     pdfUri = null
                                     title = ""
                                     branch = ""
                                     semester = ""
                                 },
                                     modifier = Modifier
                                         .fillMaxWidth()
                                         .weight(1f)
                                         .padding(4.dp)) {
                                     Text(text = "Add Notes")

                                 }
                                 OutlinedButton(onClick = {

                                     isBranch = false
                                     isSemester = false
                                     isNotes = false
                                     isPyq = false
                                     isPinDocs = false},
                                     modifier = Modifier
                                         .fillMaxWidth()
                                         .weight(1f)
                                         .padding(4.dp)) {
                                     Text(text = "Cancel", color = Color.Black)

                                 }

                             }



                    }

                if(isPyq)
                    ElevatedCard(modifier = Modifier.padding(8.dp)) {

                        if (pdfUri == null)
                            Image(painter = painterResource(id = R.drawable.attachfile),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clickable { launcher.launch("application/pdf") })
                        if (pdfUri != null)
                            Image(bitmap = thumbnailImage!!,
                                contentDescription = "Thumbnail of pdf",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clickable { launcher.launch("application/pdf") }
                                    .padding(start = 80.dp, bottom = 12.dp)
//
                            )
                        OutlinedTextField(value = title,
                            onValueChange = {title = it},
                            placeholder = { Text(text = "Enter About Pdf")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )



                        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                            OutlinedTextField(value = branch,
                                onValueChange = {branch = it},
                                readOnly = true,
                                placeholder = {
                                    Text(text = "Select Branch")
                                },
                                label = { Text(text = "Branch Name")},
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = bExpanded)
                                }

                            )
                            DropdownMenu(expanded = bExpanded,
                                onDismissRequest = { bExpanded = false}) {
                                if(branchList != null && branchList!!.isNotEmpty()) {
                                    option_branch.clear()
                                    for(data in branchList!!){
                                        option_branch.add(data.toString())
                                    }

                                }
                                option_branch.forEach { selectedOption->
                                    DropdownMenuItem(text = { Text(text = selectedOption)},
                                        onClick = {
                                            branch = selectedOption
                                            bExpanded = false
                                        }, modifier = Modifier.fillMaxWidth()
                                    )

                                }


                            }

                            Spacer(modifier = Modifier
                                .matchParentSize()
                                .padding(10.dp)
                                .clickable { bExpanded = !bExpanded }
                            )

                        }

                        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                            OutlinedTextField(value = semester,
                                onValueChange = {semester = it},
                                readOnly = true,
                                placeholder = {
                                    Text(text = "Select Semester")
                                },
                                label = { Text(text = "Semester Name")},
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = sExpanded)
                                }

                            )
                            DropdownMenu(expanded = sExpanded,
                                onDismissRequest = { sExpanded = false}) {
                                if(semesterList != null && semesterList!!.isNotEmpty()) {
                                    option_semester.clear()
                                    for(data in semesterList!!){
                                        option_semester.add(data)
                                    }

                                }
                                option_semester.forEach { selectedOption->
                                    DropdownMenuItem(text = { Text(text = selectedOption)},
                                        onClick = {
                                            semester = selectedOption
                                            sExpanded = false
                                        }, modifier = Modifier.fillMaxWidth()
                                    )

                                }


                            }

                            Spacer(modifier = Modifier
                                .matchParentSize()
                                .padding(10.dp)
                                .clickable { sExpanded = !sExpanded }
                            )

                        }


                        Row{
                            Button(onClick = {if (pdfUri == null || title == "" ) {
                                Toast.makeText(context, "Please Add All Details", Toast.LENGTH_SHORT).show()

                            } else
//                                showLoader.value = true
                                branchViewModel.savePyq(pdfUri!!, title, branch,semester)
                                pdfUri = null
                                title = ""
                                branch = ""
                                semester = ""
                            },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Add PYQ")

                            }
                            OutlinedButton(onClick = {

                                isBranch = false
                                isSemester = false
                                isNotes = false
                                isPyq = false
                                isPinDocs = false},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Cancel", color = Color.Black)

                            }

                        }

                    }

                if(isPinDocs)
                    ElevatedCard(modifier = Modifier.padding(8.dp)) {

                        if (pdfUri == null)
                            Image(painter = painterResource(id = R.drawable.attachfile),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clickable { launcher.launch("application/pdf") })
                        if (pdfUri != null)
                            Image(bitmap = thumbnailImage!!,
                                contentDescription = "Thumbnail of pdf",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clickable { launcher.launch("application/pdf") }
                                    .padding(start = 80.dp, bottom = 12.dp)
//
                            )
                        OutlinedTextField(value = title,
                            onValueChange = {title = it},
                            placeholder = { Text(text = "Enter About Pdf")},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )



                        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                            OutlinedTextField(value = branch,
                                onValueChange = {branch = it},
                                readOnly = true,
                                placeholder = {
                                    Text(text = "Select Branch")
                                },
                                label = { Text(text = "Branch Name")},
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = bExpanded)
                                }

                            )
                            DropdownMenu(expanded = bExpanded,
                                onDismissRequest = { bExpanded = false}) {
                                if(branchList != null && branchList!!.isNotEmpty()) {
                                    option_branch.clear()
                                    for(data in branchList!!){
                                        option_branch.add(data.toString())
                                    }

                                }
                                option_branch.forEach { selectedOption->
                                    DropdownMenuItem(text = { Text(text = selectedOption)},
                                        onClick = {
                                            branch = selectedOption
                                            bExpanded = false
                                        }, modifier = Modifier.fillMaxWidth()
                                    )

                                }


                            }

                            Spacer(modifier = Modifier
                                .matchParentSize()
                                .padding(10.dp)
                                .clickable { bExpanded = !bExpanded }
                            )

                        }

                        Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
                            OutlinedTextField(value = semester,
                                onValueChange = {semester = it},
                                readOnly = true,
                                placeholder = {
                                    Text(text = "Select Semester")
                                },
                                label = { Text(text = "Semester Name")},
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth(),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = sExpanded)
                                }

                            )
                            DropdownMenu(expanded = sExpanded,
                                onDismissRequest = { sExpanded = false}) {
                                if(semesterList != null && semesterList!!.isNotEmpty()) {
                                    option_semester.clear()
                                    for(data in semesterList!!){
                                        option_semester.add(data)
                                    }

                                }
                                option_semester.forEach { selectedOption->
                                    DropdownMenuItem(text = { Text(text = selectedOption)},
                                        onClick = {
                                            semester = selectedOption
                                            sExpanded = false
                                        }, modifier = Modifier.fillMaxWidth()
                                    )

                                }


                            }

                            Spacer(modifier = Modifier
                                .matchParentSize()
                                .padding(10.dp)
                                .clickable { sExpanded = !sExpanded }
                            )

                        }


                        Row{
                            Button(onClick = {if (pdfUri == null || title == "" ) {
                                Toast.makeText(context, "Please insert All Details", Toast.LENGTH_SHORT).show()

                            } else
//                                showLoader.value = true
                                branchViewModel.savePinDocs(pdfUri!!, title, branch,semester)
                                pdfUri = null
                                title = ""
                                branch = ""
                                semester = ""
                            },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(4.dp)) {
                                Text(text = "Add PinDocs")

                            }
                            OutlinedButton(onClick = {

                                isBranch = false
                                isSemester = false
                                isNotes = false
                                isPyq = false
                                isPinDocs = false},
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


}