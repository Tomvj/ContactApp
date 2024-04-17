package com.example.contacttask

import ContactViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.contacttask.Navigation.NavigationController
import com.example.contacttask.ui.theme.ZohoContactTheme

class MainActivity : ComponentActivity() {
    private val viewmodel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZohoContactTheme {
                val navController = rememberNavController()
                NavigationController(navHostController = navController, viewModel = viewmodel)
            }

        }
    }
}

