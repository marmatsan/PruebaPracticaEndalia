package com.example.pruebapracticaandroid.ui

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.pruebapracticaandroid.R
import com.example.pruebapracticaandroid.activities.DirectoryActivity
import com.example.pruebapracticaandroid.activities.EmployeeDetailActivity
import com.example.pruebapracticaandroid.activities.LoginActivity
import com.example.pruebapracticaandroid.directoryData.DirectoryData
import com.example.pruebapracticaandroid.model.MainViewModel
import com.example.pruebapracticaandroid.model.SearchWidgetState
import com.google.gson.Gson

@Composable
fun DirectoryScreen(mainViewModel: MainViewModel) {

    val searchWidgetState by mainViewModel.searchWidgetState
    val searchTextState by mainViewModel.searchTextState

    Scaffold(topBar = {
        MainAppBar(
            searchWidgetState = searchWidgetState,
            searchTextState = searchTextState,
            onTextChange = {
                mainViewModel.updateSearchTextState(newValue = it)
            },
            onCloseClicked = {
                mainViewModel.updateSearchTextState(newValue = "")
                mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
            },
            onSearchClicked = {
                Log.d("Searched Text", it)
            },
            onSearchTriggered = {
                mainViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
            }
        )
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            DirectoryContainer()
        }
    }
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            AppBar(onSearchClicked = onSearchTriggered)
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun AppBar(onSearchClicked: () -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    val currentContext = LocalContext.current

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
                DropdownMenuItem(onClick = {
                    currentContext.startActivity(
                        Intent(
                            currentContext,
                            LoginActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                }) {
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
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (text: String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = Color.White
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(), value = text, onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Busca por nombre o cargo...",
                    color = Color.Black
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium), onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            )
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DirectoryContainer() {
    val directoryData = DirectoryData()
    val data = directoryData.getData()
    val currentContext = LocalContext.current

    LazyColumn {
        val grouped = data.groupBy { it.surname.first() }

        grouped.forEach { (header, items) ->
            stickyHeader {
                Text(
                    text = header.toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 42.dp, top = 12.dp, bottom = 12.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            items(items = items) { employee ->
                Surface(modifier = Modifier.clickable {
                    val employeeAsGson = Gson().toJson(employee)
                    val intent = Intent(currentContext, EmployeeDetailActivity::class.java)
                        .putExtra("employeeAsGson", employeeAsGson)
                    currentContext.startActivity(intent)
                }) {
                    DirectoryItem(employee = employee)
                }
            }
        }
    }
}
