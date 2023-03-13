package com.csci448.sflemington.recallrumble.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.sflemington.recallrumble.R
import com.csci448.sflemington.recallrumble.data.user.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(user : User, onUserProfileSaved: (String, String) -> Unit) {
    val notif = rememberSaveable { mutableStateOf("") }
    if (notif.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, notif.value, Toast.LENGTH_LONG).show()
        notif.value = ""
    }
    var name by rememberSaveable { mutableStateOf("Name") }
    var username by rememberSaveable { mutableStateOf("Username") }
    //var bio by rememberSaveable { mutableStateOf("Bio") }
    Column() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Cancel", modifier = Modifier.clickable { notif.value = "Cancelled" })
            Text(text = "Save", modifier = Modifier.clickable {onUserProfileSaved(name, username)})
        }

        AccountImage()

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            Text(text = "Name", modifier = Modifier.width(100.dp))
            TextField(
                value = name,
                onValueChange = { name = it },
                colors = TextFieldDefaults.textFieldColors(Color.Black)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Username", modifier = Modifier.width(100.dp))
            TextField(
                value = username,
                onValueChange = { username = it },
                colors = TextFieldDefaults.textFieldColors(Color.Black)
            )
        }

        /*
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.Top
        ){
            Text(text = "Bio", modifier = Modifier.width(100.dp))
            TextField(
                value = bio,
                onValueChange = { bio = it },
                colors = TextFieldDefaults.textFieldColors(textColor = Color.Black),
                singleLine = false,
                modifier = Modifier.height(150.dp)
            )
        }
         */

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountImage() {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = CircleShape, modifier = Modifier
            .padding(8.dp)
            .size(100.dp)) {
            Image(
                painter = painterResource(R.drawable.user),
                contentDescription = null,
                modifier = Modifier.wrapContentSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

//@Preview
//@Composable
//fun CreateAccountScreenPreview() {
//    CreateAccountScreen(onNewName = {}, onNewUserName = {})
//}




