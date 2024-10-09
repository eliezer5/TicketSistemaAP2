package com.ucne.edu.ticketsistemaretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ucne.edu.ticketsistemaretrofit.presentation.navigation.NavHostSistemas
import com.ucne.edu.ticketsistemaretrofit.ui.theme.TicketSistemaRetrofitTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketSistemaRetrofitTheme {
                val navHost = rememberNavController()
                NavHostSistemas(navHost)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicketSistemaRetrofitTheme {
        val navHost = rememberNavController()
        NavHostSistemas(navHost)
    }
}