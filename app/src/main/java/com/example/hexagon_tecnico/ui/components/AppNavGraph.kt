package com.example.hexagon_tecnico.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hexagon_tecnico.util.PersonUtil

@Composable
fun AppNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "person_list",
        modifier = Modifier.padding(paddingValues = PaddingValues())

    ) {
        composable("person_list") {
            PersonListScreen(PersonUtil.samplePersonList(), navController)
        }
        composable("create_person") {
            CreatePersonForm { person ->
                navController.navigate("person_list")
            }
        }
    }
}