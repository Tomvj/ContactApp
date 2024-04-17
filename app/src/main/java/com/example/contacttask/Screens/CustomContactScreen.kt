package com.example.contacttask.Screens

import ContactViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.example.contacttask.NetworkClass
import com.example.contacttask.R
import com.example.contacttask.Responsemodel.ResponseModel
import com.example.contacttask.Responsemodel.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@Composable
fun CustomContactScreen(viewmodel: ContactViewModel, navHostController: NavHostController) {
    val contactList = viewmodel.usersPager.collectAsLazyPagingItems()
    var currentQuery = ""
     var currentPage = 1
     val itemsPerPage = 5

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ) {

        LazyColumn {
            items(contactList) { item ->
                item?.let { UserCards(user = it, navHostController = navHostController) }
            }
        }

        when (contactList.loadState.append) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {

            }
            LoadState.Loading -> {
                    LoadingItem()
            }
            is LoadState.Error -> {
                    ErrorItem(message = "Some error occurred")
            }
        }

        when (contactList.loadState.refresh) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
            }
            is LoadState.Error -> {
                ErrorItem(message = "Some error occurred")
            }
        }
    }
}



@Composable
fun UserCards(user: Result, navHostController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var emailId by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    firstName = user.name.first.toString()
    lastName = user.name.last.toString()
    mobileNumber = user.phone.toString()
    emailId = user.email.toString()
    telephone = user.cell.toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background_color))
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(color = colorResource(id = R.color.background_color))
                .selectable(
                    selected = true,
                    onClick = {
                        navHostController.navigate("contactDetails?firstName=$firstName&lastName=$lastName&mobileNumber=$mobileNumber&emailId=$emailId&telephone=$telephone")
                    }
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = user.picture.thumbnail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit,
                )

                Text(
                    text = firstName + " " + lastName,
                    color = colorResource(id = R.color.primary_color),
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 10.dp, end = 10.dp),
                    fontFamily = FontFamily(Font(R.font.zoho_puvi_bold))
                )
                /*Text("Wrap Content", color = Color.Black, modifier = Modifier.fillMaxWidth())*/
            }


        }
        Divider(
            color = colorResource(id = R.color.primary_color),
            thickness = .8.dp,
            modifier = Modifier.padding(start = 5.dp, end = 5.dp)
        )
        /*Canvas(modifier = Modifier.fillMaxWidth()) {


    }*/

    }

}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )

    }
}

@Composable
fun ErrorItem(message: String) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(8.dp)
        ) {

         Text(
                color = Color.White,
                text = message,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}





