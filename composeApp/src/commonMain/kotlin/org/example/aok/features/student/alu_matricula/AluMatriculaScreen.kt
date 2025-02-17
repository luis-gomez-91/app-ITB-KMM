package org.example.aok.features.student.alu_matricula

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import org.example.aok.features.common.home.HomeViewModel
import org.example.aok.features.common.login.LoginViewModel
import org.example.aok.ui.components.MyCircularProgressIndicator
import org.example.aok.ui.components.alerts.MyErrorAlert
import org.example.aok.ui.components.dashboard.DashBoardScreen

@Composable
fun AluMatriculaScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    loginViewModel: LoginViewModel,
    aluMatriculaViewModel: AluMatriculaViewModel
) {
    DashBoardScreen(
        title = "Matrícula en línea",
        navController = navController,
        content = {
            Screen(
                homeViewModel,
                aluMatriculaViewModel,
                navController
            )
        },
        homeViewModel = homeViewModel,
        loginViewModel = loginViewModel
    )
}

@Composable
fun Screen(
    homeViewModel: HomeViewModel,
    aluMatriculaViewModel: AluMatriculaViewModel,
    navController: NavHostController
) {
    val isLoading by homeViewModel.isLoading.collectAsState(false)
    val error by homeViewModel.error.collectAsState(null)

    LaunchedEffect(Unit) {
        homeViewModel.clearError()
        homeViewModel.clearSearchQuery()
        homeViewModel.homeData.value!!.persona.idInscripcion?.let {
            aluMatriculaViewModel.onloadAluMatricula(
                it, homeViewModel
            )
        }
    }

    if (isLoading) {
        MyCircularProgressIndicator()
    } else {

    }

    if (error != null) {
        MyErrorAlert(
            titulo = error!!.title,
            mensaje = error!!.error,
            onDismiss = {
                homeViewModel.clearError()
                navController.popBackStack()
            },
            showAlert = true
        )
    }
}