package com.example.contacttask.Screens

import ContactViewModel
import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.contacttask.R


data class Contact(val id: String, val name: String, val phoneNumber: String)

@Composable
fun MyContactsScreen(viewmodel: ContactViewModel, navHostController: NavHostController) {

    val contentResolver = LocalContext.current
    var isPermissionGranted by remember { mutableStateOf(false) }
    var allContacts by remember { mutableStateOf<List<Contact>>(emptyList()) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        isPermissionGranted = isGranted
        if (isGranted) {
            allContacts = fetchContacts(contentResolver.contentResolver)
        }
    }

    LaunchedEffect(Unit) {
        val permission = Manifest.permission.READ_CONTACTS
        if (ContextCompat.checkSelfPermission(
                contentResolver,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            isPermissionGranted = true
            allContacts = fetchContacts(contentResolver.contentResolver)

        } else {
            requestPermissionLauncher.launch(permission)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.background_color)
    ) {

        if (isPermissionGranted && allContacts != null && allContacts.size > 0) {
            ContactList(contacts = allContacts, navHostController)
        } else if (isPermissionGranted && allContacts == null || allContacts.size == 0) {
            Text(
                text = "No contacts found in your contacts",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize(),
                fontFamily = FontFamily(Font(R.font.zoho_puvi_bold))
            )
        } else {
            Text(
                text = "We're not able to access your contact.",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize(),
                fontFamily = FontFamily(Font(R.font.zoho_puvi_bold))
            )
        }
    }

}

@Composable
fun ContactList(contacts: List<Contact>, navHostController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var emailId by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }
    LazyColumn {
        items(contacts.size) {
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
                                firstName = contacts!![it].name
                                lastName = ""
                                mobileNumber = contacts!![it].phoneNumber
                                emailId = ""
                                telephone = ""
                                navHostController.navigate("contactDetails?firstName=$firstName&lastName=$lastName&mobileNumber=$mobileNumber&emailId=$emailId&telephone=$telephone")
                            }
                        )// Wrap content size
                ) {
                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Fit,
                        )
                        Column(
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(10.dp)
                        ) {
                            Text(
                                text = contacts!![it].name,
                                color = colorResource(id = R.color.primary_color),
                                modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth(),
                                fontFamily = FontFamily(Font(R.font.zoho_puvi_bold))
                            )
                            /*Text("Wrap Content", color = Color.Black, modifier = Modifier.fillMaxWidth())*/
                        }
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
    }
}


fun fetchContacts(contentResolver: ContentResolver): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val projection = arrayOf(
        ContactsContract.CommonDataKinds.Phone._ID,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    contentResolver.query(
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        projection,
        null,
        null,
        null
    )?.use { cursor ->
        val idIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)
        val nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

        while (cursor.moveToNext()) {
            val id = cursor.getString(idIndex)
            val name = cursor.getString(nameIndex)
            val phoneNumber = cursor.getString(numberIndex)
            contacts.add(Contact(id, name, phoneNumber))
        }
    }
    return contacts
}

