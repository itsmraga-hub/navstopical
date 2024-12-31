package com.example.navs_topical

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navs_topical.ui.components.NavigationComponent
import com.example.navs_topical.ui.screens.HomeScreen
import com.example.navs_topical.ui.theme.NavstopicalTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavstopicalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

//@Composable
//fun CustomTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    MaterialTheme(
//        colors = if (darkTheme) DarkColors else LightColors,
//        content = content
//    )
//}

//@Composable
//fun MyAppTheme(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    content: @Composable () -> Unit
//) {
//    val colors = if (darkTheme) {
//        darkColors(
//            primary = Color.Black,
//            primaryVariant = Color.DarkGray,
//            secondary = Color.Gray
//        )
//    } else {
//        lightColors(
//            primary = Color.White,
//            primaryVariant = Color.LightGray,
//            secondary = Color.Gray
//        )
//    }
//
//    MaterialTheme(
//        colors = colors,
//        typography = Typography,
//        shapes = Shapes,
//        content = content
//    )
//}



@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavigationComponent(navController)
//    NavHost(navController = navController, startDestination = "splash") {
//        composable("splash") { SplashScreen(navController) }
//        composable("home") { HomeScreen() }
//    }
}


//import android.Manifest
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.pm.PackageManager
//import android.os.Build
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.content.ContextCompat
//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.firebase.Firebase
//import com.google.firebase.messaging.messaging
////import com.google.firebase.quickstart.fcm.R
////import com.example.navs_topical
////import com.google.firebase.quickstart.fcm.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    private val requestPermissionLauncher = registerForActivityResult(
//        ActivityResultContracts.RequestPermission(),
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            Toast.makeText(this, "Notifications permission granted", Toast.LENGTH_SHORT)
//                .show()
//        } else {
//            Toast.makeText(
//                this,
//                "FCM can't post notifications without POST_NOTIFICATIONS permission",
//                Toast.LENGTH_LONG,
//            ).show()
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        val binding = ActivityMainBinding.inflate(layoutInflater)
////        setContentView(binding.root)
////
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
////            // Create channel to show notifications.
////            val channelId = getString(R.string.default_notification_channel_id)
////            val channelName = getString(R.string.default_notification_channel_name)
////            val notificationManager = getSystemService(NotificationManager::class.java)
////            notificationManager?.createNotificationChannel(
////                NotificationChannel(
////                    channelId,
////                    channelName,
////                    NotificationManager.IMPORTANCE_LOW,
////                ),
////            )
////        }
//
//        // If a notification message is tapped, any data accompanying the notification
//        // message is available in the intent extras. In this sample the launcher
//        // intent is fired when the notification is tapped, so any accompanying data would
//        // be handled here. If you want a different intent fired, set the click_action
//        // field of the notification message to the desired intent. The launcher intent
//        // is used when no click_action is specified.
//        //
//        // Handle possible data accompanying notification message.
//        // [START handle_data_extras]
//        intent.extras?.let {
//            for (key in it.keySet()) {
//                val value = intent.extras?.getString(key)
//                Log.d(TAG, "Key: $key Value: $value")
//            }
//        }
//        // [END handle_data_extras]
//
////        binding.subscribeButton.setOnClickListener {
////            Log.d(TAG, "Subscribing to weather topic")
////            // [START subscribe_topics]
////            Firebase.messaging.subscribeToTopic("weather")
////                .addOnCompleteListener { task ->
////                    var msg = getString(R.string.msg_subscribed)
////                    if (!task.isSuccessful) {
////                        msg = getString(R.string.msg_subscribe_failed)
////                    }
////                    Log.d(TAG, msg)
////                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
////                }
////            // [END subscribe_topics]
////        }
////
////        binding.logTokenButton.setOnClickListener {
////            // Get token
////            // [START log_reg_token]
////            Firebase.messaging.token.addOnCompleteListener(
////                OnCompleteListener { task ->
////                    if (!task.isSuccessful) {
////                        Log.w(TAG, "Fetching FCM registration token failed", task.exception)
////                        return@OnCompleteListener
////                    }
////
////                    // Get new FCM registration token
////                    val token = task.result
////
////                    // Log and toast
////                    val msg = getString(R.string.msg_token_fmt, token)
////                    Log.d(TAG, msg)
////                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
////                },
////            )
////            // [END log_reg_token]
////        }
//
//        Toast.makeText(this, "See README for setup instructions", Toast.LENGTH_SHORT).show()
//        askNotificationPermission()
//    }
//
//    private fun askNotificationPermission() {
//        // This is only necessary for API Level > 33 (TIRAMISU)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
//                PackageManager.PERMISSION_GRANTED
//            ) {
//                // FCM SDK (and your app) can post notifications.
//            } else {
//                // Directly ask for the permission
//                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//            }
//        }
//    }
//
//    companion object {
//
//        private const val TAG = "MainActivity"
//    }
//}