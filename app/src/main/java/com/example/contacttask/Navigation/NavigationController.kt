package com.example.contacttask.Navigation

import ContactViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.contacttask.Screens.ContactDashboard
import com.example.contacttask.Screens.ContactDetailsScreen
import com.example.contacttask.Screens.SplashScreen

@Composable
fun NavigationController(navHostController: NavHostController, viewModel: ContactViewModel) {
    NavHost(navController = navHostController, startDestination = "splashScreen") {
        composable("splashScreen"){
            SplashScreen(navController = navHostController)
        }
        composable("contactDashboard"){
           ContactDashboard(viewModel = viewModel, navHostController = navHostController)
        }
        composable("contactDetails?firstName={firstName}&lastName={lastName}&mobileNumber={mobileNumber}&emailId={emailId}&telephone={telephone}",
            arguments = listOf(
                navArgument(name = "firstName"){
                    type = NavType.StringType
                    defaultValue = "First Name"
                    nullable= true
                },
                navArgument(name = "lastName"){
                    type= NavType.StringType
                    defaultValue = "Last Name"
                    nullable= true
                },navArgument(name = "mobileNumber"){
                    type = NavType.StringType
                    defaultValue = "Mobile Number"
                    nullable= true
                },
                navArgument(name = "emailId"){
                    type= NavType.StringType
                    defaultValue = "Email Address"
                    nullable= true
                },
                navArgument(name = "telephone"){
                    type= NavType.StringType
                    defaultValue = "Telephone Number"
                    nullable= true
                }
            )
        ){
            ContactDetailsScreen(navHostController = navHostController,
                firstName = it.arguments?.getString("firstName").toString(),
                lastName = it.arguments?.getString("lastName").toString(),
                phoneNumber = it.arguments?.getString("mobileNumber").toString(),
                emailId = it.arguments?.getString("emailId").toString(),
                telephone = it.arguments?.getString("telephone").toString())

        }

    }
}