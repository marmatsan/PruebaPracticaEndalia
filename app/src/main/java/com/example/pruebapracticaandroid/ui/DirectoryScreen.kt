package com.example.pruebapracticaandroid.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.R
import com.example.pruebapracticaandroid.directoryData.DirectoryData

@Composable
fun DirectoryScreen() {
    Scaffold(topBar = {
        AppBar(onSearchClicked = {})
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            DirectoryContainer()
        }
    }
}


@Composable
fun AppBar(onSearchClicked: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.endalia_isotipo_512),
                    contentDescription = "TopAppBarColor",
                    modifier = Modifier
                        .height(24.dp)
                        .padding(end = 24.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = "Directorio"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color(android.graphics.Color.WHITE)
                )
            }
            IconButton(onClick = {
                showMenu = true
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Open Options"
                )
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "Log out",
                        modifier = Modifier
                            .height(12.dp)
                            .padding(
                                end = 10.dp
                            )
                    )
                    Text(text = "Cerrar sesión")
                }
            }
        }
    )
}

@Composable
fun DirectoryContainer(){
    val directoryData = DirectoryData()
    val data = directoryData.getData()
    
    LazyColumn(){
        items(items = data){ employee ->
            DirectoryItem(employee = employee)
        }
    }
}
