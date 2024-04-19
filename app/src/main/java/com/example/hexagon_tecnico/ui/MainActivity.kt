package com.example.hexagon_tecnico.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.hexagon_tecnico.ui.components.AppNavGraph
import com.example.hexagon_tecnico.ui.theme.Hexagon_tecnicoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hexagon_tecnicoTheme {
                val navController = rememberNavController()
                Scaffold(
                    content = { paddingValues ->
                        AppNavGraph(navController, paddingValues)
                    }
                )
            }
        }
    }
}