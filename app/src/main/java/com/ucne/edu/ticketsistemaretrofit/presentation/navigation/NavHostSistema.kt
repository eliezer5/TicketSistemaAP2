package com.ucne.edu.ticketsistemaretrofit.presentation.navigation

import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucne.edu.ticketsistemaretrofit.presentation.sistema.AddSistemaScreen
import com.ucne.edu.ticketsistemaretrofit.presentation.sistema.ListSistemaScreen
import com.ucne.edu.ticketsistemaretrofit.presentation.ticket.AddTicketScreen
import com.ucne.edu.ticketsistemaretrofit.presentation.ticket.ListTicketScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostSistemas(
    navHostController: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Menu")
                Spacer(modifier = Modifier.padding(10.dp))

                NavigationDrawerItem(
                    label = { Text(text = "Lista sistemas") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navHostController.navigate(Screen.SistemaList)
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Lista Tickets") },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                        navHostController.navigate(Screen.TicketList)
                    }
                )
            }
        },
    ) {
        TopAppBar(
            title = {"Menu"},
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Abrir menú")
                }
            },

        )
        Column(

        ) {


            // Contenido del NavHost debajo del TopAppBar
            Box(modifier = Modifier.padding(top = 80.dp)) {
                NavHost(
                    navController = navHostController,
                    startDestination = Screen.SistemaList
                ) {
                    composable<Screen.SistemaList> {
                        ListSistemaScreen(
                            goToAdd = { navHostController.navigate(Screen.Sistema(0)) },
                            onSelect = { navHostController.navigate(Screen.Sistema(it)) }
                        )
                    }

                    composable<Screen.Sistema> {
                        AddSistemaScreen(goToBack = { navHostController.navigate(Screen.SistemaList) })
                    }

                    composable<Screen.TicketList> {
                        ListTicketScreen(
                            goToAdd = { navHostController.navigate(Screen.Ticket(0)) },
                            onSelect = { navHostController.navigate(Screen.Ticket(it)) }
                        )
                    }

                    composable<Screen.Ticket> {
                        AddTicketScreen(goToBack = { navHostController.navigate(Screen.TicketList) })
                    }
                }
            }
        }
    }
}