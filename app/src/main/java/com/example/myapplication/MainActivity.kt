package com.example.myapplication

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController,"home_screen") {
                composable("home_screen") {
                    HomeScreen(navController)
                }
                composable("detail_screen"+"/{address}") {
                    val address = it.arguments?.getString("address")
                    DetailScreen(address?:"No address",navController)
                }
            }
        }
    }
}

@Composable
fun DataItem(person: Person, modifier: Modifier = Modifier, navController: NavController) {
    val t = LocalContext.current.getString(person.addressId)
    Row {
        Text(
            text = LocalContext.current.getString(person.nameId),
            modifier = modifier.weight(1f)
        )
        Button(
            onClick = { navController.navigate("detail_screen/$t") },
            modifier = Modifier
                .layoutId("button")
        ) {
            Text("View person's address")
        }
    }
}
@Composable
fun DataList(dataList: List<Person>, modifier: Modifier = Modifier, navController : NavController) {
    LazyColumn(modifier = modifier) {
        items(dataList) { person ->
            DataItem(
                person = person,
                modifier = Modifier.padding(8.dp),
                navController = navController
            )
        }
    }
}
@Composable
fun HomeScreen(navController : NavController) {
    DataList(
       dataList = Datasource().loadData(),
        navController = navController
    )
}