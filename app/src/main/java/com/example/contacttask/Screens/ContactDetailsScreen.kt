package com.example.contacttask.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.contacttask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDetailsScreen(
    navHostController: NavHostController,
    firstName: String?,
    lastName: String?,
    phoneNumber: String?,
    emailId: String?,
    telephone: String?
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background_color))

    ) {
        val (bannerImage, card, topBar) = createRefs()

        TopAppBar(
            title = { " " },
            modifier = Modifier
                .constrainAs(topBar) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .background(Color.White),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White
            )
        )




        Image(
            painter = painterResource(id = R.drawable.contactsbanner),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.White)
                .constrainAs(bannerImage) {
                    start.linkTo(parent.start)
                    top.linkTo(topBar.bottom)
                },
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.background_color))
                .constrainAs(card) {
                    start.linkTo(parent.start)
                    top.linkTo(bannerImage.bottom)
                },
            elevation = 10.dp,
            backgroundColor = Color.White,
            shape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp)
            ) {

                Text(
                    text = "Contact Details",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.zoho_puvi_bold)),
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(bottom = 30.dp),
                    color = colorResource(id = R.color.primary_color)
                )

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {

                    var text by remember { mutableStateOf(TextFieldValue("")) }

                    Image(
                        imageVector = Icons.Default.Person, contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            if (firstName != null) {
                                Text(
                                    text = firstName,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "First Name",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        placeholder = {
                                if (firstName != null) {
                                    Text(
                                        text = firstName,
                                        fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                    )
                                }else {
                                    Text(
                                        text = "First Name",
                                        fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                    )
                                }
                        },
                        singleLine = true,
                        readOnly = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = Color.Black,
                            unfocusedIndicatorColor = colorResource(id = R.color.primary_color),
                            focusedIndicatorColor = colorResource(id = R.color.secondary_color),
                            placeholderColor = colorResource(id = R.color.primary_color),
                            unfocusedLabelColor = colorResource(id = R.color.primary_color),
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {

                    var text by remember { mutableStateOf(TextFieldValue("")) }

                    Image(
                        imageVector = Icons.Default.Person, contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            if (lastName != null) {
                                Text(
                                    text = lastName,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "Last Name",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        placeholder = {
                            if (lastName != null) {
                                Text(
                                    text = lastName,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }else{
                                Text(
                                    text = "Last Name",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        singleLine = true,
                        readOnly = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = Color.Black,
                            unfocusedIndicatorColor = colorResource(id = R.color.primary_color),
                            focusedIndicatorColor = colorResource(id = R.color.secondary_color),
                            placeholderColor = colorResource(id = R.color.primary_color),
                            unfocusedLabelColor = colorResource(id = R.color.primary_color),
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {

                    var text by remember { mutableStateOf(TextFieldValue("")) }

                    Image(
                        imageVector = Icons.Default.Call, contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            if (telephone != null) {
                                Text(
                                    text = telephone,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "Telephone",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        placeholder = {
                            if (telephone != null) {
                                Text(
                                    text = telephone,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "Telephone Number",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        singleLine = true,
                        readOnly = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = Color.Black,
                            unfocusedIndicatorColor = colorResource(id = R.color.primary_color),
                            focusedIndicatorColor = colorResource(id = R.color.secondary_color),
                            placeholderColor = colorResource(id = R.color.primary_color),
                            unfocusedLabelColor = colorResource(id = R.color.primary_color),
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }


                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {

                    var text by remember { mutableStateOf(TextFieldValue("")) }

                    Image(
                        imageVector = Icons.Default.Call, contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            if (phoneNumber != null) {
                                Text(
                                    text = phoneNumber,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "Phone",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        placeholder = {
                            if (phoneNumber != null) {
                                Text(
                                    text = phoneNumber,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "Phone Number",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        singleLine = true,
                        readOnly = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = Color.Black,
                            unfocusedIndicatorColor = colorResource(id = R.color.primary_color),
                            focusedIndicatorColor = colorResource(id = R.color.secondary_color),
                            placeholderColor = colorResource(id = R.color.primary_color),
                            unfocusedLabelColor = colorResource(id = R.color.primary_color),
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }

                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {

                    var text by remember { mutableStateOf(TextFieldValue("")) }

                    Image(
                        imageVector = Icons.Default.Email, contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterVertically)
                    )

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth(),
                        label = {
                            if (emailId != null) {
                                Text(
                                    text = emailId,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "Email Address",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        placeholder = {
                            if (emailId != null) {
                                Text(
                                    text = emailId,
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            } else {
                                Text(
                                    text = "Email Address",
                                    fontFamily = FontFamily(Font(R.font.zoho_puvi_medium))
                                )
                            }
                        },
                        singleLine = true,
                        readOnly = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = Color.Black,
                            unfocusedIndicatorColor = colorResource(id = R.color.primary_color),
                            focusedIndicatorColor = colorResource(id = R.color.secondary_color),
                            placeholderColor = colorResource(id = R.color.primary_color),
                            unfocusedLabelColor = colorResource(id = R.color.primary_color),
                        ),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email
                        )
                    )
                }

            }

        }

        /*Image(
           painter = rememberAsyncImagePainter(
               model = "",
               placeholder = painterResource(id = R.drawable.user_placeholder),
               error = painterResource(id = R.drawable.user_placeholder)),
               contentDescription = null,
               modifier = Modifier
                   .size(100.dp)
                   .clip(CircleShape)
                   .constrainAs(image) {
                       start.linkTo(card.start)
                       top.linkTo(bannerImage.bottom)
                       bottom.linkTo(bannerImage.bottom)
                       absoluteLeft.linkTo(parent.start)
                       absoluteRight.linkTo(parent.end)
                   },
               contentScale = ContentScale.Fit)*/
    }
}

@Preview
@Composable
fun thisPreview() {
    ContactDetailsScreen(rememberNavController(), "name", "last", "phone", "email", "telephone")
}