package com.shani.nita.adminScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.shani.nita.AdminPanel_itemview.BranchDocsItemView
import com.shani.nita.ui.theme.Purple40
import com.shani.nita.viewModel.BranchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PyqScreen(navController: NavController, branch:String, semester:String){

    val context = LocalContext.current
    val branchViewModel: BranchViewModel = viewModel()


    val loading by branchViewModel.loading.observeAsState(false)

    val pyqList by branchViewModel.pyqList.observeAsState(emptyList())

    val isDeleted by branchViewModel.isDeleted.observeAsState(false)

    LaunchedEffect(true) {
        branchViewModel.getPyq(branch, semester)

    }

    LaunchedEffect(isDeleted) {
        Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = semester,
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

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
                modifier = Modifier.padding(padding)
            ) {
                items(pyqList) {branchModel->
                    BranchDocsItemView(
                        branchModel = branchModel,
                        navController,
                        branch,
                        semester,
                        branchModel.docId!!,
                        delete = {branchModel,branch, semester, docId ->
                            branchViewModel.deletePyq(branchModel, branch, semester, branchModel.docId!!)
                        }
                    )
                }

            }

        }


    }
}

