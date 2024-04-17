package com.example.contacttask.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.contacttask.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {


    LaunchedEffect(key1 = true ){

        delay(2000)
        navController.navigate("contactDashboard"){
            popUpTo("splashScreen"){
                inclusive = true
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = painterResource(id = R.drawable.spalsh), contentDescription ="" )

        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp,
            color = colorResource(id = R.color.primary_color),
            fontFamily = FontFamily(Font(R.font.zoho_puvi_bold))
        )

    }
}




@Preview
@Composable
fun SplashPreview(){
    SplashScreen(rememberNavController())
}
