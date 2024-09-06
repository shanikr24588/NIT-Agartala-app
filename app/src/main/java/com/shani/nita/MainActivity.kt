package com.shani.nita


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.shani.nita.navigation.NavGraph
import com.shani.nita.ui.theme.NITATheme
import com.shani.nita.userScreens.InAppUpdate



class MainActivity : ComponentActivity() {
    private lateinit var inAppUpdate: InAppUpdate
    private val snackbarHostState = SnackbarHostState()
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_NITA)
        super.onCreate(savedInstanceState)

        setContent {
            NITATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) }
                    ) { padding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(padding) // Use the padding parameter here
                        ) {
                            NavGraph()
                        }
                    }
                }
            }
        }
        inAppUpdate = InAppUpdate(this, snackbarHostState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        inAppUpdate.onActivityResult(requestCode,resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        inAppUpdate.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        inAppUpdate.onDestroy()
    }
}

