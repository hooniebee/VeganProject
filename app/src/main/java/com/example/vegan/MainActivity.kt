package com.example.vegan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vegan.screen.AlbumScreen
import com.example.vegan.screen.FlexitarianScreen
import com.example.vegan.screen.LactoOvo
import com.example.vegan.screen.MainScreen
import com.example.vegan.screen.Memo
import com.example.vegan.screen.Pesco
import com.example.vegan.screen.QuestionsScreen
import com.example.vegan.screen.ReviewDetailScreen
import com.example.vegan.screen.Veganism
import com.example.vegan.ui.theme.VeganTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VeganTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHostWithNavController()
                }
            }
        }
    }
}

@Composable
fun NavHostWithNavController() {
    val navController = rememberNavController()
    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.fade_in) // 직접 리소스 ID로 애니메이션을 지정할 수도 있습니다.
        .setExitAnim(R.anim.fade_out)
        .setPopEnterAnim(R.anim.fade_in)
        .setPopExitAnim(R.anim.fade_out)
        .build()

    NavHost(navController = navController, startDestination = "Main") {
        composable("Main") {
            MainScreen(navController = navController)
        }
        composable("questions") {
            QuestionsScreen(navController = navController)
        }
        composable("Flexitarian") {
            FlexitarianScreen(navController = navController)
        }
        composable("Pesco") {
            Pesco(navController = navController)
        }
        composable("LactoOvo") {
            LactoOvo(navController = navController)
        }
        composable("Veganism") {
            Veganism(navController = navController)
        }
        composable("diary") {
            Memo(navController = navController)
        }
        composable("memory") {
            AlbumScreen(navController = navController)
        }

        composable("review/{uid}") { backStackEntry ->
            val uid = backStackEntry.arguments?.getString("uid")?.toIntOrNull()
            if (uid != null) {
                ReviewDetailScreen(navController = navController, uid = uid)
            }
        }
    }
}
