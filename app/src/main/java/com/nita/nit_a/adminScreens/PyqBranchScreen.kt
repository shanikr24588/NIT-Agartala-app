package com.nita.nit_a.adminScreens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nita.nit_a.AdminPanel_itemview.PyqBranchItemView
import com.nita.nit_a.ui.theme.Purple40
import com.nita.nit_a.viewModel.BranchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PyqBranchScreen(navController: NavController){

    val context = LocalContext.current

    val branchViewModel: BranchViewModel = viewModel()

    val loading by branchViewModel.loading.observeAsState(false)
    val branchList by branchViewModel.branchList.observeAsState(emptyList())
    val isDeleted by branchViewModel.isDeleted.observeAsState(false)



    LaunchedEffect(true) {
        branchViewModel.getBranch()
    }

    LaunchedEffect(isDeleted) {
        Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Departments Name",
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
            if (loading){
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.Red)
                }
            } else{
                LazyColumn(modifier = Modifier.padding(padding)){
                    items(branchList){
                        PyqBranchItemView(navController , branch =it , delete = {branch->
                            branchViewModel.deleteBranch(it)
                        })


                    }

                }
            }






        }
    )




}