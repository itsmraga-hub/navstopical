package com.example.navs_topical.ui.components


import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.navs_topical.ui.screens.HomeScreen
import com.example.navs_topical.ui.screens.auth.SignInScreen
import com.example.navs_topical.ui.screens.auth.SignUpScreen
import com.example.navs_topical.ui.screens.splash.SplashScreen


@Composable
fun NavigationComponent(navController: NavHostController) {
//    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("sign_in") { SignInScreen(navController) }
        composable("sign_up") { SignUpScreen(navController) }
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen() }
    }
}
