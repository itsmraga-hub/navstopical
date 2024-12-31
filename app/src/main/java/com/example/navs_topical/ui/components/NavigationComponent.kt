package com.example.navs_topical.ui.components


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.navs_topical.ui.screens.HomeScreen
import com.example.navs_topical.ui.screens.auth.SignInScreen
import com.example.navs_topical.ui.screens.auth.SignUpScreen
import com.example.navs_topical.ui.screens.splash.SplashScreen
import com.example.navs_topical.verses.data.ThemeMode


@Composable
fun NavigationComponent(navController: NavHostController) {
//    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
    val currentRouteName = currentRoute.value?.destination?.route
    val themeState = remember { mutableStateOf(ThemeMode.SYSTEM) }


    NavHost(navController = navController, startDestination = "splash") {
        composable("sign_in") { SignInScreen(navController) }
        composable("sign_up") { SignUpScreen(navController) }
        composable("splash") { SplashScreen(navController) }
        composable("home") { HomeScreen(
            themeState = themeState,
            modifier = Modifier.padding(4.dp)
        ) }
    }
}
